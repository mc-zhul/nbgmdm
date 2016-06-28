package com.hzmc.nbgsyn.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.hzmc.nbgsyn.business.DbManager;
import com.hzmc.nbgsyn.service.ReceiveService;

@Service
public class ReceiveServiceImpl implements ReceiveService {

	@Autowired
	DbManager dbManager;
	private static final Logger logger = Logger.getLogger(ReceiveServiceImpl.class);

	@Override
	public JSONObject daoData(String table, JSONObject data) {
		String type = data.getString("type");
		JSONObject rj =null;
		switch (type) {
		case "C":
			rj = dbManager.dataCreate(table, data);
			logger.info(table + "新增数据" + data);
			break;
		case "U":
			rj = dbManager.dataUpdate(table, data);
			logger.info(table + "更新数据" + data);
			break;
		default:
			break;
		}
		
		return rj;
	}

	@Override
	public JSONObject daoSyn(String str) {
		return dbManager.dataSyn(str);
	}

}
