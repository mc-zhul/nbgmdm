package com.hzmc.nbgsyn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.AuthorityManager;
import com.hzmc.nbgsyn.domain.persistence.UserAuthorityBean;
import com.hzmc.nbgsyn.service.DataInitService;
import com.hzmc.nbgsyn.service.TokenData;

@Service
public class DataInitServiceImpl implements DataInitService {

	@Autowired
	AuthorityManager authorityManager;

	@Override
	public void init() {
		List<UserAuthorityBean> usersColumnList = authorityManager.getUserColumns();
		List<UserAuthorityBean> allColumnList = authorityManager.getAllColumns();
		ConcurrentHashMap<Long, Map<Long, List<String>>> map = new ConcurrentHashMap<Long, Map<Long, List<String>>>();
		ConcurrentHashMap<Long, Map<Long, List<String>>> fmap = new ConcurrentHashMap<Long, Map<Long, List<String>>>();
		ConcurrentHashMap<Long, Map<Long, Long>> timeMap = new ConcurrentHashMap<Long, Map<Long, Long>>();
		Map<Long, List<String>> map1 = new HashMap<Long, List<String>>();
		Map<Long, List<String>> tempMap = null;

		for (UserAuthorityBean u : allColumnList) {// 获取所有字段
			long sourceId = u.getSource_id();
			String column = u.getCol_name();
			if (null == map1.get(sourceId)) {
				map1.put(sourceId, new ArrayList<String>());
			}
			if (null != column && !"".equals(column)) {
				map1.get(sourceId).add(column);
			}
		}
		List<String> tempList = null;
		for (UserAuthorityBean u : usersColumnList) {// 获取用户
			long userId = u.getSource_id();
			long sourceId = u.getSource_id();
			tempList = new ArrayList<>();
			if (null != map1.get(sourceId)) {
				tempList.addAll(map1.get(sourceId));
			}
			String column = u.getCol_name();
			if (null == map.get(userId)) {
				map.put(userId, new HashMap<Long, List<String>>());
				fmap.put(userId, new HashMap<Long, List<String>>());
			}
			tempMap = map.get(userId);
			if (null == tempMap.get(sourceId)) {
				map.get(userId).put(sourceId, tempList);
				fmap.get(userId).put(sourceId, new ArrayList<String>());
			}
			if (null != column && !"".equals(column)) {
				map.get(userId).get(sourceId).remove(column);
				fmap.get(userId).get(sourceId).add(column);
			}
		}
		TokenData.authMap = map;
		TokenData.fauthMap = fmap;
		// time
		List<UserAuthorityBean> timeList = authorityManager.getTime();
		for (UserAuthorityBean u : timeList) {
			long userId = u.getSource_id();
			long sourceId = u.getSource_id();
			long time = u.getTime();
			if (null == timeMap.get(userId)) {
				timeMap.put(userId, new HashMap<Long, Long>());
			}
			Map<Long, Long> tMap = timeMap.get(userId);
			if (null == tMap.get(sourceId)) {
				timeMap.get(userId).put(sourceId, time);
			}
		}
		TokenData.timeMap = timeMap;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
