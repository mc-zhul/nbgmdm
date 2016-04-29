package com.hzmc.hzbicycle.domain.persistence;

public class ReturnStationBean {
	private long net_uid;	//网点id
	private String subarea;	//片区
	private String area;	//区域
	private String net_name; //站点名称
	private String net_address; //站点地址
	private long longitud;	//经度
	private long latitude;	//纬度
	private long return_num; 	//租车量
	private long rank; 	//排名
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
	public long getReturn_num() {
		return return_num;
	}
	public void setReturn_num(long return_num) {
		this.return_num = return_num;
	}
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	
}
