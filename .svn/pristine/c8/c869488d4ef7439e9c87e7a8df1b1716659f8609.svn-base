/**   
* @Title: PopulorCompanyServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月17日 上午11:04:17 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.company.PopulorCompanyDao;
import com.srd.ljzd.entity.company.PopulorCompany;
import com.srd.ljzd.service.company.PopulorCompanyService;

/** 
 * @ClassName: PopulorCompanyServiceImpl
 * @Description: 热门企业Service实现类
 * @author shiyong
 * @date 2016年11月17日 上午11:04:17
 *  
 */
@Service("populorCompanyService")
public class PopulorCompanyServiceImpl implements PopulorCompanyService{
	
	@Autowired
	private PopulorCompanyDao populorCompanyDao;

	@Override
	public List<PopulorCompany> queryRecentPopulorCompanyList(int num) {
		
		return populorCompanyDao.queryRecentPopulorCompanyList(num);
	}
	
	
	
	
}
