package com.hzmc.nbgsyn.web;


import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.module.http.internal.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgsyn.service.CallService;

/**
 * 主数据持久化的入口
 * 
 * @author ZhuLi
 *
 */
public class CrudCall implements Callable {

	@Autowired
	private CallService callService;

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// 判断类型
		JSONObject jo = null;
		JSONObject o = new JSONObject();
		;
		try {
			MuleMessage mes = eventContext.getMessage();
			ParameterMap map = (ParameterMap) (mes.getPayload());
			String str = map.get("jsonstr").trim();
			jo = JSONObject.fromObject(str);
		} catch (Exception e) {
			e.printStackTrace();
			o.accumulate("msgID", "0020");
			o.accumulate("msgDesc", "参数格式有错！请检查参数");
			return o;
		}

		// JSONObject jo = JSONObject.fromObject(str);
		String action = String.valueOf(jo.get("action"));
		if (StringUtils.isEmpty(action)) {
			return null;
		}
		
		switch (action) {
		case "SERVICE_REGISTER": // 注册
			o = callService.register(eventContext);
			break;
		case "token": // 身份
			o = callService.token(eventContext);
			break;
		case "TRANSFOR": // 主数据同步
			o = callService.transforToTalend(eventContext);
//			callService.transData(str);
			break;
		default:
			break;
		}
		return o;
	}

}
