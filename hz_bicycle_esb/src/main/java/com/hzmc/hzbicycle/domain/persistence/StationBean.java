package com.hzmc.hzbicycle.domain.persistence;
/**
 * 自行车所有站点信息
 * @author ZhuLi
 *
 */
public class StationBean {
	
	private long net_uid;	//网点id
	private String subarea;	//片区
	private String area;	//区域
	private String net_name; //站点名称
	private String net_address; //站点地址
	private long bike_num; 	//车位数
	private long longitud;	//经度
	private long latitude;	//纬度
	
	public long getNet_uid() {
		return net_uid;
	}
	public void setNet_uid(long net_uid) {
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
	public long getBike_num() {
		return bike_num;
	}
	public void setBike_num(long bike_num) {
		this.bike_num = bike_num;
	}
	public long getLongitud() {
		return longitud;
	}
	public void setLongitud(long longitud) {
		this.longitud = longitud;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	
}
