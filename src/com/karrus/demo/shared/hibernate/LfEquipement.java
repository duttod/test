package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * LfEquipement generated by hbm2java
 */
public class LfEquipement implements java.io.Serializable {

	private String id;
	private Double longitude;
	private Double latitude;

	public LfEquipement() {
	}

	public LfEquipement(String id) {
		this.id = id;
	}

	public LfEquipement(String id, Double longitude, Double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}