package com.karrus.demo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.gwidgets.api.leaflet.DivIcon;

import com.gwidgets.api.leaflet.L;
import com.gwidgets.api.leaflet.LatLng;
import com.gwidgets.api.leaflet.LayerGroup;
import com.gwidgets.api.leaflet.Map;
import com.gwidgets.api.leaflet.Marker;
import com.gwidgets.api.leaflet.events.EventCallback;
import com.gwidgets.api.leaflet.events.EventTypes;
import com.gwidgets.api.leaflet.events.MouseEvent;
import com.gwidgets.api.leaflet.options.DivIconOptions;
import com.gwidgets.api.leaflet.options.MarkerOptions;
import com.gwidgets.api.leaflet.options.PolylineOptions;
import com.gwidgets.api.leaflet.options.PopupOptions;
import com.gwidgets.api.leaflet.options.TooltipOptions;
import com.karrus.demo.shared.hibernate.LfMarker;


import com.karrus.demo.shared.hibernate.LfEquipement;
import com.karrus.demo.shared.hibernate.LfFonction;
import com.karrus.demo.shared.hibernate.LfConf;
/**
 * Data managment.
 */

public class LoadData extends Features {

	public final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	// ---- Lists ---- //
	// public static HashMap<String, RsuStation> mapstation = new HashMap<String,
	// RsuStation>();
	public static HashMap<String, List<String>> lanes = new HashMap<String, List<String>>();
	public static HashMap<Marker, String> CityMarker = new HashMap<Marker, String>();

	public static  ArrayList<Marker> UBRsMarkers = new ArrayList<Marker>();
	public static ArrayList<LfEquipement> rsuList = new ArrayList<LfEquipement>();
	public static HashMap<String, Marker> PmvMarker = new HashMap<String, Marker>();
	public static HashMap<String, LatLng[][]> maplines1518 = new HashMap<String, LatLng[][]>();
	public static HashMap<String, LatLng[][]> maplines1014 = new HashMap<String, LatLng[][]>();
	//public static HashMap<String, TtItinerary> mapiti = new HashMap<String, TtItinerary>();
	public LayerGroup UBRsMarker = L.layerGroup(null);
	public LayerGroup Polylines1518 = L.layerGroup(null);
	public LayerGroup Polylines1014 = L.layerGroup(null);

	// ---- Map ---- //
	public static Map map;
	public static Marker marker;
	// ---- Widgets ---- //
	public static MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	public static SuggestBox box = null;
	public CheckBox UBR = new CheckBox("UBR");
	public Button closePop = new Button("Close All Popups");
	public Button search = new Button("Search");
	// ---- Polyline colors ---- //
	PolylineOptions fast = new PolylineOptions.Builder().smoothFactor(0.0).color("green").build();
	PolylineOptions medium = new PolylineOptions.Builder().smoothFactor(0.0).color("yellow").build();
	PolylineOptions slow = new PolylineOptions.Builder().smoothFactor(0.0).color("red").build();
	PolylineOptions noData = new PolylineOptions.Builder().smoothFactor(0.0).color("purple").build();

	

	// Fonctions de chargement qui va récupérer tout les RSU et toutes les routes
	// afin de les charger
	// dans la base de données au préalable
	public void LoadLanesRSU() {
		greetingService.getAllEquipement(new AsyncCallback<ArrayList<LfEquipement>>() {
			public void onFailure(Throwable caught) {
				Log.error("greetingService.getAllEquipement() failed.");

			}

			@Override
			public void onSuccess(ArrayList<LfEquipement> AllRsu) {
				rsuList.addAll(AllRsu);
			}
		});
		//LoadDatabase();
	}

	public String getMapURL() {
		ArrayList<LfConf> list = new ArrayList<LfConf>();
		greetingService.getMapURL(new AsyncCallback<List<LfConf>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<LfConf> result) {
				list.addAll(result);
				
			}
			
		});
		
		
		if(!list.isEmpty() && list.get(0).getValue() !=null) {
			return list.get(0).getValue();
		}else {
			return "http://localhost:8080/geoserver/nice/wms";
		}
		
	}
	
	public void LoadDatabase() {

		// ---- Options popup ---- //
		PopupOptions popupUBROptions = new PopupOptions.Builder().className("UBRPopup").closeButton(true)
				.autoClose(false).build();
		TooltipOptions tooltipOptions = new TooltipOptions.Builder().direction("right").className("UBRTooltip")
				.interactive(true).sticky(true).build();

		try {
			

			// ---- Vérification de la préscence de l'UBR : si non, suppression de l'icône
			// qui lui est actuellement dédiée ---- //

			// UBRsMarkers.forEach(marker -> UBRPresent(marker, rsuList));
			Log.debug("ForEachMarker");
			// --- Mise à jour des UBR : ajout des nouveaux ---- //
			for (LfEquipement rsu : rsuList) {
//				String station = rsu.getId().replaceAll(" ", "_");

				// Test des modules du RSU si cyclecount !=-1 le module est activé sinon il est
				// hors-service
				Log.debug("GETMARKER ASYNC " + rsu.getId());

				greetingService.getMarker(rsu.getId(), new AsyncCallback<List<LfMarker>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					public void onSuccess(List<LfMarker> result) {
						// TODO Auto-generated method stub
						String station = rsu.getId().replaceAll(" ", "_");
						if (!(CityMarker.containsValue(rsu.getId().replaceAll(" ", "_")))) {
							oracle.add(rsu.getId());

							// ---- Création du marqueur (point sur la carte) ---- //

							DivIconOptions divIconOptions = new DivIconOptions.Builder("").className("ClickableHTML")
									.html(result.get(0).getHtmlMarker()).build();

							DivIcon iconUBR = L.divIcon(divIconOptions);
							// Création du markers sur les coordonées du RSU
							MarkerOptions markerOptions = new MarkerOptions.Builder().icon(iconUBR).clickable(true)
									.build();
							
							marker = L.marker(L.latLng(rsu.getLatitude(), rsu.getLongitude()), markerOptions);
							// Création du popup qui s'ouvrira quand on clique sur le marker
							marker.bindPopup(result.get(0).getHtmlPopup(), popupUBROptions);

							marker.bindTooltip(result.get(0).getHtmlHover(), tooltipOptions);
//							POPUP DRAG AND DROP
							marker.on(EventTypes.MarkerEvents.CLICK, new EventCallback<MouseEvent>() {
								@Override
								public void call(MouseEvent event) {
									Element divpop = DOM.getElementById("popup" + station).getParentElement()
											.getParentElement().getParentElement();
									Element divcontent = DOM.getElementById("popup" + station).getParentElement()
											.getParentElement();
									divpop.setDraggable(Element.DRAGGABLE_TRUE);

									Event.sinkEvents(divcontent, Event.ONMOUSEDOWN);
									Event.setEventListener(divcontent, new EventListener() {
										public void onBrowserEvent(Event event) {
											event.preventDefault();
											// Window.alert("CLICK DIVCONTENT");
											NativeEvent e = Document.get().createMouseDownEvent(0, event.getScreenX(),
													event.getScreenY(), event.getClientX(), event.getClientY(), false,
													false, false, false, 0);
											DOM.dispatchEvent(Event.as(e), divpop);

										}
									});

									Event.sinkEvents(divpop, Event.MOUSEEVENTS);
									Event.setEventListener(divpop, new EventListener() {
										int initialX, initialY = 0;
										int depX, depY = 0;
										int offsetiniX, offsetiniY = 0;
										boolean clicked = false;

										public void onBrowserEvent(Event event) {

											if (event.getTypeInt() == Event.ONMOUSEDOWN) {
												// event.stopPropagation();
												event.preventDefault();
												map.dragging.disable();
												initialX = event.getClientX();
												initialY = event.getClientY();
												offsetiniX = divpop.getOffsetLeft();
												offsetiniY = divpop.getOffsetTop();
												clicked = true;
											} else if (event.getTypeInt() == Event.ONMOUSEMOVE && clicked) {
												event.preventDefault();
												depX = event.getClientX() - initialX;
												depY = event.getClientY() - initialY;
												divpop.getStyle().setLeft(depX + offsetiniX + 5, Unit.PX);
												divpop.getStyle().setTop(depY + offsetiniY + 5, Unit.PX);
											} else if (event.getTypeInt() == Event.ONMOUSEUP) {
												clicked = false;
												map.dragging.enable();
											}
										}
									});

								}

							});
//							POPUP DRAG AND DROP
							
							marker.getPopup().update();
							
							
							// Ajoute des markers au layergroup
							UBRsMarkers.add(marker);

							
							UBRsMarker.addLayer(marker);
							CityMarker.put(marker, station);
							// Ajout des markers a la carte
							// marker.addTo(map);
							// ---- Création du marqueur ---- //
						}
					}

				});
				UBRsMarker.addTo(map);
				
				// ---- Récupération des données de chacun des modules du RSU ---- //
				// ---- UBRStatement ---- //
				
				greetingService.getAllFonction(new AsyncCallback<List<LfFonction>>() {
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<LfFonction> result) {

						for(LfFonction fonction : result) {
							if(fonction.getId().getFonction().equals("bluetooth")) {
								Element bluetooth = DOM.createElement("div");
								bluetooth.setId("bluetooth" + fonction.getId().getId().replaceAll(" ", "_"));
								// Création de la 'vue' qui va afficher les données récupéré
								bluetooth.setInnerHTML(fonction.getHtmlPopup());
								// Stockage de la vue dans un élément div du DOM >informmationsPopupMarker
								if(fonction.getHtmlPopup().contains("<p>")) {
									DOM.getElementById(fonction.getId().getId().replaceAll(" ", "_").concat("imgB")).getStyle().setBackgroundColor("red");
								}else {
									DOM.getElementById(fonction.getId().getId().replaceAll(" ", "_").concat("imgB")).getStyle().clearBackgroundColor();
								}
								if (DOM.getElementById("bluetooth" + fonction.getId().getId().replaceAll(" ", "_")) != null) {
									DOM.getElementById("informationsPopupMarker").replaceChild(bluetooth,
											DOM.getElementById("bluetooth" + fonction.getId().getId().replaceAll(" ", "_")));
								} else {
									DOM.getElementById("informationsPopupMarker").appendChild(bluetooth);

								}
										
							}else if(fonction.getId().getFonction().equals("traffic")) {
								if (fonction.getHtmlPopup() != null) {

									Element traffic = DOM.createElement("div");
									traffic.setId("traffic" + fonction.getId().getId().replaceAll(" ", "_"));
									traffic.setInnerHTML(fonction.getHtmlPopup());
									if(fonction.getHtmlPopup().contains("<p>")) {
										DOM.getElementById(fonction.getId().getId().replaceAll(" ", "_").concat("imgT")).getStyle().setBackgroundColor("red");
									}else {
										DOM.getElementById(fonction.getId().getId().replaceAll(" ", "_").concat("imgT")).getStyle().clearBackgroundColor();
									}
									// Stockage de la vue dans un élément div du DOM >informationsPopupMarker
									if (DOM.getElementById("traffic" + fonction.getId().getId().replaceAll(" ", "_")) != null) {
										DOM.getElementById("informationsPopupMarker").replaceChild(traffic,
												DOM.getElementById("traffic" + fonction.getId().getId().replaceAll(" ", "_")));
									} else {
										DOM.getElementById("informationsPopupMarker").appendChild(traffic);

									}
								}		
							}
							
							
						}
						
					}
					
					
				});
				
//				
			}

			// --------------------------------------PMV----------------------------------------------------
//			// ----Traficolor----//
		} catch (Exception e) {
			// Log.error("EXCEPTION CATCHED");
			Log.debug(e.getMessage());

		}

		map.addLayer(UBRsMarker, true);

	}

}
