package main.java;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.hzmc.nbgsyn.web.CrudCall;
import com.hzmc.nbgsyn.web.TransCall;

public class Test {

	public static void main(String[] args) {
		CrudCall t = new CrudCall();
		
//		try {
//			String s = URLEncoder.encode("{\"action\":\"TRANSFOR\",\"operand\":\"NBG\",\"token\":\"token\",\"type\":\"U\",\"root\":\"RD_COUNTRY\",\"applyData\":{\"RD_COUNTRYId\":\"4\",\"COUNTRY_CODE\":\"28\"},\"whereData\":{\"PORT_ID\":\"346\"}}", "utf-8");
////					.replace("[", URLEncoder.encode("[", "utf-8")).replace("]", URLEncoder.encode("[", "utf-8"));
////			t.postCtos(s);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int i = 0;
		int j = 0;
		for (int q = 0; q < 1000000; q++) {
			Double d = Math.random();
			if(0.8 <= d && d <0.9){
				i++;
			}else if(0.7 <= d && d <0.8){
				j++;
			}
		}
		System.out.println("i= " + i + " j= " +j);
	}
}
