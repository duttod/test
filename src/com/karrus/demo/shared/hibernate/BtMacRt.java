package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * BtMacRt generated by hbm2java
 */
public class BtMacRt implements java.io.Serializable {

	private BtMacRtId id;
	private String class_;
	private String station;

	public BtMacRt() {
	}

	public BtMacRt(BtMacRtId id, String station) {
		this.id = id;
		this.station = station;
	}

	public BtMacRt(BtMacRtId id, String class_, String station) {
		this.id = id;
		this.class_ = class_;
		this.station = station;
	}

	public BtMacRtId getId() {
		return this.id;
	}

	public void setId(BtMacRtId id) {
		this.id = id;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
