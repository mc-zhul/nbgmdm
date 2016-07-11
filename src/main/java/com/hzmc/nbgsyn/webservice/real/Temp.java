package com.hzmc.nbgsyn.webservice.real;

import main.java.TalendWs;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class Temp implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("hahahah");

		TalendWs.method1();

		return null;
	}

}
