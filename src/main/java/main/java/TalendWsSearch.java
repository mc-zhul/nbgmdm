package main.java;

import javax.xml.ws.BindingProvider;

import com.mchz.nbg.talendservice.TMDMService;
import com.mchz.nbg.talendservice.TMDMService_Service;
import com.mchz.nbg.talendservice.WSDataClusterPK;
import com.mchz.nbg.talendservice.WSDataModelPK;
import com.mchz.nbg.talendservice.WSGetItem;
import com.mchz.nbg.talendservice.WSPutItem;
import com.mchz.nbg.talendservice.WSPutItemWithReport;

public class TalendWsSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalendWsSearch.method1();
	}

	public static void method1() {
		final String S_URL = "http://169.169.171.148:8080/talendmdm/services/soap";

		try {
			TMDMService_Service tws = new TMDMService_Service();

			TMDMService port = tws.getTMDMPort();
			BindingProvider bp = (BindingProvider) port;

			bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "administrator");
			bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "administrator");
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, S_URL);
			WSGetItem wsGetItem = new WSGetItem();
//			wsGetItem.setWsItemPK(value);
			
//			port.getItem(wsGetItem).
		} catch (Exception e) {

			System.out.println("error");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
