package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * LfConf generated by hbm2java
 */
public class LfConf implements java.io.Serializable {

	private String var;
	private String value;

	public LfConf() {
	}

	public LfConf(String var) {
		this.var = var;
	}

	public LfConf(String var, String value) {
		this.var = var;
		this.value = value;
	}

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
