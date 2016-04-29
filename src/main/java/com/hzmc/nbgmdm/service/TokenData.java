package com.hzmc.nbgmdm.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.hzmc.nbgmdm.domain.persistence.SearchLog;


public class TokenData {
	public static ConcurrentHashMap<Long, String> tokenMap = new ConcurrentHashMap<Long, String>();
	public static ConcurrentHashMap<String, Long> userIdMap = new ConcurrentHashMap<String, Long>();
	public static ConcurrentHashMap<Long, Map<Long,List<String>>> authMap;
	public static ConcurrentHashMap<Long, Map<Long,List<String>>> fauthMap;
	public static ConcurrentHashMap<Long, Map<Long,Long>> timeMap;
	public static LinkedBlockingQueue<SearchLog> logList = new LinkedBlockingQueue<SearchLog>(10000);
	public static long rows = 100L;
}
