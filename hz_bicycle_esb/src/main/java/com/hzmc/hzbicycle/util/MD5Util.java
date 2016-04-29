/**
 * 版权所有：美创科技
 * 项目名称:
 * 创建者: jinmq
 * 创建日期: 2012-11-20
 * 文件说明:
 * 最近修改者：jinmq
 * 最近修改日期：2012-11-20
 */
package com.hzmc.hzbicycle.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;


/**
 * @author jinmq
 */
public class MD5Util {

	public static String MD5(String pwd) {
		// 用于加密的字符
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			// 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
			byte[] btInput = pwd.getBytes();
			// 获得指定摘要算法的 MessageDigest对象，此处为MD5
			// MessageDigest类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			// 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// System.out.println(mdInst);
			// MD5 Message Digest from SUN, <initialized>
			// MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
			mdInst.update(btInput);
			// System.out.println(mdInst);
			// MD5 Message Digest from SUN, <in progress>
			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
			byte[] md = mdInst.digest();
			// System.out.println(md);
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			// System.out.println(j);
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) { // i = 0
				byte byte0 = md[i]; // 95
				str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
				str[k++] = md5String[byte0 & 0xf]; // F
			}
			// 返回经过加密后的字符串
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.print("请输入要加密的字符串：");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			String s=br.readLine();
			System.out.println("MD5："+MD5(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}