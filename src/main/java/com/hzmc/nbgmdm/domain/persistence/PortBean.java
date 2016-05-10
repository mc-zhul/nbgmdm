package com.hzmc.nbgmdm.domain.persistence;
/**
 * 港口
 * @author ZhuLi
 *
 */
public class PortBean {
	
	private long port_id;	
	private String port_code;	
	private String port_cname;	
	private String port_ename; 
	private String port_short_cname;
	private String port_country_code; 	
	private String port_un_code;	
	private String port_navigation_area;	
	private String port_active_flag;	
	private String port_cn_flag;
	
	public long getPort_id() {
		return port_id;
	}
	public void setPort_id(long port_id) {
		this.port_id = port_id;
	}
	public String getPort_code() {
		return port_code;
	}
	public void setPort_code(String port_code) {
		this.port_code = port_code;
	}
	public String getPort_cname() {
		return port_cname;
	}
	public void setPort_cname(String port_cname) {
		this.port_cname = port_cname;
	}
	public String getPort_ename() {
		return port_ename;
	}
	public void setPort_ename(String port_ename) {
		this.port_ename = port_ename;
	}
	public String getPort_short_cname() {
		return port_short_cname;
	}
	public void setPort_short_cname(String port_short_cname) {
		this.port_short_cname = port_short_cname;
	}
	public String getPort_country_code() {
		return port_country_code;
	}
	public void setPort_country_code(String port_country_code) {
		this.port_country_code = port_country_code;
	}
	public String getPort_un_code() {
		return port_un_code;
	}
	public void setPort_un_code(String port_un_code) {
		this.port_un_code = port_un_code;
	}
	public String getPort_navigation_area() {
		return port_navigation_area;
	}
	public void setPort_navigation_area(String port_navigation_area) {
		this.port_navigation_area = port_navigation_area;
	}
	public String getPort_active_flag() {
		return port_active_flag;
	}
	public void setPort_active_flag(String port_active_flag) {
		this.port_active_flag = port_active_flag;
	}
	public String getPort_cn_flag() {
		return port_cn_flag;
	}
	public void setPort_cn_flag(String port_cn_flag) {
		this.port_cn_flag = port_cn_flag;
	}	
}
