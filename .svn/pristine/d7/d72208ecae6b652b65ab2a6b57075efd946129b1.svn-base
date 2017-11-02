/**   
* @Title: MonitorDefaultCompanyDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月6日 下午6:47:11 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorDefaultCompanyDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorDefaultCompany;

/** 
 * @ClassName: MonitorDefaultCompanyDaoImpl
 * @Description: 默认动态监控公司Dao实现类
 * @author shiyong
 * @date 2017年9月6日 下午6:47:11
 *  
 */
@Repository("monitorDefaultCompanyDao")
public class MonitorDefaultCompanyDaoImpl extends BaseDaoImpl<MonitorDefaultCompany, String> implements MonitorDefaultCompanyDao {
	@Override
	public List<MonitorDefaultCompany> getMonitorDefaultCompanyList() {
		String hql = "from MonitorDefaultCompany where deleteFlag = '0' ";
		
		return super.getListByHql(hql);
	}
}
