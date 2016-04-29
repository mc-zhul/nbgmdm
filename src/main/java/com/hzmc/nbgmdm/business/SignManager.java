package com.hzmc.nbgmdm.business;

import java.util.LinkedHashMap;

public interface SignManager {
	/**
	 * 根据参数生成签名
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getSign(LinkedHashMap map);
	
	/**
	 * Md5加密，将secret 拼接到参数字符串头、尾进行md5加密后，再转化成大写。
	 * @param kv
	 * @param secret
	 * @return
	 */
	public String md5Secret(String kv,String secret);
	
	/**
	 * hmac加密：采用hmac的md5方式，secret只在头部的签名后再转化成大写。
	 * @param kv
	 * @param secret
	 * @return
	 */
	public String hmacSecret(String kv,String secret);
}
