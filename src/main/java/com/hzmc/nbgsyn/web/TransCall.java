package com.hzmc.nbgsyn.web;


import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.module.http.internal.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgsyn.service.CallService;
import com.hzmc.nbgsyn.service.ReceiveService;

/**
 * 主数据输到其他业务系统的入口
 * @author ZhuLi
 *
 */
public class TransCall implements Callable {

	@Autowired
	private ReceiveService receiveService;
	@Autowired
	private CallService callService;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		//判断类型
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		String str = map.get("jsonstr").trim();
		JSONObject jo = JSONObject.fromObject(str);
		String action = String.valueOf(jo.get("action"));
//		String table = jo.getString("root");
		JSONObject rj = new JSONObject();
		if (StringUtils.isEmpty(action)){
			return null;
		}
		switch (action) {
		case "SYN": //ctos等业务部门同步
			String type = jo.getString("type");
			String t =null;
			switch (type) {
			case "UPDATE":
				t = "U";
				jo.element("type", t);
				jo.element("action", "TRANSFOR");
				callService.transData(jo.toString());
				break;
			case "CREATE":
				t = "C";
				jo.element("type", t);
				jo.element("action", "TRANSFOR");
				callService.transData(jo.toString());
				break;
			case "DELETE":
				t = "D";
				jo.element("type", t);
				jo.element("action", "TRANSFOR");
				callService.transData(jo.toString());
				break;
			default:
				break;
			}
			break;
		case "TRANSFOR": //主数据同步
			rj = receiveService.daoSyn(str);
			break;
		default:
			break;
		}
		
		return rj;
	}
}
