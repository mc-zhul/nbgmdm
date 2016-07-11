package com.hzmc.nbgsyn.web;

import main.java.TalendWs;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.module.http.internal.ParameterMap;

import com.hzmc.nbgsyn.business.enums.MsgEnum;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;
import com.hzmc.nbgsyn.domain.persistence.TalendApplyDate;
import com.hzmc.nbgsyn.service.impl.HandOutServiceImpl;

/**
 * 主数据输到其他业务系统的入口
 * 
 * @author ZhuLi
 *
 */
public class TransCall implements Callable {

	private Logger logger = Logger.getLogger(TransCall.class);

	// oncall 的restFull 请求
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		TalendWs.method1();
		// 判断类型
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		String jsonstr = map.get("jsonstr").trim();

		JSONObject jo = JSONObject.fromObject(jsonstr);
		TalendApplyDate talendApplyDate = new TalendApplyDate();

		ResultBean resultBean = new ResultBean();
		resultBean.setMsgId(MsgEnum.SUCCESS.getMsgId());
		resultBean.setMsgDesc(MsgEnum.SUCCESS.getMsgDesc());
		try {
			talendApplyDate = (TalendApplyDate) JSONObject.toBean(jo, TalendApplyDate.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			resultBean.setMsgId(MsgEnum.FORMART_ERROR.getMsgId());
			resultBean.setMsgDesc(MsgEnum.FORMART_ERROR.getMsgDesc());
			return JSONObject.fromObject(resultBean).toString();
		}
		String action = talendApplyDate.getAction();
		// action router
		TalendApplyDate temp = (TalendApplyDate) BeanUtils.cloneBean(talendApplyDate);
		temp.setAction("TRANSFOR");
		// 下发
		if ("SYN".equals(action)) {
			String type = talendApplyDate.getType();
			switch (type) {
				case "UPDATE":
					temp.setType("U");
					break;
				case "CREATE":
					temp.setType("C");
					break;
				case "DELETE":
					temp.setType("D");
					break;
			}
			// String resStr = HandOutServiceImpl.callEDIWSB(url, operationname,
			// nameSpace, fromNode, esbID, applyData, userId, password);
		} else {
			resultBean.setMsgId(MsgEnum.PARAM_ERROR.getMsgId());
			resultBean.setMsgId(MsgEnum.PARAM_ERROR.getMsgDesc());
			// resultBean.setResult("applyDate's action is null!");
		}

		System.out.println(JSONObject.fromObject(resultBean));
		return JSONObject.fromObject(resultBean);
	}
}
