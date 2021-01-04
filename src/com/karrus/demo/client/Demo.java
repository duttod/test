package com.karrus.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwidgets.api.leaflet.Control;
import com.gwidgets.api.leaflet.L;
import com.gwidgets.api.leaflet.Marker;
import com.gwidgets.api.leaflet.events.EventCallback;
import com.gwidgets.api.leaflet.events.EventTypes;
import com.gwidgets.api.leaflet.events.MouseEvent;
import com.gwidgets.api.leaflet.options.MapOptions;
import com.gwidgets.api.leaflet.options.TileLayerWMSOptions;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Demo extends LoadData implements  EntryPoint, ClickHandler {

	public void onModuleLoad() {
		
		LoadLanesRSU();
		
//		LoadPolylines();
//		LoadItinerary();
		// ---- Options ---- //"postgis_isere:postgis_isere_syncro_style" LAYER SERVEUR
		// ---- Options ---- //"isere:planet_osm_line" LAYER LOCAL
		//String MAP_URL = "http://92.243.8.76:8081/geoserver/postgis_isere/wms";
		//--------------------- NICE PROJECT MAP OPTIONS -------------------------------------------------
//		String MAP_URL = "http://localhost:8080/geoserver/nice/wms" ;
//		TileLayerWMSOptions tloptions = new TileLayerWMSOptions.Builder("nice:planet_osm_line").build();
//		MapOptions options = new MapOptions.Builder(L.latLng(43.696514, 7.253084), 13.0, 10.0).closePopupOnClick(false).maxZoom(18.0).build();
		//--------------------- NICE PROJECT MAP OPTIONS -------------------------------------------------
		String MAP_URL = "http://10.11.0.101:8081/geoserver/karrus/wms" ;
		//String MAP_URL = getMapURL();
		TileLayerWMSOptions tloptions = new TileLayerWMSOptions.Builder("karrus:planet_osm_line").build();
		MapOptions options = new MapOptions.Builder(L.latLng(43.696514, 7.253084), 13.0, 10.0).closePopupOnClick(false).maxZoom(18.0).build();
		
		
//		String MAP_URL = "http://localhost:8080/geoserver/isere/wms" ;
//		TileLayerWMSOptions tloptions = new TileLayerWMSOptions.Builder("isere:planet_osm_line").build();
//		MapOptions options = new MapOptions.Builder(L.latLng(45.1875602, 5.7357819), 13.0, 10.0).closePopupOnClick(false).maxZoom(18.0).build();
		
		//---- Map ---- // 
		map = L.map("map", options);
		L.tileLayer.wms(MAP_URL, tloptions).addTo(map);
		map.doubleClickZoom.disable();
		
		Timer t2 = new Timer() {
			@Override
			public void run() {
				LoadDatabase();
			}
		};
		t2.schedule(2000);
		

		//---- Load data ---- // 
				
		Timer t = new Timer() {
			@Override
			public void run() {
				LoadDatabase();
			}
		};
		
		t.scheduleRepeating(40000);
		
		
		
		// ---- Widgets ---- //
		Control scale = L.control.scale(null);
		scale.addTo(map);
//		UBR.setValue(true);
//		UBR.addClickHandler(this);
//		box = new SuggestBox(oracle);
//		search.addClickHandler(this);
//		closePop.addClickHandler(this);
//		HorizontalPanel horizontalPanel = new HorizontalPanel();
//		horizontalPanel.add(box);
//		horizontalPanel.add(search);
//		VerticalPanel verticalPanel = new VerticalPanel();
//		verticalPanel.add(horizontalPanel);
//		verticalPanel.add(closePop);
//		verticalPanel.add(UBR);
//		RootPanel.get().add(verticalPanel);
		map.addLayer(Polylines1014, false);
		map.on(EventTypes.MapEvents.ZOOM, new EventCallback<MouseEvent>() {
			
			public void call(MouseEvent event) {
                            if(map.getZoom()>=10 && map.getZoom()<=14) {
                            	map.addLayer(Polylines1014,false);
                            	Polylines1518.removeFrom(map);
                            	
                            }else if(map.getZoom()>=15 && map.getZoom()<=18){
                            	map.addLayer(Polylines1518,false);
                            	Polylines1014.removeFrom(map);
                            	
                            }
                        }
                        
                });
		}

	// ---- Button listener ---- //
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();
		if (sender == closePop) {
			UBRsMarkers.forEach(marker -> closePopups(marker));
		}
		if (sender == UBR) {
			UBRsMarkers.forEach(marker -> invisible(marker));
		}
		else if (sender == search) {
//			greetingService.setViewOnSearch(box.getValue(), new AsyncCallback<List<RsuStation>>() {
//				public void onFailure(Throwable caught) {
//					Log.error("greetingService.getTraffic() failed.");
//				}
//				@Override
//				public void onSuccess(List<RsuStation> result) {
//					if (result.get(0).getId() != null) {
//						map.setView(L.latLng(result.get(0).getLatitude(), result.get(0).getLongitude()), 14.0, null);
//					}
//				}
//			});
		}
	}

	private Object invisible(Marker marker) {
		if(UBR.getValue()==true) {
			marker.addTo(map);
		}
		else if(UBR.getValue()==false) {
			marker.removeFrom(map);
		}
		return null;
	}

	private Object closePopups(Marker marker) {
		marker.closePopup();

		return null;
	}

}
