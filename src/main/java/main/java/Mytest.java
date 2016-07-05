package main.java;

import com.hzmc.nbgsyn.domain.persistence.ApplyDate;

import net.sf.json.JSONObject;

public class Mytest {

	public static void main(String[] args) {
		String s = "{\"action\":\" SERVICE_REGISTER\",\"username\":\"username\",\"password\":\"password\",\"type \":\"C\",\"data\":[{\"MD_CODE\":\"NBG_MDM\", \"SYS_CODE\":\" PORT5\", \"ENTITY_CODE\":\"MD_LINE_SHIPLINE\",\"SERVICE_NAME\":\"sysn\",\"username\":\"port5\",\"password\":\"123\"}]}";
		System.out.println(s);
		JSONObject jo = JSONObject.fromObject(s);
		System.out.println(jo);
		ApplyDate applyDate = (ApplyDate) JSONObject.toBean(jo, ApplyDate.class);
		System.out.println("aaa");
	}
}
