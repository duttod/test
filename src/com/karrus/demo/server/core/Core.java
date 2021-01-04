package com.karrus.demo.server.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import com.karrus.demo.client.Features;
import com.karrus.demo.server.database.DatabaseDriver;
import com.karrus.demo.shared.hibernate.BtBox;
import com.karrus.demo.shared.hibernate.BtMacRt;
import com.karrus.demo.shared.hibernate.CtIndivData;
import com.karrus.demo.shared.hibernate.LfConf;
import com.karrus.demo.shared.hibernate.LfEquipement;
import com.karrus.demo.shared.hibernate.LfFonction;
import com.karrus.demo.shared.hibernate.Station;


public class Core extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// public final GreetingfonctionAsync greetingfonction =
	// GWT.create(Greetingfonction.class);
	private static Logger logger = Logger.getLogger(Core.class);
	public LfConf config;
	// -----------------------CONTENEUR DE DONNEES
	// -------------------------------------------------
	public static HashMap<String, List<String>> lanes = new HashMap<String, List<String>>();
	public static ArrayList<LfEquipement> rsuList = new ArrayList<LfEquipement>();
	public static HashMap<String, LfEquipement> mapstation = new HashMap<String, LfEquipement>();

	// -----------------------CONTENEUR DE DONNEES
	// -------------------------------------------------
	@SuppressWarnings("unchecked")
	public void LoadLanesRSU() {

		try {
			config = ((List<LfConf>) DatabaseDriver.get("select * from lf_conf where var='lf_equipement';",
					LfConf.class)).get(0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (config.getValue() == null) {
			config.setValue("lf_equipement");
		}

		String[] tabequip = config.getValue().split(",");
		@SuppressWarnings("rawtypes")
		ArrayList<LfEquipement> test = new ArrayList<LfEquipement>();
		try {
			test.addAll((List<LfEquipement>) DatabaseDriver.get("select * from lf_equipement;",
					LfEquipement.class));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (test.isEmpty()) {
			for (int i = 0; i < tabequip.length; i++) {
				ArrayList<LfEquipement> mylist = new ArrayList<LfEquipement>();
				try {
					mylist.addAll((List<LfEquipement>) DatabaseDriver
							.get("select * from " + tabequip[i] + " ;", LfEquipement.class));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				for (LfEquipement eq : mylist) {
					try {
						DatabaseDriver.executeQuery("Insert into lf_equipement values('" + eq.getId() + "','"
								+ eq.getLongitude() + "','" + eq.getLatitude() + "');");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		try {
			rsuList.addAll((List<LfEquipement>) DatabaseDriver.get("select * from lf_equipement ORDER BY id;",
					LfEquipement.class));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (LfEquipement rsu : rsuList) {
			mapstation.put(rsu.getId(), rsu);

		}
//		Timer t = new Timer();
//		t.schedule(new GetData(), new Date());
	}
	
	public String GetSerial(String id) {
		ArrayList<Station> list = new ArrayList<Station>();
		try {
			list.addAll((List<Station>) DatabaseDriver.get("select * from station where station='"+id+"' ;", Station.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!list.isEmpty()) {
			return list.get(0).getSerial();
		}else {
			return "0";
		}
	}
	
	public class GetData extends TimerTask {

		@Override
		public void run() {
			logger.info("GetData Executed");
			// ArrayList<RsuDiag> diagRSU = new ArrayList<RsuDiag>();
			ArrayList<LfFonction> info = new ArrayList<LfFonction>();
			for (LfEquipement rsu : rsuList) {
				String station = rsu.getId().replaceAll(" ", "_");
//---------------------------------------------------------UBR STATEMENT--------------------------------

//---------------------------------------------------UBR STATEMENT-----------------------------------------------------
//----------------------------------------------------------BLUETOOTH-------------------------------------------------------
				try {
					if (!(DatabaseDriver.get("Select * from bt_box where site='" + rsu.getId() + "'  ;",
							BtBox.class)).isEmpty()) {
						CompletableFuture.runAsync(new Runnable() {
							@SuppressWarnings("unchecked")
							@Override
							public void run() {
								ArrayList<BtMacRt> bluetoothRSU = new ArrayList<BtMacRt>();
								try {
									// AND timestamp > current_timestamp - INTERVAL '10 minutes' AND timestamp <
									// current_timestamp
									bluetoothRSU.addAll((List<BtMacRt>) DatabaseDriver.get(
											"SELECT * FROM bt_mac_rt WHERE station = '" + rsu.getId()
													+ "' AND timestamp > current_timestamp - INTERVAL '10 minutes' AND timestamp < current_timestamp ;",
													BtMacRt.class));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								int nbPassages = 0;
								Date passagePremier = null;
								Date passageDernier = null;

								for (BtMacRt bth : bluetoothRSU) {
									if (nbPassages == 0 && bluetoothRSU.size() != 0) {
										passageDernier = bth.getId().getTimestamp();
										passagePremier = bth.getId().getTimestamp();
									} else if (bth.getId().getTimestamp().before(passagePremier)) {
										passagePremier = bth.getId().getTimestamp();
									} else if (bth.getId().getTimestamp().after(passageDernier)) {
										passageDernier = bth.getId().getTimestamp();
									}
									nbPassages++;
								}
								String bhtml = "";
								if (nbPassages != 0) {
									bhtml = "<div id=\""+station+"\"><table class=Bluetooth>" + "<tr class=timestamp> <th>"
											+ Features.formatDateBluetooth(passagePremier) + " - "
											+ Features.formatDateBluetooth(passageDernier)
											+ " </th> <td></td> <td> </td> </tr>"
											+ "<tr>	<th> Nombre de passages : </th> <td>" + nbPassages + " </td> </tr>"
											+ "</table>";
								} else {
									bhtml = "<div id=\""+station+"\"><p style=\"color:red;font-size:18px\">Aucun passage depuis plus de 10 minutes <p></div>";
								}
								// ------------------------TEST---------------
								info.clear();
								String bluetooth_html = bhtml;
								try {
									info.addAll(
											(List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"
													+ rsu.getId() + "' and fonction='bluetooth';", LfFonction.class));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								// ------------------------TEST---------------
								if (info.isEmpty()) {
									CompletableFuture.runAsync(new Runnable() {
										@Override
										public void run() {
											try {
												DatabaseDriver.executeQuery("Insert into lf_fonction values('"
														+ rsu.getId() + "','bluetooth','" + bluetooth_html + "');");
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});

								} else {
									CompletableFuture.runAsync(new Runnable() {
										@Override
										public void run() {
											try {
												DatabaseDriver.executeQuery("Update lf_fonction set html_popup='"
														+ bluetooth_html + "' where fonction='bluetooth' and id='"
														+ rsu.getId() + "';");
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});

								}
							}
						});
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

//----------------------------------------------------------BLUETOOTH-------------------------------------------------------				
//----------------------------------------------------------TRAFIC----------------------------------------------------------
				try {
					if (!(DatabaseDriver.get("Select * from station where station='" + rsu.getId() + "';",
							Station.class)).isEmpty()) {
						CompletableFuture.runAsync(new Runnable() {
							@Override
							public void run() {
								String nbtrafic = "0";
								Date dernierPassage = null;
								try {
									// AND timestamp > current_timestamp - INTERVAL '2 minutes'

									nbtrafic = DatabaseDriver.countQuery("SELECT count(*) FROM"
											+ " ct_indiv_data WHERE station= \'" + GetSerial(rsu.getId())
											+ "\'  AND timestamp > current_timestamp - INTERVAL '10 minutes' AND timestamp < current_timestamp;");
									ArrayList<CtIndivData> thelist = new ArrayList<CtIndivData>();
									thelist.addAll((List<CtIndivData>) DatabaseDriver.get("select * from ct_indiv_data where  timestamp > current_timestamp - INTERVAL '10 minutes' AND timestamp < current_timestamp"
											+ " and station='"+GetSerial(rsu.getId())+"' order by timestamp desc limit 1;", CtIndivData.class));
									
									if(!thelist.isEmpty()) {
										dernierPassage = thelist.get(0).getId().getTimestamp();
									}
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String thtml = "";
								//logger.info(rsu.getId()+":"+nbtrafic);
								if (nbtrafic != "0") {
									thtml = "<div id=\""+station+"\"><table class=Traffic>" + "<tr class=timestamp> <th>"
											+ "Dernier Passage:"+Features.formatDateBluetooth(dernierPassage)+" </th> <td></td> <td> </td> </tr>"
											+ "<tr>	<th> Nombre de passages : </th> <td>" + nbtrafic
											+ " </td> </tr>" + "</table> </div>";
								} else {
									thtml = "<div id=\""+station+"\"><p style=\"color:red;font-size:18px\">Aucun passage depuis plus de 10 minutes <p></div>";
								}

								// ------------------------TEST---------------
								info.clear();
								try {
									info.addAll(
											(List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"
													+ rsu.getId() + "' and fonction='traffic';", LfFonction.class));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								// ------------------------TEST---------------
								final String traffic_html1 = thtml;
								if (info.isEmpty()) {
									CompletableFuture.runAsync(new Runnable() {
										@Override
										public void run() {
											try {
												DatabaseDriver.executeQuery("Insert into lf_fonction values('"
														+ rsu.getId() + "','traffic','" + traffic_html1 + "');");
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
								} else {
									CompletableFuture.runAsync(new Runnable() {
										@Override
										public void run() {
											try {
												if (traffic_html1 != null) {
													DatabaseDriver.executeQuery("Update lf_fonction set html_popup='"
															+ traffic_html1 + "' where fonction='traffic' and id='"
															+ rsu.getId() + "';");
												}

											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});

								}
							}
						});
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//----------------------------------------------------------TRAFIC----------------------------------------------------------
//----------------------------------------------------------METEO-----------------------------------------------------------

//----------------------------------------------------------METEO-----------------------------------------------------------
//----------------------------------------------------------V2X-------------------------------------------------------------

//----------------------------------------------------------V2X-------------------------------------------------------------
//----------------------------------------------------------PMV-------------------------------------------------------------

			}
			logger.info("GetData Finished");
		}
	}

	public Core() {
		try {
			DatabaseDriver.clearTable("lf_marker");
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		logger.info("Core started.");
		LoadLanesRSU();

		for (LfEquipement rsu : rsuList) {
			String station = rsu.getId().replaceAll(" ", "_");
			String stationP = "''" + station + "''";
			String htmlMarker = "<table onclick=\"ouverturepopup(" + stationP + ")\" class=UBR>" + "<tbody>" + "<tr>";
			String divnameT = station.concat("imgT");
			String divnameB = station.concat("imgB");
			ArrayList<LfFonction> func = new ArrayList<LfFonction>();
			String htmlPopup = "<div id=\"popup" + station + "\" class=\"UBRPopupContent\">" + "<div class=\"menu\">"
					+ rsu.getId();

			try {
				func.addAll((List<LfFonction>) DatabaseDriver.get(
						"Select * from lf_fonction where id='" + rsu.getId() + "' and fonction='bluetooth';",
						LfFonction.class));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if (!func.isEmpty()) {
				htmlMarker += "<td id=\""+divnameB+"\" onclick=\"onClickBluetooth("+"''"+station+"''"+")\" class=\"enabled\"><img src=\"images/bluetooth2.png\"></td>";
				htmlPopup += func.get(0).getHtmlPopup();
			}
			func.clear();
			try {
				func.addAll((List<LfFonction>) DatabaseDriver.get(
						"Select * from lf_fonction where id='" + rsu.getId() + "' and fonction='traffic';",
						LfFonction.class));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (!func.isEmpty()) {
				htmlMarker += "<td id=\"" + divnameT + "\" onclick=\"onClickTraffic("+"''"+station+"''"+")\" class=\"enabled\"><img src=\"images/traffic.png\"></td>";
				htmlPopup += func.get(0).getHtmlPopup();
			}

			String html_hover = "<p style=\"font-weight:bold;font-size:14px\">Station:"+rsu.getId()+"</p>";
			String idimg = station.concat("ipop");

			try {
				DatabaseDriver.executeQuery("Insert into lf_marker values('" + rsu.getId() + "'," + rsu.getLatitude()
						+ "," + rsu.getLongitude() + ",'" + htmlMarker + "','" + htmlPopup + "','RSU','" + html_hover
						+ "')");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		Timer t = new Timer();
		Date d = new Date();
		t.scheduleAtFixedRate(new GetData(), d, 40000);
	}

}
