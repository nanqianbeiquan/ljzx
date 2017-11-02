package com.srd.ljzd.service.sys;

public interface AuthService {
	
	/**
	 * 缓存验证码
	 * @param cellPhone
	 * @param code
	 * @return
	 */
	public boolean cacheCode(String cellPhone,String code);
	
    /**
     * 获取验证码
     * @param cellPhone
     * @return
     */
	public String getCachedCode(String cellPhone);
	/**
	 * 清楚验证码
	 * @param cellPhone
	 * @return
	 */
	public boolean clearCachedCode(String cellPhone);

	public int updateMisPwdCount(String accountId);

	public void clearMisPwdCount(String accountId);
	
	public int getMisPwdCount(String accountId);

	public void addToForbidden(String accountId);
	
	public boolean isInForbidden(String accountId);
	
}
