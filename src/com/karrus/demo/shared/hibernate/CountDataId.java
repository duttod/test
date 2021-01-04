package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

import java.util.Date;

/**
 * CountDataId generated by hbm2java
 */
public class CountDataId implements java.io.Serializable {

	private Date timestamp;
	private String serial;
	private String lane;

	public CountDataId() {
	}

	public CountDataId(Date timestamp, String serial, String lane) {
		this.timestamp = timestamp;
		this.serial = serial;
		this.lane = lane;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getLane() {
		return this.lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CountDataId))
			return false;
		CountDataId castOther = (CountDataId) other;

		return ((this.getTimestamp() == castOther.getTimestamp()) || (this.getTimestamp() != null
				&& castOther.getTimestamp() != null && this.getTimestamp().equals(castOther.getTimestamp())))
				&& ((this.getSerial() == castOther.getSerial()) || (this.getSerial() != null
						&& castOther.getSerial() != null && this.getSerial().equals(castOther.getSerial())))
				&& ((this.getLane() == castOther.getLane()) || (this.getLane() != null && castOther.getLane() != null
						&& this.getLane().equals(castOther.getLane())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTimestamp() == null ? 0 : this.getTimestamp().hashCode());
		result = 37 * result + (getSerial() == null ? 0 : this.getSerial().hashCode());
		result = 37 * result + (getLane() == null ? 0 : this.getLane().hashCode());
		return result;
	}

}
