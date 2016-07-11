package main.java;

import java.util.List;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.RandomFontsWebService;
import cn.com.webxml.RandomFontsWebServiceSoap;

public class CharTest {

	public static void main(String[] args) {
		CharTest.method1();
	}

	public static void method1() {
		RandomFontsWebService fontsWebService = new RandomFontsWebService();
		RandomFontsWebServiceSoap fontsWebServiceSoap = fontsWebService.getRandomFontsWebServiceSoap();
		ArrayOfString arrayOfString = fontsWebServiceSoap.getCharFonts(5);
		List<String> res = arrayOfString.getString();
		System.out.println(res);
	}

}
