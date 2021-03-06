package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * CtIndivData generated by hbm2java
 */
public class CtIndivData implements java.io.Serializable {

	private CtIndivDataId id;
	private Double occupancy;
	private Double gap;
	private Double speed;
	private Double length;
	private Integer latency;

	public CtIndivData() {
	}

	public CtIndivData(CtIndivDataId id) {
		this.id = id;
	}

	public CtIndivData(CtIndivDataId id, Double occupancy, Double gap, Double speed, Double length, Integer latency) {
		this.id = id;
		this.occupancy = occupancy;
		this.gap = gap;
		this.speed = speed;
		this.length = length;
		this.latency = latency;
	}

	public CtIndivDataId getId() {
		return this.id;
	}

	public void setId(CtIndivDataId id) {
		this.id = id;
	}

	public Double getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(Double occupancy) {
		this.occupancy = occupancy;
	}

	public Double getGap() {
		return this.gap;
	}

	public void setGap(Double gap) {
		this.gap = gap;
	}

	public Double getSpeed() {
		return this.speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getLength() {
		return this.length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Integer getLatency() {
		return this.latency;
	}

	public void setLatency(Integer latency) {
		this.latency = latency;
	}

}
