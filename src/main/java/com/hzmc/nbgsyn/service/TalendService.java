package com.hzmc.nbgsyn.service;

import com.hzmc.nbgsyn.domain.persistence.ApplyDate;
import com.hzmc.nbgsyn.domain.persistence.ResultBean;

public interface TalendService {

	/**
	 * 保存数据
	 * 
	 * @param applyDate
	 * @return
	 */
	public ResultBean saveApplyDate(ApplyDate applyDate);

	/**
	 * 删除数据
	 * 
	 * @param applyDate
	 * @return
	 */
	public ResultBean removeApplyDate(ApplyDate applyDate);

	/**
	 * 更新数据
	 * 
	 * @param applyDate
	 * @return
	 */
	public ResultBean updateApplyDate(ApplyDate applyDate);

	/**
	 * 查找数据
	 * 
	 * @param applyDate
	 * @return
	 */
	public ResultBean findApplyDate(ApplyDate applyDate);

}
