package com.hzmc.hzbicycle.business;

import java.util.List;
import java.util.Map;

/**
 * 驗證appkey
 * @author ZhuLi
 *
 */
public interface GenerateManager {
	/**
	 * 校验appkey
	 * @param appKey
	 * @param secret
	 * @param grant_type
	 * @return
	 */
	public boolean checkAppkey(String appKey, String secret, String grant_type);
	

}
