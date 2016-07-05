package com.hzmc.nbgsyn.service.impl;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.module.http.internal.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.RegisterManager;
import com.hzmc.nbgsyn.domain.persistence.ApplyDate;
import com.hzmc.nbgsyn.domain.persistence.RegisterBean;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;
import com.hzmc.nbgsyn.service.CallService;
import com.hzmc.nbgsyn.service.LogService;
import com.hzmc.nbgsyn.service.TalendService;
import com.hzmc.nbgsyn.util.DataSyncManager;

@Service
public class CallServiceImpl implements CallService {

	@Autowired
	private RegisterManager registerManager;
	@Autowired
	private LogService logservice;
	@Autowired
	private TalendService talendService;
	private static final Logger logger = Logger.getLogger(CallServiceImpl.class);

	// private static SimpleDateFormat sFormat = new
	// SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public JSONObject register(MuleEventContext eventContext) {
		JSONObject reJson = new JSONObject();
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		String str = map.get("jsonstr").trim();
		JSONObject jo = JSONObject.fromObject(str);
		JSONObject data = JSONObject.fromObject(jo.get("applyData"));
		String type = String.valueOf(jo.get("type"));
		RegisterBean rb = new RegisterBean();
		rb.setMd_code(data.getString("MD_CODE"));
		rb.setSys_code(data.getString("SYS_CODE"));
		rb.setInterface_code(data.getString("INTERFACE_CODE"));
		rb.setService_url(data.getString("SERVICE_URL"));
		rb.setUsername(data.getString("username"));
		rb.setPassword(data.getString("password"));
		switch (type) {
			case "C":
				registerManager.registerC(rb);
				break;
			case "D":
				registerManager.registerD(rb);
				break;
			case "U":
				registerManager.registerU(rb);
				break;
			default:
				break;
		}

		return reJson;

	}

	@Override
	public JSONObject token(MuleEventContext eventContext) {
		return null;
	}

	@Override
	public JSONObject transforToTalend(MuleEventContext eventContext) {
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		// dbCall(eventContext);

		String str = map.get("jsonstr");
		JSONObject jo = JSONObject.fromObject(str);

		String data = String.valueOf(jo.get("applyData"));
		String operand = String.valueOf(jo.get("operand"));
		String type = String.valueOf(jo.get("type"));
		String root = String.valueOf(jo.get("root"));
		JSONObject j = null;
//		j = toTalend(root, type, operand, operand, data);
		// dbCall(eventContext);
		//
		// RegisterBean rb = new RegisterBean();
		// rb.setInterface_code(operand);
		// List<RegisterBean> rgList = registerManager.registerList(rb);
		// for(RegisterBean rbs : rgList){
		// String serviceUrl = rbs.getService_url();
		// String un = rbs.getUsername();
		// String pw = rbs.getPassword();
		// JSONObject jo1 = new JSONObject();
		// //
		// jo1.accumulate("operand",operand);
		// jo1.accumulate("type",type);
		// jo1.accumulate("username",un);
		// jo1.accumulate("password",pw);
		// jo1.accumulate("applyData", data);
		// String url = "";
		// url = serviceUrl + "?jsonstr={" + data + "}";
		// HttpClient client = new HttpClient();
		// HttpMethod method = new GetMethod(url);
		// try {
		// client.executeMethod(method);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

		return j;
	}

	@Override
	public JSONObject dbCall(MuleEventContext eventContext) {
		MuleMessage mes = eventContext.getMessage();
		ParameterMap map = (ParameterMap) (mes.getPayload());
		String str = map.get("jsonstr").trim();
		DataSyncManager dsm = new DataSyncManager();
		// 
		String r = dsm.dataSync(str);
		return JSONObject.fromObject(r);
	}

	/**
	 * 
	 * @param entity
	 *            entity(ex：RD_NATIONALITY)
	 * @param type
	 *            CRUD
	 * @param model
	 *            MDM_NBG
	 * @param cluster
	 *            和model一样 null时取model值
	 * @param data
	 *            数据值
	 * @return
	 */
	private JSONObject toTalend(String entity, String type, String model, String cluster, JSONArray dataArray) {
		cluster = cluster == null ? model : cluster;
		// 生成entity
		Element root = DocumentHelper.createElement(entity);
		Document document = DocumentHelper.createDocument(root);
		// JSONArray ja = JSONArray.fromObject(data);
		// try {
		// Collection<?> joList = JSONArray.toCollection(ja);
		// Object[] oList = joList.toArray();
		// System.out.println(oList.length);
		// for(Object o :oList){
		//
		// JSONObject jo = JSONObject.fromObject(o);
		// Iterator it = jo.keys();
		// while (it.hasNext()) {
		// String k = it.next().toString();
		// root.addElement(k).addText(jo.getString(k));
		// }
		// System.out.println(o);
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 改成ja 遍历
		JSONObject jo = JSONObject.fromObject(dataArray);
		Iterator it = jo.keys();
		while (it.hasNext()) {
			String k = it.next().toString();
			root.addElement(k).addText(jo.getString(k));
		}
		String text = document.getRootElement().asXML();
		JSONObject j = new JSONObject();
		if ("U".equals(type)) {
//			 talendService.talendSaveOrUpdateWS(type, model, cluster, text);
		} else if ("C".equals(type)) {
//			j = talendService.talendWS(type, model, cluster, text);
		}
		logger.info("talend 同步数据，model：" + model + "操作类型：" + type + ",data:" + dataArray.toString());
		return j;
	}

	@Override
	public JSONObject talendCall(String type, String model, String cluster, String xmls) {
		return null;
	}

	@Override
	public JSONObject transData(ApplyDate applyDate) {
		String serviceUrl = "http://localhost:8082/nbgsyn/trans";
		GetMethod method = null;
		try {
			String url = "/nbgsyn/trans";
			String host = "127.0.0.1";
			// jsonstr = URLEncoder.encode(jsonstr, "utf-8");
			// String param = "jsonstr=" + jsonstr;
			HttpClient httpClient = new HttpClient();
			httpClient.getHostConfiguration().setHost(host, 8082, "http");
			// method = new GetMethod(url + "?" + param);
			httpClient.executeMethod(method);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		return null;
	}

	/**
	 * 保存数据到talend
	 */
	@Override
	public ResultBean saveToTalend(ApplyDate applyDate) {
		// TODO Auto-generated method stub
		return null;
	}
}
