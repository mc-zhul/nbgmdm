package com.hzmc.hzbicycle.domain.persistence;

/**
 * 车辆最后状态
 * @author ZhuLi
 *
 */
public class CarStatusBean {
	private String Car_id;
	private String  status;
	private String  time;
	private String card_id;
	private String net_uid;
	private String subarea;
	private String area;
	private String net_name;
	private String net_address;
	private String car_num;
	public String getCar_id() {
		return Car_id;
	}
	public void setCar_id(String car_id) {
		Car_id = car_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
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
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	
	
}
