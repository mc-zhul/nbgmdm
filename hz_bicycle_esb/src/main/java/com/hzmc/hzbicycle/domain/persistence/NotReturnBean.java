package com.hzmc.hzbicycle.domain.persistence;

import java.util.Date;

public class NotReturnBean {
	private long bike_id;
	private Date rent_time;
	private Date xj_time;
	
	public long getBike_id() {
		return bike_id;
	}
	public void setBike_id(long bike_id) {
		this.bike_id = bike_id;
	}
	public Date getRent_time() {
		return rent_time;
	}
	public void setRent_time(Date rent_time) {
		this.rent_time = rent_time;
	}
	public Date getXj_time() {
		return xj_time;
	}
	public void setXj_time(Date xj_time) {
		this.xj_time = xj_time;
	}
}
