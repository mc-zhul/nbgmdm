package com.hzmc.nbgsyn.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.BindingProvider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.Constant;
import com.hzmc.nbgsyn.business.enums.MsgEnum;
import com.hzmc.nbgsyn.business.exception.TalendException;
import com.hzmc.nbgsyn.domain.persistence.ApplyDate;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;
import com.hzmc.nbgsyn.domain.persistence.ResultInfo;
import com.hzmc.nbgsyn.service.TalendService;
import com.mchz.nbg.talendservice.TMDMService;
import com.mchz.nbg.talendservice.TMDMService_Service;
import com.mchz.nbg.talendservice.WSDataClusterPK;
import com.mchz.nbg.talendservice.WSDataModelPK;
import com.mchz.nbg.talendservice.WSPutItem;
import com.mchz.nbg.talendservice.WSPutItemWithReport;

@Service
public class TalendServiceImpl implements TalendService {

	private Logger log = Logger.getLogger(TalendServiceImpl.class);

	public static void main(String[] args) throws TalendException {
		TalendServiceImpl impl = new TalendServiceImpl();
		String s = impl
				.talendSaveOrUpdateWS(
						"C",
						"MDM_NBG",
						"MDM_NBG",
						"<RD_NATIONALITY><NATIONALITY_ACTIVE_FLAG>Y</NATIONALITY_ACTIVE_FLAG><NATIONALITY_CNAME>中国</NATIONALITY_CNAME><NATIONALITY_CODE>CNH</NATIONALITY_CODE><NATIONALITY_ENAME>china</NATIONALITY_ENAME></RD_NATIONALITY>");
		System.out.println(s);
	}

	/**
	 * 
	 * @param type
	 * @param model
	 *            entity
	 * @param cluster
	 *            entity
	 * @param xmls
	 * @return
	 * @throws TalendException
	 */
	public String talendSaveOrUpdateWS(String type, String model, String cluster, String xmls) throws TalendException {
		type = "C";
		model = "MDM_NBG";
		cluster = model;
		xmls = "<RD_NATIONALITY><NATIONALITY_ACTIVE_FLAG>Y</NATIONALITY_ACTIVE_FLAG><NATIONALITY_CNAME>中国</NATIONALITY_CNAME><NATIONALITY_CODE>CNH</NATIONALITY_CODE><NATIONALITY_ENAME>china</NATIONALITY_ENAME></RD_NATIONALITY>";
		// final String WS_URL = Constant.WS_URL;
		final String S_URL = Constant.S_URL;
		String rtnMessage = "";
		try {
			TMDMService_Service tws = new TMDMService_Service();
			TMDMService port = tws.getTMDMPort();
			BindingProvider bp = (BindingProvider) port;

			bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "administrator");
			bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "administrator");
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, S_URL);
			WSPutItem item = new WSPutItem();
			WSDataModelPK dp = new WSDataModelPK();
			WSDataClusterPK dc = new WSDataClusterPK();
			dc.setPk(cluster);
			dp.setPk(model);
			item.setWsDataModelPK(dp);
			item.setWsDataClusterPK(dc);
			if ("U".equals(type)) {
				item.setIsUpdate(true);
			} else {
				item.setIsUpdate(false);
			}
			item.setXmlString(xmls);
			WSPutItemWithReport itemrp = new WSPutItemWithReport();
			itemrp.setSource("cbos-call");
			itemrp.setWsPutItem(item);
			itemrp.setInvokeBeforeSaving(true);

			rtnMessage = port.putItemWithReport(itemrp).getIds().toString();
		} catch (Exception e) {
			e.printStackTrace();
			// 扔出异常
			log.error(e);
			throw new TalendException("talend调用错误" + e.getMessage());
		}

		return rtnMessage;
	}

	@Override
	public ResultBean saveApplyDate(ApplyDate applyDate) {
		// TODO Auto-generated method stub
		return toTalend(applyDate, "C");
	}

	@Override
	public ResultBean removeApplyDate(ApplyDate applyDate) {
		// TODO Auto-generated method stub
		return toTalend(applyDate, "D");
	}

	@Override
	public ResultBean updateApplyDate(ApplyDate applyDate) {
		// TODO Auto-generated method stub
		return toTalend(applyDate, "U");
	}

	@Override
	public ResultBean findApplyDate(ApplyDate applyDate) {
		// TODO Auto-generated method stub
		return toTalend(applyDate, "R");
	}

	private ResultBean toTalend(ApplyDate applyDate, String type) {
		ResultBean res = new ResultBean(MsgEnum.SUCCESS.getMsgId(), MsgEnum.SUCCESS.getMsgDesc());

		String entity = applyDate.getEntity();
		String inType = type;
		String model = applyDate.getModel();
		String cluster = model;

		// 数据的array
		JSONArray jsonArray = applyDate.getData();
		// 对应每一个data的返回信息
		List<ResultInfo> resultInfos = new ArrayList<ResultInfo>();
		// 遍历数据
		for (int i = 0; i < jsonArray.size(); i++) {
			ResultInfo temp = new ResultInfo("success", "");
			JSONObject dataInfo = (JSONObject) jsonArray.get(i);
			// 组装xmls
			Element root = DocumentHelper.createElement(entity);
			Document document = DocumentHelper.createDocument(root);
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = dataInfo.keys();
			while (iterator.hasNext()) {
				String key = iterator.next().toString();
				root.addElement(key).addText(dataInfo.getString(key));
			}
			String xmls = document.getRootElement().asXML();
			try {
				// 调用toTalendDetail
				String primaryKey = talendSaveOrUpdateWS(inType, model, cluster, xmls);
				temp.setMsg("调用成功,主键为" + primaryKey);
			} catch (TalendException e) {
				// 处理异常 截取错误
				String msg = e.getMessage();
				if (msg.length() > 100) {
					msg = msg.substring(0, 100);
				}
				temp.setMsg("调用talend失败,详情:" + msg);
				temp.setSuccess("fail");
			}
			resultInfos.add(temp);
		}
		res.getResult().put("resultInfos", resultInfos);
		MsgEnum resEnum = generateMsgEnumByResultInfos(resultInfos);
		res.setMsgId(resEnum.getMsgId());
		res.setMsgDesc(resEnum.getMsgDesc());
		return res;
	}

	/**
	 * 根据 resultInfos 来判断成功 部分成功 失败
	 * 
	 * @param resultInfos
	 * @return
	 */
	private MsgEnum generateMsgEnumByResultInfos(List<ResultInfo> resultInfos) {
		// TODO Auto-generated method stub
		HashSet<String> resultSet = new HashSet<String>();
		for (ResultInfo resultInfo : resultInfos) {
			resultSet.add(resultInfo.getSuccess());
		}
		// 部分成功
		if (resultSet.contains("success") && resultSet.contains("fail"))
			return MsgEnum.PART_SUCCESS;
		// 成功
		else if (resultSet.contains("success") && (!resultSet.contains("fail")))
			return MsgEnum.SUCCESS;
		else
			return MsgEnum.FAIL;
	}

}
