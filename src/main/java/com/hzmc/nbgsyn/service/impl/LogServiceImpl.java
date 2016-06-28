package com.hzmc.nbgsyn.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.GenerateManager;
import com.hzmc.nbgsyn.domain.persistence.SearchLog;
import com.hzmc.nbgsyn.service.LogService;
import com.hzmc.nbgsyn.service.TokenData;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private GenerateManager generateManager;

	@Override
	public void insertLog(long userid, int sourceid, long costtime, long period, String reslut, String params,String result) {
//		if (null == TokenData.logList) {
//			TokenData.logList = new ArrayList<>();
//		}
		SearchLog log = new SearchLog();
		log.setUser_id(userid);
		log.setOp_time(new Date());
		log.setSource_id(sourceid);
		log.setCost_time(costtime);
		log.setSearch_period(period);
		log.setResult_status(reslut);
		log.setParams(params);
		log.setResult(result);
		try {
			TokenData.logList.put(log);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		if (TokenData.logList.size() > 0) {
//			generateManager.insertLog(TokenData.logList);
//			TokenData.logList.clear();
//		}
	}

	@Override
	public long checkPeriodFromCache(long timeP, int type, String time) {
		long r = 0;
		if (null == time) {
			r = -1;
		} else {
			try {
				long t = Long.valueOf(time);
				if (timeP < t) {
					r = -3;
				} else {
					r = t;
				}
			} catch (Exception e) {
				r = 0;
			}
		}
		return r;
	}

	@Override
	public long checkPeriod(long user_id, int type, String starttime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long checkPeriod(long user_id, int type, long peroid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
