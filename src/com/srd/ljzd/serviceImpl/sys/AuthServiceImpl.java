package com.srd.ljzd.serviceImpl.sys;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.srd.ljzd.service.sys.AuthService;

/**
 * 授权业务逻辑（登录注册）
 * 
 * @author weitao.liu
 *
 */
@Service("authService")
@CacheConfig
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	protected static Logger log = LogManager.getLogger(AuthServiceImpl.class.getName());

	/**
	 * 缓存验证码
	 * @param cellPhone
	 * @param code
	 * @return
	 */
	@Override
	public boolean cacheCode(String cellPhone, String code) {
		redisTemplate.delete(cellPhone);
		redisTemplate.opsForValue().set(cellPhone, code, 5*60, TimeUnit.SECONDS);//5分钟
		return true;
	}
	 /**
     * 获取验证码
     * @param cellPhone
     * @return
     */
	@Override
	public String getCachedCode(String cellPhone) {
		return (String)redisTemplate.opsForValue().get(cellPhone);
	}
	
	/**
	 * 清楚验证码
	 * @param cellPhone
	 * @return
	 */
	@Override
	public boolean clearCachedCode(String cellPhone) {
		redisTemplate.delete(cellPhone);
		return true;
	}
	
	@Override
	public int updateMisPwdCount(String accountId) {
		// TODO Auto-generated method stub
		String key = accountId+"_ljzx_misCount";
		int count = getMisPwdCount(accountId);
		//删除已有记录
		redisTemplate.delete(key);
		//保存新的
		redisTemplate.opsForValue().set(key, count+1, 30*60, TimeUnit.SECONDS);//半个小时
		return count+1;
	}
	@Override
	public void clearMisPwdCount(String accountId) {
		// TODO Auto-generated method stub
		redisTemplate.delete(accountId+"_ljzx_misCount");
	}
	@Override
	public int getMisPwdCount(String accountId) {
		String key = accountId+"_ljzx_misCount";
		Object countObj = redisTemplate.opsForValue().get(key);
		int count = 0;
		if(countObj!=null){
			count = (int)countObj;
		}
		
		return count;
	}
	@Override
	public void addToForbidden(String accountId) {
		String key = accountId+"_ljzx_forbidden";
		//删除已有记录
		redisTemplate.delete(key);
		//保存新的
		redisTemplate.opsForValue().set(key, true, 10*60, TimeUnit.SECONDS);//10分钟
	}
	@Override
	public boolean isInForbidden(String accountId) {
		String key = accountId+"_ljzx_forbidden";
		Object forbidden = redisTemplate.opsForValue().get(key);
		if(forbidden!=null){
			return true;
		}
		return false;
	}

}
