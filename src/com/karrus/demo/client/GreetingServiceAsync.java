package com.karrus.demo.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
 * Async RPC service.
 */
public interface GreetingServiceAsync {

	void getAllEquipement( AsyncCallback<ArrayList<LfEquipement>> asyncCallback);
	

	//void getUbrStatement(String id, AsyncCallback<List<LfFonction>> asyncCallback);

	void getAllFonction( AsyncCallback<List<LfFonction>> asyncCallback);

	void getBluetooth(String id, AsyncCallback<List<LfFonction>> asyncCallback);

	void getTraffic(String id, AsyncCallback<List<LfFonction>> asyncCallback);

	//void getMeteo(String id, AsyncCallback<List<LfFonction>> asyncCallback);
	
	void getMapURL(AsyncCallback<List<LfConf>> asyncCallback) ;
	
//	void getAllPmv(AsyncCallback<List<Pmv>> asyncCallback) throws Exception;
//	
//	void getAllCamera(AsyncCallback<List<Camera>> asyncCallback) throws Exception;
	
	
	void  getMarker(String id,AsyncCallback<List<LfMarker>> asyncCallback)  ;
	
	//void get10lastMinTraffic(String id , AsyncCallback<List<CtIndivData>> asyncCallback);
	
//	void getV2x(String id, AsyncCallback<List<LfFonction>> asyncCallback);
//
//	void getLanes(String id ,AsyncCallback<List<Station>> asyncCallback) ;
//	
//	void setViewOnSearch(String text, AsyncCallback<List<RsuStation>> asyncCallback);

	//void getPolylines(AsyncCallback<List<LfPolyline>> asyncCallback);

	void getEtatBluetooth(AsyncCallback<List<BtBox>> asyncCallback) ;
	
	//void getAvgTravelTime(String origin, String dest, AsyncCallback<List<BtItt>> asyncCallback);

	//void getScales( AsyncCallback<List<TtItinerary>> asyncCallback);
	
}
