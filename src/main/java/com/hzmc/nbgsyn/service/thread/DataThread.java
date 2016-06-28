package com.hzmc.nbgmdm.service.thread;


import com.hzmc.nbgmdm.service.DataInitService;

public class DataThread extends Thread {

	DataInitService dataInitService;

	public DataThread(DataInitService s){
		this.dataInitService = s;
	}
	
	public void run() {
		// Property property = new Property();
		// property.getSystemProperties();
		while (true) {
			dataInitService.init();
			try {
				sleep(1000 * 60 * 4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
