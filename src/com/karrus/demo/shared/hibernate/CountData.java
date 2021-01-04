package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * CountData generated by hbm2java
 */
public class CountData implements java.io.Serializable {

	private CountDataId id;
	private Double occupancy;
	private Integer count;
	private Double speed;
	private Integer latency;

	public CountData() {
	}

	public CountData(CountDataId id) {
		this.id = id;
	}

	public CountData(CountDataId id, Double occupancy, Integer count, Double speed, Integer latency) {
		this.id = id;
		this.occupancy = occupancy;
		this.count = count;
		this.speed = speed;
		this.latency = latency;
	}

	public CountDataId getId() {
		return this.id;
	}

	public void setId(CountDataId id) {
		this.id = id;
	}

	public Double getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(Double occupancy) {
		this.occupancy = occupancy;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSpeed() {
		return this.speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Integer getLatency() {
		return this.latency;
	}

	public void setLatency(Integer latency) {
		this.latency = latency;
	}

}