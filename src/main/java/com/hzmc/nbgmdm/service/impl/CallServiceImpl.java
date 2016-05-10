package com.hzmc.nbgmdm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.module.http.internal.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmc.nbgmdm.service.BiService;
import com.hzmc.nbgmdm.service.CallService;
import com.hzmc.nbgmdm.service.LogService;
import com.hzmc.nbgmdm.service.TokenData;
import com.hzmc.nbgmdm.util.AuthorityUtil;

@Service
public class CallServiceImpl implements CallService {

	@Autowired
	private BiService biService;
	@Autowired
	private LogService logservice;
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static boolean isSave;


	@Override
	public JSONObject mdCall(MuleEventContext eventContext, int type) {
		JSONObject reJson = new JSONObject();
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		long user_id = mes.getOutboundProperty("user_id");
		String time = map.get("time");
		String net_uid = map.get("net_uid");
		boolean can = true;
		String result = "0";// 查询状态
		// 判断权限
		List<String> cList = null;// 没有权限的字段
		List<String> fList;// 有权限的字段
		if (user_id < 0) {
			reJson.accumulate("0009", "用户不存在");
			result = "2";
			can = false;
		} else {
			Map<Long, List<String>> fMap = TokenData.fauthMap.get(user_id);
			Map<Long, List<String>> cMap = TokenData.authMap.get(user_id);
			if (null != cMap) {
				cList = cMap.get(Long.valueOf(type));
			}
			if (null != fMap) {
				fList = fMap.get(Long.valueOf(type));
			} else {
				fList = null;
			}
			if (null == fList || 0 == fList.size()) {
				reJson.accumulate("007", "没有接口权限");
				result = "3";
				can = false;
			}
		}
		// 获取时间权限
		long timeP = 0;
		try {
			timeP = TokenData.timeMap.get(user_id).get(Long.valueOf(type));
		} catch (Exception e1) {
			timeP = 0;
		}
		long r = logservice.checkPeriodFromCache(user_id, type, time);
		if (6 != type && 1 != type && r < 0) {
			reJson.accumulate("0004", "时间权限不足");
			result = "3";
			can = false;
		}
		long rows = TokenData.rows;
		try {
			rows = Long.valueOf(map.get("rows"));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		// 日志信息

		long start = new Date().getTime();
		if (can) {
			switch (type) {
			case 1:
				String area = map.get("area");
				if (null != area && !"".equals(area)) {
					reJson = biService.getPort(user_id, rows);
					result = "1";
				} else {
					reJson.accumulate("0001", "参数不全");
				}

				break;
			
			default:
				break;
			}
		}
		long end = new Date().getTime();
		long cost = end - start;
		// 插入日志
		AuthorityUtil.coulumfilter(reJson, cList);
		String re = reJson.toString();
		logservice.insertLog(user_id, type, cost, timeP, result, map.entrySet().toString(), re);
		
		return reJson;

	}

	/**
	 * 時間權限的確定
	 * 
	 * @param timeperiod
	 * @param startTime
	 * @return
	 */
	private boolean checkStartTime(long timeperiod, String startTime) {
		// 时间空的不在这里判断
		if (null == startTime) {
			return true;
		}
		long now = new Date().getTime();
		long p = 0;
		long min = 60 * 1000;
		long start = 0;
		if (0 == timeperiod) {
			p = now;
		} else if (5 == timeperiod) {
			p = now - 5 * min;
		} else if (10 == timeperiod) {
			p = now - 10 * min;
		} else if (30 == timeperiod) {
			p = now - 30 * min;
		} else if (60 == timeperiod) {
			p = now - 60 * min;
		} else if (24 == timeperiod) {
			p = now - 24 * 60 * min;
		} else if (7 == timeperiod) {
			p = now - 7 * 24 * 60 * min;
		} else if (365 == timeperiod) {
			p = now - 365 * 24 * 60 * min;
		}
		try {
			start = sFormat.parse(startTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (p > start) {
			return false;
		} else {
			return true;
		}
	}
}
