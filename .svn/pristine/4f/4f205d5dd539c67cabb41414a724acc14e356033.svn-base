/**   
* @Title: PopulorCompanyDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月17日 上午10:49:51 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.company.PopulorCompanyDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.company.PopulorCompany;

/** 
 * @ClassName: PopulorCompanyDaoImpl
 * @Description: 热门企业Dao实现类
 * @author shiyong
 * @date 2016年11月17日 上午10:49:51
 *  
 */
@Repository("populorCompanyDao")
public class PopulorCompanyDaoImpl extends BaseDaoImpl<PopulorCompany, String> implements PopulorCompanyDao{

	protected static Logger logger = LogManager.getLogger(PopulorCompanyDaoImpl.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PopulorCompany> queryRecentPopulorCompanyList(int num) {
		
		String hql = "from PopulorCompany where isDelete = '0' order by createTime desc ";
		
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(num);
		
		List<PopulorCompany> populorCompanyList = null;
		
		try{
			populorCompanyList = query.list();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		if(populorCompanyList == null){
			populorCompanyList = new ArrayList<PopulorCompany>();
		}
		
		return populorCompanyList;
	}
	
}
