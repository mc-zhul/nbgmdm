package com.hzmc.hzbicycle.domain.persistence;

/**
 * 自行车具体某一站点详细信息
 * @author ZhuLi
 *
 */
public class StationDetailBean {
	
	private long net_uid;	//网点id
	private String subarea;	//片区
	private String area;	//区域
	private String net_name; //站点名称
	private String net_address; //站点地址
	private long bike_num; 	//车位数
	private long longitud;	//经度
	private long latitude;	//纬度
	private long locks;		//锁止器
	private long free_locks; //锁止器空余数量
	private long rent_num;	//租车量
	private long restore_num;	//还车量	
	
	
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
	public long getLocks() {
		return locks;
	}
	public void setLocks(long locks) {
		this.locks = locks;
	}
	public long getFree_locks() {
		return free_locks;
	}
	public void setFree_locks(long free_locks) {
		this.free_locks = free_locks;
	}
	public long getRent_num() {
		return rent_num;
	}
	public void setRent_num(long rent_num) {
		this.rent_num = rent_num;
	}
	public long getRestore_num() {
		return restore_num;
	}
	public void setRestore_num(long restore_num) {
		this.restore_num = restore_num;
	}
	
}
