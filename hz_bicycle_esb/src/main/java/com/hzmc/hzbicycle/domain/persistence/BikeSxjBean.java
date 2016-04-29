package com.hzmc.hzbicycle.domain.persistence;

/**
 * 车辆上下架
 * @author ZhuLi
 *
 */
public class BikeSxjBean {
	private String net_uid;	//网点id
	private String area;	//区域
	private String net_name; //站点名称
	private String net_address; //站点地址
	private String time; //上下架时间
	private String card_id;//员工卡号
	private String car_num;//车位号
	private String car_id;//车号
	private String type;//類型
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getNet_uid() {
		return net_uid;
	}
	public void setNet_uid(String net_uid) {
		this.net_uid = net_uid;
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
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
}
