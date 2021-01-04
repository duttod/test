package com.karrus.demo.client;

import java.util.Date;

public class Features  {
	
	public static String formatDateV2x(Date date) {
		@SuppressWarnings("unused")
		String Weekday = date.toString().substring(0, 3);
		String Jour = date.toString().substring(8, 10);
		String Mois = date.toString().substring(4, 7);
		String Annee = date.toString().substring(27);
		String Heure = date.toString().substring(11, 19);

		return "" + Mois + "-" + Jour + "-" + Annee + " / " + Heure;
	}

	public static String formatDateTraffic(Date date) {

		String Hour = date.toString().substring(11, 19);

		return "Heure:"+ Hour;
	}
	
	public static String formatDateMeteo(Date date) {

		String Hour = date.toString().substring(11, 19);

		return "Heure:" + Hour;
	}
	
	public static String formatDateBluetooth(Date date) {
		
		if(date !=null) {
			String Hour = date.toString().substring(11, 19);

			return Hour;
		}else {
			return "No data";
		}
		
	}
	
	public static String formatDateUBR(Date date) {

		String Hour = date.toString().substring(11, 19);

		return "Heure: " + Hour;
	}
	
}
