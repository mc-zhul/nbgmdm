package com.hzmc.nbgsyn.domain.persistence;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * 注册
 * 
 * @author ZhuLi
 *
 */
public class RegisterBean {

	private String md_code;
	private String sys_code;
	private String interface_code;
	private String service_url;
	private String username;
	private String password;


	public String getMd_code() {
		return md_code;
	}

	public void setMd_code(String md_code) {
		this.md_code = md_code;
	}

	public String getSys_code() {
		return sys_code;
	}

	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}

	public String getInterface_code() {
		return interface_code;
	}

	public void setInterface_code(String interface_code) {
		this.interface_code = interface_code;
	}

	public String getService_url() {
		return service_url;
	}

	public void setService_url(String service_url) {
		this.service_url = service_url;
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

}
