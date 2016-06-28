package com.hzmc.nbgsyn.service.impl;

import javax.xml.ws.BindingProvider;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.service.TalendService;
import com.mchz.nbg.talendservice.TMDMService;
import com.mchz.nbg.talendservice.TMDMService_Service;
import com.mchz.nbg.talendservice.WSDataClusterPK;
import com.mchz.nbg.talendservice.WSDataModelPK;
import com.mchz.nbg.talendservice.WSPutItem;
import com.mchz.nbg.talendservice.WSPutItemWithReport;

@Service
public class TalendServiceImpl implements TalendService {

	@Override
	public JSONObject talendWS(String type, String model,String cluster,String xmls) {
		final String WS_URL = "file:D:/talend/soap.wsdl";
//		final String S_URL = "http://localhost:8180/talendmdm/services/soap";
		final String S_URL = "http://169.169.171.148:8080/talendmdm/services/soap";
		String r = null;
		JSONObject j = new JSONObject();
		try {
			// QName qname = new
			// QName("http://www.talend.com/mdm","TMDMService");
			// Service service = Service.create(url, qname);
			// TMDMService port=service.getPort(TMDMService.class);
			TMDMService_Service tws = new TMDMService_Service();
			TMDMService port = tws.getTMDMPort();
			BindingProvider bp = (BindingProvider) port;
			bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,"administrator");
			bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,"administrator");
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, S_URL);
			WSPutItem item = new WSPutItem();
			WSDataModelPK dp = new WSDataModelPK();
			WSDataClusterPK dc = new WSDataClusterPK();
//			dc.setPk("NBG");
//			dp.setPk("NBG");
			dc.setPk(cluster);
			dp.setPk(model);
			item.setWsDataModelPK(dp);
			if("U".equals(type)){
				item.setIsUpdate(true);
			}else{
				item.setIsUpdate(false);
			}
			item.setWsDataClusterPK(dc);
//			item.setXmlString("<RD_COUNTRY><RD_COUNTRYId>4</RD_COUNTRYId><COUNTRY_CODE>23</COUNTRY_CODE></RD_COUNTRY>");
			item.setXmlString(xmls);
			WSPutItemWithReport itemrp = new WSPutItemWithReport();
			itemrp.setSource("cbos-call");
			itemrp.setWsPutItem(item);
			itemrp.setInvokeBeforeSaving(true);

			String rtnMessage = port.putItemWithReport(itemrp).getIds().toString();
			// String rtnMessage1=port.putItem(item).getIds().toString();
			r = "主键为：" + rtnMessage + "的数据保存成功";
			System.out.println(r);
			j.accumulate("msgID", "0001");
		} catch (Exception e) {
			j.accumulate("msgID", "0011");
			e.printStackTrace();
			r = "错误信息：" + e.getMessage();
		}
		
		j.accumulate("msgDesc", r);
		
		return j;
	}

}
