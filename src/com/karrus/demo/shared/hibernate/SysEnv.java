package com.karrus.demo.shared.hibernate;
// Generated 14 janv. 2020 17:44:50 by Hibernate Tools 5.4.7.Final

/**
 * SysEnv generated by hbm2java
 */
public class SysEnv implements java.io.Serializable {

	private String var;
	private String content;

	public SysEnv() {
	}

	public SysEnv(String var) {
		this.var = var;
	}

	public SysEnv(String var, String content) {
		this.var = var;
		this.content = content;
	}

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
