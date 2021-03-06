package main.java;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import com.mchz.nbg.talendservice.GetItems;
import com.mchz.nbg.talendservice.PutItem;
import com.mchz.nbg.talendservice.TMDMService;
import com.mchz.nbg.talendservice.TMDMService_Service;
import com.mchz.nbg.talendservice.WSDataClusterPK;
import com.mchz.nbg.talendservice.WSDataModel;
import com.mchz.nbg.talendservice.WSDataModelPK;
import com.mchz.nbg.talendservice.WSGetItems;
import com.mchz.nbg.talendservice.WSPutItem;
import com.mchz.nbg.talendservice.WSPutItemWithReport;
import com.mchz.nbg.talendservice.WSWhereAnd;
import com.mchz.nbg.talendservice.WSWhereItem;

public class TalendWsGet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String WS_URL = "file:D:/talend/soap.wsdl";
		final String S_URL = "http://localhost:8180/talendmdm/services/soap";

		URL url;
		try {
			url = new URL(WS_URL);

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
			WSGetItems item = new WSGetItems();
			WSDataModelPK dp = new WSDataModelPK();
			WSDataClusterPK dc = new WSDataClusterPK();
			dc.setPk("Product");
			dp.setPk("Product");
//			item.setWsDataModelPK(dp);
			item.setWsDataClusterPK(dc);
			item.setMaxItems(4);
			item.setConceptName("<Product><Id>1211</Id></Product>");
			
			WSWhereItem wit = new WSWhereItem();
			WSWhereAnd wad = new WSWhereAnd();
			wit.setWhereAnd(wad);
			item.setWhereItem(wit);
//			
//			item.setWhereItem(wit);
			
//			item.setXmlString("<RD_COUNTRY><RD_COUNTRYId>66</RD_COUNTRYId><COUNTRY_CODE>25</COUNTRY_CODE></RD_COUNTRY>");
//			item.setXmlString("<RD_COUNTRY><RD_COUNTRYId>72</RD_COUNTRYId><COUNTRY_CODE>25</COUNTRY_CODE></RD_COUNTRY>");
			
//			WSPutItem item1 = new WSPutItem();
//			item1.setWsDataModelPK(dp);
//			item1.setWsDataClusterPK(dc);
//			item1.setIsUpdate(false);
//			item1.setXmlString("<RD_COUNTRY><RD_COUNTRYId>71</RD_COUNTRYId><COUNTRY_CODE>25</COUNTRY_CODE></RD_COUNTRY>");
//			
//			WSGetItem itemrp = new WSPutItemWithReport();
//			itemrp.setSource("cbos-call");
//			itemrp.set
////			itemrp.setWsPutItem(item1);
//			itemrp.setInvokeBeforeSaving(true);
			
			String rtnMessage = port.getItems(item).toString();
			// String rtnMessage1=port.putItem(item).getIds().toString();
			System.out.println("Result=>" + rtnMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
