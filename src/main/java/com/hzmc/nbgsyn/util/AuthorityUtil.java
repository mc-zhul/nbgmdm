package com.hzmc.nbgsyn.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 帮助类
 * 
 * @author ZhuLi
 *
 */
public class AuthorityUtil {

	/**
	 * 过滤字段权限
	 * 
	 * @param array
	 * @param coulums
	 */
	public static void coulumfilter(JSONObject jo, List<String> coulums) {
		if (null != jo.get("list")) {
			JSONArray array = jo.getJSONArray("list");
			for (int i = 0; i < array.size(); i++) {
				for (String c : coulums) {
					array.getJSONObject(i).remove(c);
				}
			}
			jo.put("list", array);
		}
	}
}
