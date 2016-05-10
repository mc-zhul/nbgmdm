package com.hzmc.nbgmdm.web;


import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgmdm.service.CallService;

/**
 * 
 * @author ZhuLi
 *
 */
public class CrudCall implements Callable {

	@Autowired
	private CallService callService;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		return callService.mdCall(eventContext, 1);
	}
}
