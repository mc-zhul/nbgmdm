package com.hzmc.nbgsyn.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgsyn.business.IUserManager;
import com.hzmc.nbgsyn.business.enums.MsgEnum;
import com.hzmc.nbgsyn.business.exception.UserInfoException;
import com.hzmc.nbgsyn.domain.persistence.ApplyDate;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;
import com.hzmc.nbgsyn.domain.persistence.UserInfoBean;
import com.hzmc.nbgsyn.webservice.EdiEsbService;

/**
 * 实现类
 * 
 * @author tfchen 2016年6月28日15:58:19
 */
@WebService(endpointInterface = "com.hzmc.service.EdiEsbService", serviceName = "EdiEsbService")
public class EdiEsbServiceImpl implements EdiEsbService {

	@Autowired
	private IUserManager userManager;

	private Logger logger = Logger.getLogger(EdiEsbServiceImpl.class);

	@Override
	public String callEDIESBPub(String fromNode, String toNode, String esbID, String applyDataStr, String userID,
			String password) {
		// TODO Auto-generated method stub
		ResultBean resultBean = new ResultBean();

		// 只需要applyDate
		JSONArray applyDataJarry;
		ApplyDate applyDate = new ApplyDate();

		try {
			applyDataJarry = JSONArray.fromObject(applyDataStr);
			JSONObject applyDateJo = applyDataJarry.getJSONObject(0);
			applyDate = (ApplyDate) JSONObject.toBean(applyDateJo, ApplyDate.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			resultBean.setMsgId(MsgEnum.FORMART_ERROR.getMsgId());
			resultBean.setMsgDesc(MsgEnum.FORMART_ERROR.getMsgDesc());
			return JSONObject.fromObject(resultBean).toString();
		}

		String username = applyDate.getUsername();
		String userPassword = applyDate.getPassword();
		username = username == null ? "" : username;
		userPassword = userPassword == null ? "" : userPassword;

		// 验证用户 --- 通讯用户--和下面注册的用户无关
		if (!userManager.validateUser(username, userPassword)) {
			resultBean.setMsgId(MsgEnum.USER_PWD_ERROR.getMsgId());
			resultBean.setMsgDesc(MsgEnum.USER_PWD_ERROR.getMsgDesc());
			return JSONObject.fromObject(resultBean).toString();
		}

		String actionType = applyDate.getAction();
		// 注册方法
		if ("SERVICE_REGISTER".equals(actionType)) {
			resultBean.setMsgId(MsgEnum.SUCCESS.getMsgId());
			resultBean.setMsgDesc(MsgEnum.SUCCESS.getMsgDesc());

			JsonConfig config = new JsonConfig();
			config.setRootClass(UserInfoBean.class);
			@SuppressWarnings("unchecked")
			List<UserInfoBean> userInfoList = (ArrayList<UserInfoBean>) JSONSerializer.toJava(applyDate.getData(),
					config);

			String type = applyDate.getType();
			// 注册 他们系统的用户 前三个选项的联合主键
			if ("C".equals(type)) {
				for (UserInfoBean userInfoBean : userInfoList) {
					try {
						userManager.saveRegisterUserInfo(userInfoBean);
					} catch (UserInfoException e) {
						// TODO Auto-generated catch block
						logger.error(e);
						resultBean.setMsgId(MsgEnum.USER_REGISTER_ERROR.getMsgId());
						resultBean.setMsgDesc(MsgEnum.USER_REGISTER_ERROR.getMsgDesc());
						resultBean.appendResult(e.getMessage());
					}
				}
			}
			// 删除
			else if ("D".equals(type)) {
				for (UserInfoBean userInfoBean : userInfoList) {
					try {
						userManager.removeUserInfo(userInfoBean);
					} catch (UserInfoException e) {
						// TODO Auto-generated catch block
						logger.error(e);
						resultBean.setMsgId(MsgEnum.USER_DELETE_ERROR.getMsgId());
						resultBean.setMsgDesc(MsgEnum.USER_DELETE_ERROR.getMsgDesc());
						resultBean.appendResult(e.getMessage());
					}
				}
			}
			// 更新
			else if ("U".equals(type)) {
				for (UserInfoBean userInfoBean : userInfoList) {
					try {
						userManager.modifyUserInfo(userInfoBean);
					} catch (UserInfoException e) {
						// TODO Auto-generated catch block
						logger.error(e);
						resultBean.setMsgId(MsgEnum.USER_UPDATE_ERROR.getMsgId());
						resultBean.setMsgDesc(MsgEnum.USER_UPDATE_ERROR.getMsgDesc());
						resultBean.appendResult(e.getMessage());
					}
				}
			}
		}
		//
		return JSONObject.fromObject(resultBean).toString();
	}

}
