/**   
* @Title: CompanyInfoService.java 
* @Package com.srd.ljzd.service.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月16日 下午5:51:26 
* @version V1.0   
*/
package com.srd.ljzd.service.company;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.company.RegisterInfo;
import com.srd.ljzd.util.Page;


/** 
 * @ClassName: CompanyInfoService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月16日 下午5:51:26
 *  
 */
public interface CompanyInfoService {
	
	/** 
	* @Title: queryCompanyInfoList 
	* @Description: 根据关键字搜索公司
	* @param @param keyword（关键字）
	* @param @param resultNum（结果中返回公司的数量）
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月17日 下午1:28:11
	*/
	public Map<String, Object> queryCompanyInfoList(String keyword, int resultNum);
	
	/** 
	* @Title: queryCompanyInfoListUnion 
	* @Description: 根据关键字联合查询公司
	* @param @param keyword
	* @param @param resultNum
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月19日 上午9:59:19
	*/
	public Map<String, Object> queryCompanyInfoListUnion(String keyword, int resultNum);
	
	
	/** 
	* @Title: saveCompanyInfoListToCache 
	* @Description: 保存公司列表信息到缓存中
	* @param @param keyword
	* @param @param companyInfoList 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月27日 上午11:31:25
	*/
	public void saveCompanyInfoListToCache(String keyword, Map<String, Object> resultMap);

	/** 
	* @Title: getCompanyInfoListFromCache 
	* @Description: 从缓存中获取公司列表信息
	* @param @param keyword
	* @param @return 设定文件 
	* @return List<CompanyInfo> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月27日 上午11:31:38
	*/
	public List<CompanyInfo> getCompanyInfoListFromCache(String keyword);
	
	/** 
	* @Title: getProvinceOfCompany 
	* @Description: 获取公司省份
	* @param @param companyName
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月18日 下午5:25:33
	*/
	public String getProvinceOfCompany(String companyName);
	
	/** 
	* @Title: getProvinceOfCompanyFromUnion 
	* @Description: 从自由数据或外部数据中获取公司省份
	* @param @param companyName
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月19日 上午10:36:03
	*/
	public String getProvinceOfCompanyFromUnion(String companyName);
	
	/** 
	* @Title: getCompanyRegisterInfo 
	* @Description: 获取公司登记信息
	* @param @param companyName
	* @param @return 设定文件 
	* @return CompanyInfo 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月21日 下午2:18:10
	*/
	public CompanyInfo getCompanyRegisterInfo(String companyName);
	
	/** 
	* @Title: getCompanyRegisterInfoFromUnion 
	* @Description: 从自由数据或外部数据中获取公司登记信息
	* @param @param companyName
	* @param @return 设定文件 
	* @return CompanyInfo 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月19日 上午10:44:01
	*/
	public CompanyInfo getCompanyRegisterInfoFromUnion(String companyName);
	
	CompanyInfo getCompanyRegisterInfoQiChaCha(String companyName);
	
	/** 
	* @Title: convertCompanyInfoFromXml 
	* @Description: 从XML中转换公司信息
	* @param @param xml
	* @param @return 设定文件 
	* @return CompanyInfo 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月19日 上午10:45:18
	*/
	public CompanyInfo convertCompanyInfoFromXml(String xml);
	
	/** 
	* @Title: convertRegisterInfoFromXml 
	* @Description: 从XML中转换公司基本信息
	* @param @param basic
	* @param @return 设定文件 
	* @return RegisterInfo 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月19日 上午11:45:29
	*/
	public RegisterInfo convertRegisterInfoFromXml(Element basic);
	
	
	
	/**
	* @Title: getCompanyInfoListFromCacheByNum
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param  @param keyword
	* @param  @param num
	* @param  @return  
	* @return List<CompanyInfo>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月22日 下午5:24:52
	*/
	public Map<String, Object>  getCompanyInfoListFromCacheByNum(String keyword,int num);
/**
 * 构造companyInfo 对象的Page对象
 */
	public Page<Map<String,Object>> buildCompanyInfoPage(
			List<CompanyInfo> companyInfoList, Integer pageNo, Integer pageSize);
	
	public CompanyInfo buildCompanyInfoFromMultiChannel(String companyName);
/**
 * 曾用名
 * @param companyName
 * @return
 */
	public JSONArray getUsedName(String companyName);
}
