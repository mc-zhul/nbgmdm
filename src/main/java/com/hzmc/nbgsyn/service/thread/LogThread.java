package com.hzmc.nbgsyn.service.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.hzmc.nbgsyn.business.GenerateManager;
import com.hzmc.nbgsyn.domain.persistence.SearchLog;
import com.hzmc.nbgsyn.service.TokenData;

public class LogThread extends Thread {

	private static final Logger LOG = Logger.getLogger(LogThread.class);
	GenerateManager generateManager;

	public LogThread(GenerateManager g) {
		this.generateManager = g;
		this.setName("LogThread");
	}

	public void run() {
		List<SearchLog> logList = new ArrayList<>();
		SearchLog searchLog;
		long start = System.currentTimeMillis();
		int num = 0;
		while (true) {
			try {
				searchLog = TokenData.logList.poll(1000, TimeUnit.MILLISECONDS);
				if (null != searchLog) {
					num++;
					logList.add(searchLog);
				}
				if ((System.currentTimeMillis() - start) > 1000 * 30 || num >= 2000) {
					generateManager.insertLog(logList);
					logList.clear();
					num = 0;
				}
			} catch (InterruptedException e) {
				LOG.error(e.getMessage());
			}
		}

	}
}
