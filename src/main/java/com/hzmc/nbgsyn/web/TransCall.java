package com.hzmc.nbgsyn.web;

import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.module.http.internal.ParameterMap;

import com.hzmc.nbgsyn.business.enums.MsgEnum;
import com.hzmc.nbgsyn.domain.persistence.ApplyDate;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;

/**
 * 主数据输到其他业务系统的入口
 * 
 * @author ZhuLi
 *
 */
public class TransCall implements Callable {

	// oncall 的restFull 请求
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// 判断类型
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		String jsonstr = map.get("jsonstr").trim();

		JSONObject jo = JSONObject.fromObject(jsonstr);
		ApplyDate applyDate = (ApplyDate) JSONObject.toBean(jo, ApplyDate.class);

		String action = applyDate.getAction();

		ResultBean resultBean = new ResultBean();
		resultBean.setMsgId(MsgEnum.SUCCESS.getMsgId());
		resultBean.setMsgId(MsgEnum.SUCCESS.getMsgDesc());

		if (StringUtils.isEmpty(action)) {
			resultBean.setMsgId(MsgEnum.PARAM_ERROR.getMsgId());
			resultBean.setMsgId(MsgEnum.PARAM_ERROR.getMsgDesc());
			// resultBean.setResult("applyDate's action is null!");
			return JSONObject.fromObject(resultBean);
		}

		// action router
		ApplyDate temp = (ApplyDate) BeanUtils.cloneBean(applyDate);
		temp.setAction("TRANSFOR");
		switch (action) {
			case "SYN": // ctos等业务部门同步 // 下发
				String type = applyDate.getType();
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
				break;
			default:
				break;
		}

		return JSONObject.fromObject(resultBean);
	}
}
