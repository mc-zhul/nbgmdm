package com.hzmc.hzbicycle.business.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;







import com.hzmc.hzbicycle.business.SignManager;
import com.hzmc.hzbicycle.util.MD5Util;

public class SignManagerImpl implements SignManager {

	@SuppressWarnings("rawtypes")
	@Override
	public String getSign(LinkedHashMap map) {
		StringBuffer kv = new StringBuffer();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String k = String.valueOf(iterator.next());
			String v = String.valueOf(map.get(k));
			kv.append(k).append(v);
		}
		return kv.toString();
	}

	@Override
	public String md5Secret(String kv, String secret) {
		StringBuffer sb = new StringBuffer();
		sb.append(secret).append(kv).append(secret);
		String s = sb.toString();
		s = MD5Util.MD5(s).toUpperCase();
		return null;
	}

	@Override
	public String hmacSecret(String kv, String secret) {
		StringBuffer sb = new StringBuffer();
		sb.append(secret).append(kv);
		String s = sb.toString();
		s = MD5Util.MD5(s).toUpperCase();
		return null;
	}

}
