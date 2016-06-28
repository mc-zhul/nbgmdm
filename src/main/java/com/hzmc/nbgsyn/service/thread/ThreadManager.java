package com.hzmc.nbgsyn.service.thread;


import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgsyn.business.GenerateManager;
import com.hzmc.nbgsyn.service.DataInitService;

public class ThreadManager {
	@Autowired
	DataInitService dataInitService;
	@Autowired
	GenerateManager generateManager;
	
	public void start(){
//		new DataThread(dataInitService).start();
//		new LogThread(generateManager).start();
	}
}
