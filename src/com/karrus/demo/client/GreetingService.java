package com.karrus.demo.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.karrus.demo.shared.hibernate.BtBox;
import com.karrus.demo.shared.hibernate.LfConf;
//import com.karrus.demo.shared.hibernate.Camera;
//import com.karrus.demo.shared.hibernate.CtLane;
//import com.karrus.demo.shared.hibernate.Pmv;
//import com.karrus.demo.shared.hibernate.RsuStation;
import com.karrus.demo.shared.hibernate.LfEquipement;
import com.karrus.demo.shared.hibernate.LfFonction;
import com.karrus.demo.shared.hibernate.LfMarker;


/**
 * Client-side RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	ArrayList<LfEquipement> getAllEquipement() throws Exception;

//	List<LfFonction> getUbrStatement(String CityName) throws Exception;
	
	List<LfFonction> getAllFonction() throws Exception;

	List<LfConf> getMapURL() throws Exception;

	List<LfFonction> getBluetooth(String id) throws Exception;

	List<LfFonction> getTraffic(String id) throws Exception;
	
//	List<Pmv> getAllPmv() throws Exception;
//	
//	List<Camera> getAllCamera() throws Exception;
	
	
	
	//List<CtIndivData> get10lastMinTraffic(String id)  throws Exception;
//	
//	List<Station> getLanes(String id) throws Exception;
//	List<RsuStation> setViewOnSearch(String text) throws Exception;
//	
	List<LfMarker> getMarker(String id)  throws Exception;
//	
//	List<LfFonction> getMeteo(String id) throws Exception;
//
//	List<LfFonction> getV2x(String id) throws Exception;

//	List<LfPolyline> getPolylines() throws Exception;
	
	List<BtBox> getEtatBluetooth() throws Exception;

//	List<BtItt> getAvgTravelTime(String origin, String dest ) throws Exception;

//	List<TtItinerary> getScales() throws Exception;

}
