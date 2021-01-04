package com.karrus.demo.server;

import com.karrus.demo.client.GreetingService;

import com.karrus.demo.shared.FieldVerifier;
import com.karrus.demo.shared.hibernate.BtBox;
import com.karrus.demo.shared.hibernate.BtItt;
import com.karrus.demo.shared.hibernate.CtIndivData;
 import com.karrus.demo.shared.hibernate.LfConf;
import com.karrus.demo.shared.hibernate.LfMarker;
import com.karrus.demo.shared.hibernate.LfEquipement;


import com.karrus.demo.shared.hibernate.Station;

import com.karrus.demo.shared.hibernate.LfFonction;

import com.karrus.demo.server.database.DatabaseDriver;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server-side implementation of the RPC fonction.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	public LfConf config;

	

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		

		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<LfEquipement> getAllEquipement() throws Exception {
		return  (ArrayList<LfEquipement>) DatabaseDriver.get("select * from lf_equipement ;", LfEquipement.class);	
	}
	
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LfFonction> getUbrStatement(String CityName) throws Exception {
//		
//		return  (List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"+CityName+"' and fonction='diag';", LfFonction.class);
//	}
	
	@SuppressWarnings("unchecked")
	public List<LfFonction> getAllFonction() throws Exception {
		
		return  (List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction ;", LfFonction.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LfConf> getMapURL() throws Exception {
		// TODO Auto-generated method stub
		return  (List<LfConf>) DatabaseDriver.get("Select * from lf_conf where var='lf_geoserver_url' ;", LfConf.class);
	}
		
	
	@SuppressWarnings("unchecked")
	public List<LfMarker> getMarker(String id)  throws Exception {
		return (List<LfMarker>) DatabaseDriver.get("Select * from lf_marker where id='"+id+"';", LfMarker.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LfFonction> getBluetooth(String CityName) throws Exception {
		
			return  (List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"+CityName+"' and fonction='bluetooth';", LfFonction.class);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LfFonction> getTraffic(String CityName) throws Exception {
		
		return  (List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"+CityName+"' and fonction='traffic';", LfFonction.class);

	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CtIndivData> get10lastMinTraffic(String CityName) throws Exception {
//		
//			return  (List<CtIndivData>) DatabaseDriver.get("SELECT * FROM ct_indiv_data WHERE station= \'"+ CityName +"\' AND timestamp > current_timestamp - INTERVAL '10 minutes' ORDER BY timestamp DESC;", CtIndivData.class);
//
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Station> getLanes(String CityName) throws Exception {
//		
//			return  (List<Station>) DatabaseDriver.get("SELECT * FROM station WHERE id= \'"+ CityName +"\' ;", Station.class);
//
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LfFonction> getMeteo(String CityName) throws Exception {
//		return  (List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"+CityName+"' and fonction='meteo';", LfFonction.class);
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BtBox> getEtatBluetooth() throws Exception {
			return  (List<BtBox>) DatabaseDriver.get("SELECT * FROM tt_bt_box ORDER by site  ;", BtBox.class);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LfFonction> getV2x(String CityName) throws Exception {
//		
//			return  (List<LfFonction>) DatabaseDriver.get("Select * from lf_fonction where id='"+CityName+"' and fonction='v2x';", LfFonction.class);
//
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<RsuStation> setViewOnSearch(String CityName) throws Exception {
//		return  (List<RsuStation>) DatabaseDriver.get("SELECT * FROM rsu_station WHERE id= \'"+ CityName +"\' ;", RsuStation.class);
//		
//	}
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LfPolyline> getPolylines() throws Exception {
//		return  (List<LfPolyline>) DatabaseDriver.get("SELECT * FROM lf_polyline  ;", LfPolyline.class);
//		
//	}
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TtItinerary> getScales() throws Exception {
//		return  (List<TtItinerary>) DatabaseDriver.get("select * from tt_itinerary ;", TtItinerary.class);
//		
//	}
	
//	public List<Pmv> getAllPmv() throws Exception{
//		return (List<Pmv>) DatabaseDriver.get("select * from pmv ;", Pmv.class);
//	}
	
//	public List<Camera> getAllCamera() throws Exception{
//		return (List<Camera>) DatabaseDriver.get("select * from camera ;", Camera.class);
//	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<BtItt> getAvgTravelTime(String origin, String dest) throws Exception {
//		return (List<BtItt>)  DatabaseDriver.get("select * from tt_bt_itt where origin=\'" + origin +"\' and destination=\'" + dest + "\' and timestamp>current_timestamp - interval '15 minutes';", BtItt.class);
//		
//	}

	
}
