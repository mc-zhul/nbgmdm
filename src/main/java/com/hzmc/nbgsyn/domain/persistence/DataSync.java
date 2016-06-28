package com.hzmc.nbgsyn.domain.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据同步json文本参数
 * 
 * @author fg
 *
 */
public class DataSync {

	private String operand;
	private String root;// 操作对象（数据表）
	private char type;// 操作类型（CUDR:增改删查）
	private String token;
	private Map<String, Object> applyData = new HashMap<String, Object>(0);// 参数
	private Map<String, Object> whereData = new HashMap<String, Object>(0);// where条件

	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public char getType() {
		return type;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, Object> getApplyData() {
		return applyData;
	}

	public void setApplyData(Map<String, Object> applyData) {
		this.applyData = applyData;
	}

	public Map<String, Object> getWhereData() {
		return whereData;
	}

	public void setWhereData(Map<String, Object> whereData) {
		this.whereData = whereData;
	}

}
