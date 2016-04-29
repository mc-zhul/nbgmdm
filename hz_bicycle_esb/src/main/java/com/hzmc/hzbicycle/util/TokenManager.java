package com.hzmc.hzbicycle.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

public class TokenManager {

	private static final Logger log4j = Logger.getLogger(TokenManager.class);

	/**
	 * 生成token
	 * 
	 * @param appkey
	 * @param secret
	 * @return
	 */
	public static String getToken(String appkey) {
		String time = String.valueOf(System.currentTimeMillis());
		String s = time + appkey;
		return new String(encrypt(s, "hzmc"));
	}

	/**
	 * 加密
	 * 
	 * @param content  需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			log4j.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			log4j.error(e.getMessage());
		} catch (InvalidKeyException e) {
			log4j.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log4j.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			log4j.error(e.getMessage());
		} catch (BadPaddingException e) {
			log4j.error(e.getMessage());
		}
		return content.getBytes();
	}
}
