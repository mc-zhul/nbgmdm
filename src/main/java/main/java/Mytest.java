package main.java;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import com.hzmc.nbgsyn.domain.persistence.UserInfoBean;

public class Mytest {

	public static void main(String[] args) {
		String str = "";
		List<UserInfoBean> userInfos = new ArrayList<UserInfoBean>();
		for (int i = 0; i < 2; i++) {
			UserInfoBean temp = new UserInfoBean();
			temp.setUsername("haha");
			userInfos.add(temp);
		}
		JSONObject jo = new JSONObject();
		jo.element("userInfos", userInfos);
		str = jo.toString();
		System.out.println(str);
		JSONObject joo = JSONObject.fromObject(str);
		System.out.println(joo);
		JsonConfig config = new JsonConfig();
		config.setRootClass(UserInfoBean.class);
		List<UserInfoBean> userInfoList = (ArrayList<UserInfoBean>) JSONSerializer.toJava(joo.getJSONArray("userInfos"),
				config);
		System.out.println(userInfoList);
		
	}
}
