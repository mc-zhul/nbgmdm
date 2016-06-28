package com.hzmc.nbgsyn.domain.persistence;

import java.util.Date;

public class SearchLog {
	private long user_id;
	private Date op_time;
	private int source_id;
	private long cost_time;
	private long search_period;
	private String result_status;
	private String params;
	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Date getOp_time() {
		return op_time;
	}
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public long getCost_time() {
		return cost_time;
	}
	public void setCost_time(long cost_time) {
		this.cost_time = cost_time;
	}
	public long getSearch_period() {
		return search_period;
	}
	public void setSearch_period(long search_period) {
		this.search_period = search_period;
	}
	public String getResult_status() {
		return result_status;
	}
	public void setResult_status(String result_status) {
		this.result_status = result_status;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
