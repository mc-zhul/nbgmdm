package com.hzmc.nbgsyn.business;

import com.hzmc.nbgsyn.business.exception.UserInfoException;
import com.hzmc.nbgsyn.domain.persistence.UserInfoBean;

/**
 * 用来管理服务注册接口总线的一些服务
 * 
 * @author tfchen 2016年6月29日16:57:35
 */
public interface IUserManager {
	/**
	 * 验证用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Boolean validateUser(String username, String userPassword);

	/**
	 * 保存注册用户
	 * 
	 * @param userInfoBean
	 * @exception 用户自定义异常
	 */
	public void saveRegisterUserInfo(UserInfoBean userInfoBean) throws UserInfoException;

	/**
	 * 删除用户
	 * 
	 * @param userInfoBean
	 * @throws UserInfoException
	 */
	public void removeUserInfo(UserInfoBean userInfoBean) throws UserInfoException;

	/**
	 * 更新用户
	 * 
	 * @param userInfoBean
	 */
	public void modifyUserInfo(UserInfoBean userInfoBean) throws UserInfoException;
}
