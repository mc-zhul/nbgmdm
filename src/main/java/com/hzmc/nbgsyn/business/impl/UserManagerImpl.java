package com.hzmc.nbgsyn.business.impl;

import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.IUserManager;
import com.hzmc.nbgsyn.business.exception.UserInfoException;
import com.hzmc.nbgsyn.domain.persistence.UserInfoBean;

/**
 * 用户的一些服务
 * 
 * @author tfchen
 *
 */
@Service
public class UserManagerImpl implements IUserManager {

	@Override
	public Boolean validateUser(String username, String userPassword) {
		// TODO Auto-generated method stub
		Boolean res = false;
		// 验证用户 未完成 在缓存中验证
		if (username.equals("ctf"))
			res = true;
		return res;
	}

	@Override
	public void saveRegisterUserInfo(UserInfoBean userInfoBean) throws UserInfoException {
		// TODO Auto-generated method stub
		String md_code = userInfoBean.getMD_CODE();
		String sys_code = userInfoBean.getSYS_CODE();
		String entity_code = userInfoBean.getENTITY_CODE();

		if ("A".equals(md_code) && "B".equals(sys_code) && "C".equals(entity_code)) {
			throw new UserInfoException("md_code:" + md_code + ",sys_code:" + sys_code + ",entity_code" + entity_code
					+ "已注册！");
		}
		// 添加用户
	}

	@Override
	public void removeUserInfo(UserInfoBean userInfoBean) throws UserInfoException {
		// TODO Auto-generated method stub
		String md_code = userInfoBean.getMD_CODE();
		String sys_code = userInfoBean.getSYS_CODE();
		String entity_code = userInfoBean.getENTITY_CODE();
		UserInfoBean temp = this.findUserInfo(userInfoBean);
		if (temp == null)
			throw new UserInfoException("md_code:" + md_code + ",sys_code:" + sys_code + ",entity_code" + entity_code
					+ "找不到用户信息！");
		// 删除用户
	}

	/**
	 * 查找用户信息
	 * 
	 * @param userInfoBean
	 * @return
	 */
	private UserInfoBean findUserInfo(UserInfoBean userInfoBean) {
		// TODO Auto-generated method stub
		// 查找用户
		UserInfoBean temp = null;
		return temp;
	}

	@Override
	public void modifyUserInfo(UserInfoBean userInfoBean) throws UserInfoException {
		String md_code = userInfoBean.getMD_CODE();
		String sys_code = userInfoBean.getSYS_CODE();
		String entity_code = userInfoBean.getENTITY_CODE();
		// TODO Auto-generated method stub
		UserInfoBean temp = this.findUserInfo(userInfoBean);
		if (temp == null)
			throw new UserInfoException("md_code:" + md_code + ",sys_code:" + sys_code + ",entity_code" + entity_code
					+ "找不到用户信息！");
		// 更新
	}

}
