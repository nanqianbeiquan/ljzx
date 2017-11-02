/**   
* @Title: MonitorCompanyFinanceSituationDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年6月27日 下午2:06:06 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorCompanyFinanceSituationDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceSituation;

/** 
 * @ClassName: MonitorCompanyFinanceSituationDaoImpl
 * @Description: 动态监控企业财务状况DAO实现类
 * @author shiyong
 * @date 2017年6月27日 下午2:06:06
 *  
 */
@Repository
public class MonitorCompanyFinanceSituationDaoImpl extends BaseDaoImpl<MonitorCompanyFinanceSituation,String> implements MonitorCompanyFinanceSituationDao{

	@Override
	public MonitorCompanyFinanceSituation getCompanyFinanceSituationByCompanyId(
			String companyId) {
		
		String hql = "from MonitorCompanyFinanceSituation where companyBasicInfo.companyId = '" + companyId +"'";
		
		return getByHql(hql);
	}

	
}
