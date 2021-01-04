package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

import java.util.Date;

/**
 * BtBox generated by hbm2java
 */
public class BtBox implements java.io.Serializable {

	private BtBoxId id;
	private Date installationDate;
	private Boolean enabled;
	private String ip;
	private String ipVpn;
	private Date ipLastSeen;
	private Date ipVpnLastSeen;
	private String comment;
	private Double longitude;
	private Double latitude;

	public BtBox() {
	}

	public BtBox(BtBoxId id) {
		this.id = id;
	}

	public BtBox(BtBoxId id, Date installationDate, Boolean enabled, String ip, String ipVpn, Date ipLastSeen,
			Date ipVpnLastSeen, String comment, Double longitude, Double latitude) {
		this.id = id;
		this.installationDate = installationDate;
		this.enabled = enabled;
		this.ip = ip;
		this.ipVpn = ipVpn;
		this.ipLastSeen = ipLastSeen;
		this.ipVpnLastSeen = ipVpnLastSeen;
		this.comment = comment;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public BtBoxId getId() {
		return this.id;
	}

	public void setId(BtBoxId id) {
		this.id = id;
	}

	public Date getInstallationDate() {
		return this.installationDate;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpVpn() {
		return this.ipVpn;
	}

	public void setIpVpn(String ipVpn) {
		this.ipVpn = ipVpn;
	}

	public Date getIpLastSeen() {
		return this.ipLastSeen;
	}

	public void setIpLastSeen(Date ipLastSeen) {
		this.ipLastSeen = ipLastSeen;
	}

	public Date getIpVpnLastSeen() {
		return this.ipVpnLastSeen;
	}

	public void setIpVpnLastSeen(Date ipVpnLastSeen) {
		this.ipVpnLastSeen = ipVpnLastSeen;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
