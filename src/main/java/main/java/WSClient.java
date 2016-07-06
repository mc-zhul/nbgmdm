package main.java;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

public class WSClient {

	public static String callEDIWSB(String url, String operationname, String nameSpace, String fromNode, String esbID,
			String applyData, String userId, String password) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			QName qName = new QName(nameSpace, operationname);
			call.setOperationName(qName);
			call.addParameter(new QName(nameSpace, "arg0"), Constants.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(nameSpace, "arg1"), Constants.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(nameSpace, "arg2"), Constants.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(nameSpace, "arg3"), Constants.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(nameSpace, "arg4"), Constants.XSD_STRING, ParameterMode.IN);
			// call.addParameter(new QName(parameter), Constants.XSD_STRING,
			// ParameterMode.IN);
			// call.setUseSOAPAction(true);
			call.setReturnType(XMLType.XSD_STRING);
			// call.setTimeout(5000);
			result = (String) call.invoke(new Object[] { fromNode, esbID, applyData, userId, password });
			System.out.println(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		String url = "http://169.169.6.92:8888/com.nbport.ediesb.service.EDIESBService";
		String operationname = "callEDIESB";
		String nameSpace = "http://service.ediesb.nbport.com/";
		callEDIWSB(url, operationname, nameSpace, "NPEDI", "CiqImportLCLPass",
				"TGHU0176445::UN9392901:1530WI:PCLUINC00262943F|PCLUINC00262943|PCLUINC00262943B", null, null);

	}
}
