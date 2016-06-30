package com.hzmc.nbgsyn.domain.persistence;

import java.io.Serializable;

import net.sf.json.JSONArray;

/**
 * bean of applyDate
 * 
 * @author tfche 2016年6月29日16:32:32
 */
public class ApplyDate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8587581076502496701L;

	/**
	 * 
	 */

	private String action;

	private String model;

	private String type;

	private String username;

	private String password;

	private String entity;

	private JSONArray data;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

}
