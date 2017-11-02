/**   
* @Title: CompanyUsedNameService.java 
* @Package com.srd.ljzd.service.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年8月10日 上午9:49:56 
* @version V1.0   
*/
package com.srd.ljzd.service.company;

import com.srd.ljzd.entity.common.ResultBean;

/** 
 * @ClassName: CompanyUsedNameService
 * @Description: 企业曾用名Service接口
 * @author shiyong
 * @date 2017年8月10日 上午9:49:56
 *  
 */
public interface CompanyUsedNameService {
	/** 
	* @Title: saveCompanyUsedName 
	* @Description: 保存企业曾用名
	* @param @param companyName
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月10日 上午9:54:09
	*/
	public ResultBean saveCompanyUsedName(String companyName);
}
