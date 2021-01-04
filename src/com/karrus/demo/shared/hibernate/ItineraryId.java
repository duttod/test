package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * ItineraryId generated by hbm2java
 */
public class ItineraryId implements java.io.Serializable {

	private String origin;
	private String destination;

	public ItineraryId() {
	}

	public ItineraryId(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
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
		if (!(other instanceof ItineraryId))
			return false;
		ItineraryId castOther = (ItineraryId) other;

		return ((this.getOrigin() == castOther.getOrigin()) || (this.getOrigin() != null
				&& castOther.getOrigin() != null && this.getOrigin().equals(castOther.getOrigin())))
				&& ((this.getDestination() == castOther.getDestination())
						|| (this.getDestination() != null && castOther.getDestination() != null
								&& this.getDestination().equals(castOther.getDestination())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrigin() == null ? 0 : this.getOrigin().hashCode());
		result = 37 * result + (getDestination() == null ? 0 : this.getDestination().hashCode());
		return result;
	}

}