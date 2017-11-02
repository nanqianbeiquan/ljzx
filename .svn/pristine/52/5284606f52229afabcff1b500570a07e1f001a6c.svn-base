package com.srd.ljzd.service.relationCompanyAndPerson;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.entity.relationCompanyAndPerson.RelationCompanyAndPerson;


/**   
 * 版权所有：2017
 * 项目名称：ljzx   
 *
 * 类描述：增加关联关系服务类；
 * 类名称：com.srd.ljzd.service.AddRelationCompanyAndPersonService     
 * 创建人：裴子辉
 * 创建时间：2017年1月16日 上午10:46:13   
 * 修改人：
 * 修改时间：2017年1月16日 上午10:46:13   
 * 修改备注：   
 * @version   V1.3    
 */
  
public interface AddRelationCompanyAndPersonService {
	
	/**
	* @Title: queryAllRelationsList
	* @Description: TODO(查询全部关联)
	* @param  @param companyName
	* @param  @return  
	* @return Map<String,List<RelationCompanyAndPerson>>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月23日 上午9:53:22
	*/
	public Map<String, List<RelationCompanyAndPerson>> queryAllRelationsList(String companyName) ;
	
	/**
	* @Title: saveRelationCompanyAndPersonListToCache
	* @Description: TODO(保存缓存)
	* @param  @param companyName
	* @param  @param map  
	* @return void    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月23日 上午9:53:16
	*/
	public void saveRelationCompanyAndPersonListToCache(String companyName, Map<String, List<RelationCompanyAndPerson>> map); 
	
	/**
	* @Title: getRelationCompanyAndPersonListFromCache
	* @Description: TODO(从缓存中获结果)
	* @param  @param companyName
	* @param  @param map
	* @param  @return  
	* @return Map<String,List<RelationCompanyAndPerson>>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月23日 上午9:53:13
	*/
	public  Map<String, List<RelationCompanyAndPerson>> getRelationCompanyAndPersonListFromCache(String companyName);

}
