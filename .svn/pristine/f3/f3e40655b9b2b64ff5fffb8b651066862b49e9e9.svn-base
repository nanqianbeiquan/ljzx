/**   
* @Title: MonitorDefaultGroupDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月6日 下午6:48:23 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorDefaultGroupDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorDefaultGroup;

/** 
 * @ClassName: MonitorDefaultGroupDaoImpl
 * @Description: 动态监控默认分组Dao实现类
 * @author shiyong
 * @date 2017年9月6日 下午6:48:23
 *  
 */
@Repository("monitorDefaultGroupDao")
public class MonitorDefaultGroupDaoImpl extends BaseDaoImpl<MonitorDefaultGroup, String> implements MonitorDefaultGroupDao {
	@Override
	public List<MonitorDefaultGroup> getMonitorDefaultGroupList() {
		
		String hql = "from MonitorDefaultGroup where deleteFlag = '0' ";
		
		return super.getListByHql(hql);
	}
}
