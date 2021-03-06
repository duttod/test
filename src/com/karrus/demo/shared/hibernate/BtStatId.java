package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

import java.util.Date;

/**
 * BtStatId generated by hbm2java
 */
public class BtStatId implements java.io.Serializable {

	private Date timestamp;
	private String origin;
	private String destination;

	public BtStatId() {
	}

	public BtStatId(Date timestamp, String origin, String destination) {
		this.timestamp = timestamp;
		this.origin = origin;
		this.destination = destination;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BtStatId))
			return false;
		BtStatId castOther = (BtStatId) other;

		return ((this.getTimestamp() == castOther.getTimestamp()) || (this.getTimestamp() != null
				&& castOther.getTimestamp() != null && this.getTimestamp().equals(castOther.getTimestamp())))
				&& ((this.getOrigin() == castOther.getOrigin()) || (this.getOrigin() != null
						&& castOther.getOrigin() != null && this.getOrigin().equals(castOther.getOrigin())))
				&& ((this.getDestination() == castOther.getDestination())
						|| (this.getDestination() != null && castOther.getDestination() != null
								&& this.getDestination().equals(castOther.getDestination())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTimestamp() == null ? 0 : this.getTimestamp().hashCode());
		result = 37 * result + (getOrigin() == null ? 0 : this.getOrigin().hashCode());
		result = 37 * result + (getDestination() == null ? 0 : this.getDestination().hashCode());
		return result;
	}

}
