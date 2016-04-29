package com.hzmc.nbgmdm.service.thread;


import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgmdm.business.GenerateManager;
import com.hzmc.nbgmdm.service.DataInitService;

public class ThreadManager {
	@Autowired
	DataInitService dataInitService;
	@Autowired
	GenerateManager generateManager;
	
	public void start(){
		new DataThread(dataInitService).start();
		new LogThread(generateManager).start();
	}
}
