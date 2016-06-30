package com.hzmc.nbgsyn.business;

import java.util.List;

import net.sf.json.JSONObject;

import com.hzmc.nbgsyn.domain.persistence.RegisterBean;

/**
 * 
 * @author ZhuLi
 *
 */
public interface RegisterManager {
//	/**
//	 * port
//	 * @param area
//	 * @param time
//	 * @return
//	 */
//	public List<PortBean> getPort(long pagesize, long index);
	
	/**
	 * 新增
	 * @param registerBean
	 * @return
	 */
	public JSONObject registerC(RegisterBean registerBean);
	
	/**
	 * 更新
	 * @param registerBean
	 * @return
	 */
	public JSONObject registerU(RegisterBean registerBean);
	
	
	/**
	 * 删除
	 * @param registerBean
	 * @return
	 */
	public JSONObject registerD(RegisterBean registerBean);
	
	/**
	 * 查询
	 * @param registerBean
	 * @return
	 */
	public List<RegisterBean> registerList(RegisterBean registerBean);
	
	
}
