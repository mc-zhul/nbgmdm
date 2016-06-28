package com.hzmc.nbgmdm.service;

public interface DataInitService {
	/**
	 * 初始化权限数据
	 */
	public void init();
	
	/**
	 * 刷新权限数据
	 */
	public void refresh();
}
