package com.hzmc.nbgsyn.service;


import net.sf.json.JSONObject;

import org.mule.api.MuleEventContext;

import com.hzmc.nbgsyn.domain.persistence.ApplyDate;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;

public interface CallService {
	
	/**
	 * 注册
	 * @param eventContext
	 * @return
	 */
	public JSONObject register(MuleEventContext eventContext);

	/**
	 * 身份
	 * @param eventContext
	 * @return
	 */
	public JSONObject token(MuleEventContext eventContext);
	
	/**
	 * 传递数据
	 * @param eventContext
	 * @return
	 */
	public JSONObject transforToTalend(MuleEventContext eventContext);
	
	/**
	 * 持久化数据
	 * @param eventContext
	 * @return
	 */
	public JSONObject dbCall(MuleEventContext eventContext);

	/**
	 * 传输数据
	 * @param eventContext
	 * @return
	 */
	public JSONObject talendCall(String type, String model,String cluster,String xmls);

	/**
	 * 下发
	 * @param ApplyDate
	 * @return
	 */
	public JSONObject transData(ApplyDate applyDate);
	
	/**
	 * 调用talend同步
	 * @param applyDate
	 * @return
	 */
	public ResultBean saveToTalend(ApplyDate applyDate);
}
