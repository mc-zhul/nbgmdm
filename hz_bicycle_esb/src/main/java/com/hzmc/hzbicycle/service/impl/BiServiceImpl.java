package com.hzmc.hzbicycle.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;





import com.hzmc.hzbicycle.business.AuthorityManager;
import com.hzmc.hzbicycle.business.BiManager;
import com.hzmc.hzbicycle.domain.persistence.StationBean;
import com.hzmc.hzbicycle.service.BiService;

public class BiServiceImpl implements BiService {

	@Autowired
	private BiManager biManager;
	
	@Autowired
	private AuthorityManager authorityManager;
	
	@Override
	public JSONObject getstationall(String area, long id) {
		//获取权限
		List<String> columnList = authorityManager.getColumnById(1, id);
//		long timePeriod = authorityManager.getTimeById(1, id);
//		if(timePeriod < time){
//			time = timePeriod;
//		}
		//查询列表
		List<StationBean> stationList = biManager.getStationAll(area);
		
		
		return null;
	}

	@Override
	public JSONObject getstationDetail(String net_id, long time, long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getReturnTatol(long time, long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getRentTatol(long time, long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getNearbyStations(String net_id, long time, long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
