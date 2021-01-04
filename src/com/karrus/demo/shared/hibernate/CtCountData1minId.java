package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

import java.io.Serializable;
import java.util.Date;

/**
 * CtCountData1minId generated by hbm2java
 */
public class CtCountData1minId implements java.io.Serializable {

	private Date timestamp;
	private String station;
	private String lane;
	private Double occupancy;
	private Long count;
	private Double speed;
	private Serializable latency;

	public CtCountData1minId() {
	}

	public CtCountData1minId(Date timestamp, String station, String lane, Double occupancy, Long count, Double speed,
			Serializable latency) {
		this.timestamp = timestamp;
		this.station = station;
		this.lane = lane;
		this.occupancy = occupancy;
		this.count = count;
		this.speed = speed;
		this.latency = latency;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getLane() {
		return this.lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public Double getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(Double occupancy) {
		this.occupancy = occupancy;
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Double getSpeed() {
		return this.speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Serializable getLatency() {
		return this.latency;
	}

	public void setLatency(Serializable latency) {
		this.latency = latency;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CtCountData1minId))
			return false;
		CtCountData1minId castOther = (CtCountData1minId) other;

		return ((this.getTimestamp() == castOther.getTimestamp()) || (this.getTimestamp() != null
				&& castOther.getTimestamp() != null && this.getTimestamp().equals(castOther.getTimestamp())))
				&& ((this.getStation() == castOther.getStation()) || (this.getStation() != null
						&& castOther.getStation() != null && this.getStation().equals(castOther.getStation())))
				&& ((this.getLane() == castOther.getLane()) || (this.getLane() != null && castOther.getLane() != null
						&& this.getLane().equals(castOther.getLane())))
				&& ((this.getOccupancy() == castOther.getOccupancy()) || (this.getOccupancy() != null
						&& castOther.getOccupancy() != null && this.getOccupancy().equals(castOther.getOccupancy())))
				&& ((this.getCount() == castOther.getCount()) || (this.getCount() != null
						&& castOther.getCount() != null && this.getCount().equals(castOther.getCount())))
				&& ((this.getSpeed() == castOther.getSpeed()) || (this.getSpeed() != null
						&& castOther.getSpeed() != null && this.getSpeed().equals(castOther.getSpeed())))
				&& ((this.getLatency() == castOther.getLatency()) || (this.getLatency() != null
						&& castOther.getLatency() != null && this.getLatency().equals(castOther.getLatency())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTimestamp() == null ? 0 : this.getTimestamp().hashCode());
		result = 37 * result + (getStation() == null ? 0 : this.getStation().hashCode());
		result = 37 * result + (getLane() == null ? 0 : this.getLane().hashCode());
		result = 37 * result + (getOccupancy() == null ? 0 : this.getOccupancy().hashCode());
		result = 37 * result + (getCount() == null ? 0 : this.getCount().hashCode());
		result = 37 * result + (getSpeed() == null ? 0 : this.getSpeed().hashCode());
		result = 37 * result + (getLatency() == null ? 0 : this.getLatency().hashCode());
		return result;
	}

}