package com.hzmc.hzbicycle.domain.persistence;
/**
 * 员工考勤查询(POS机签到签退)
 * @author ZhuLi
 *
 */
public class AttendenceBean {
	private String net_uid;	//网点id
	private String subarea;	//片区
	private String area;	//区域
	private String net_name; //站点名称
	private String net_address; //站点地址
	private String car_id;
	private String sign_time;
	private String pos_id;
	
	public String getNet_uid() {
		return net_uid;
	}
	public void setNet_uid(String net_uid) {
		this.net_uid = net_uid;
	}
	public String getSubarea() {
		return subarea;
	}
	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getNet_name() {
		return net_name;
	}
	public void setNet_name(String net_name) {
		this.net_name = net_name;
	}
	public String getNet_address() {
		return net_address;
	}
	public void setNet_address(String net_address) {
		this.net_address = net_address;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getSign_time() {
		return sign_time;
	}
	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
}
