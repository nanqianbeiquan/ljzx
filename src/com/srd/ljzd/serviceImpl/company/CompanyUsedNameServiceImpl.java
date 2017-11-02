/**   
* @Title: CompanyUsedNameServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年8月10日 上午9:52:16 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.company;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.company.CompanyUsedNameDao;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.company.CompanyUsedName;
import com.srd.ljzd.service.company.CompanyUsedNameService;

/** 
 * @ClassName: CompanyUsedNameServiceImpl
 * @Description: 企业曾用名Service实现类
 * @author shiyong
 * @date 2017年8月10日 上午9:52:16
 *  
 */
@Service("companyUsedNameService")
public class CompanyUsedNameServiceImpl implements CompanyUsedNameService {

	@Autowired
	private CompanyUsedNameDao companyUsedNameDao;
	
	@Override
	public ResultBean saveCompanyUsedName(String companyName) {
		
		ResultBean result = new ResultBean();
		
		CompanyUsedName companyUsedName = new CompanyUsedName();
		
		companyUsedName.setCompanyName(companyName);
		companyUsedName.setCreateTime(new Date());
		
		boolean flag = companyUsedNameDao.save(companyUsedName);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("保存企业曾用名成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("保存企业曾用名失败！");
		}
		
		return result;
	}
	
}
