package com.hzmc.nbgmdm.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmc.nbgmdm.business.AuthorityManager;
import com.hzmc.nbgmdm.business.BiManager;
import com.hzmc.nbgmdm.domain.persistence.StationBean;
import com.hzmc.nbgmdm.service.BiService;

@Service
public class BiServiceImpl implements BiService {

	@Autowired
	private BiManager biManager;

	@Autowired
	private AuthorityManager authorityManager;

	@Override
	public JSONObject getStation(String area, long user_id, long rows) {
		// 查询列表
		List<StationBean> stationList = biManager.getStationAll(area, rows);
		JSONObject reJson = new JSONObject();
		JSONArray array = JSONArray.fromObject(stationList);
		reJson.accumulate("count", array.size());
		reJson.accumulate("list", array);

		return reJson;
	}

}
