package com.hzmc.nbgsyn.domain.persistence;

/**
 * 系统操作日志
 * @author zhul
 *
 */
public class LogBean {
	private String stauts; //查询结果： 1：成功，0：失败
	private String sys_code;
	private String params; //查询条件
	private String result; //返回结果
	private String md_code;
	private String entity_code;
	private String operate_time; //操作时间
	
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMd_code() {
		return md_code;
	}
	public void setMd_code(String md_code) {
		this.md_code = md_code;
	}
	public String getEntity_code() {
		return entity_code;
	}
	public void setEntity_code(String entity_code) {
		this.entity_code = entity_code;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	
}
