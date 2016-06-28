package com.hzmc.nbgsyn.service;


import org.mule.api.MuleEventContext;

import net.sf.json.JSONObject;

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
	 * @param jsonstr
	 * @return
	 */
	public JSONObject transData(String jsonstr);
}
