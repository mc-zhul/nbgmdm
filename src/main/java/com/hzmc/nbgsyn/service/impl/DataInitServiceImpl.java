package com.hzmc.nbgsyn.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.IUserManager;
import com.hzmc.nbgsyn.domain.persistence.UserInfoBean;
import com.hzmc.nbgsyn.service.CacheData;
import com.hzmc.nbgsyn.service.DataInitService;

@Service
public class DataInitServiceImpl implements DataInitService {
	
	@Autowired
	private IUserManager iUserManager;
	
	@Override
	public void dataInit() {
		List<UserInfoBean> userInfoBeans = iUserManager.findAllUserInfo();
		for (UserInfoBean userInfoBean : userInfoBeans) {
			//将3个联合主键合成key
			String key = userInfoBean.getMD_CODE() + "|" + userInfoBean.getSYS_CODE() + "|" + userInfoBean.getENTITY_CODE();
			if(null == CacheData.userInfoMap){
				CacheData.userInfoMap = new HashMap<String, UserInfoBean>();
			}
			CacheData.userInfoMap.put(key, userInfoBean);
		}
		
	}

}
