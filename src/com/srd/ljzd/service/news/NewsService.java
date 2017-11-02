package com.srd.ljzd.service.news;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.entity.news.NewsOpinion;
import com.srd.ljzd.util.Page;


/**   
 * 版权所有：2017
 * 项目名称：ljzx   
 *
 * 类描述： 新闻舆情service
 * 类名称：com.srd.ljzd.service.news.NewsService     
 * 创建人：裴子辉
 * 创建时间：2017年1月3日 上午9:52:24   
 * 修改人：
 * 修改时间：2017年1月3日 上午9:52:24   
 * 修改备注：   
 * @version   V1.3    
 */
  
public interface NewsService {

	/**
	* @Title: queryNewsDetails
	* @Description: TODO()
	* @param  @param newsKey
	* @param  @param companyName
	* @param  @return  
	* @return NewsOpinion    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月3日 上午9:51:49
	*/
	public NewsOpinion queryNewsDetails(String newsKey,String newsid, String companyName,String opinionType);

	

	/**
	* @Title: queryAllRiskList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param  @param companyName
	* @param  @return  
	* @return Map<String,List<NewsOpinion>>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月3日 上午9:52:04
	*/
	public Map<String, List<NewsOpinion>> queryAllOpinionList(String companyName);
	

	/**
	* @Title: getNewsNum
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param  @param companyName
	* @param  @return  
	* @return int    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月3日 上午9:52:12
	*/
	public int getNewsNum(String companyName);
	
	
	/**
	* @Title: queryNewsOpinionList
	* @Description: TODO(获取公司舆情)
	* @param  @param companyName
	* @param  @param currentPageNo
	* @param  @param pageSize
	* @param  @return  
	* @return Page<NewsOpinion>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月3日 上午10:24:08
	*/
	public Page<NewsOpinion> queryNewsOpinionList(String companyName,String type,Integer currentPageNo,Integer pageSize);
}
