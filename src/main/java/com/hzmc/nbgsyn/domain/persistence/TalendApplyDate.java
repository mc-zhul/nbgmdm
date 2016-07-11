package com.hzmc.nbgsyn.domain.persistence;

import java.io.Serializable;
import net.sf.json.JSONObject;

/**
 * bean of applyDate
 * 
 * @author tfche 2016年6月29日16:32:32
 */
public class TalendApplyDate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8587581076502496701L;

	/**
	 * 
	 */

	// 动作 register 和 transt
	private String action;
	// 模块
	private String model;
	// 类型 R U D C
	private String type;
	// Data model的entity名称
	private String entity;
	// 数据块
	private JSONObject applyData;

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

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public JSONObject getApplyData() {
		return applyData;
	}

	public void setApplyData(JSONObject applyData) {
		this.applyData = applyData;
	}

}
