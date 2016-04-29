package com.hzmc.hzbicycle.domain.persistence;

public class RentTotalBean {
		private long net_uid;	//网点id
		private String subarea;	//片区
		private String area;	//区域
		private String net_name; //站点名称
		private String net_address; //站点地址
		private long rent_num; //租车辆
		
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
		public long getRent_num() {
			return rent_num;
		}
		public void setRent_num(long rent_num) {
			this.rent_num = rent_num;
		}
		
}
