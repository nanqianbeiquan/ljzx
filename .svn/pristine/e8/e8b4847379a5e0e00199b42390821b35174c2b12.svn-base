package com.srd.ljzd.dao.monitor;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.dto.monitor.MonitorCompanyDTO;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.util.Page;

/**
 * 
 * 版权所有：
 * 项目名称：lengjingzd2.0 
 *
 * 类描述：获取动态监控公司信息表信息
 * 类名称：com.srd.ljzd.dao.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月1日下午5:49:09
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorCompanyDao extends BaseDao<MonitorCompany,String>{

	
	/** 
	* @Title: queryRecentMonitorCompanyList 
	* @Description: 获取最近加入动态监控的几家公司
	* @param @param num
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月16日 下午2:29:34
	*/
	public List<MonitorCompany> queryRecentMonitorCompanyList(String accountId, int num);
	
	/** 
	* @Title: getMonitorCompany 
	* @Description: 获取动态监控公司信息
	* @param @param monitorCompany
	* @param @return 设定文件 
	* @return MonitorCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 上午10:15:44
	*/
	public MonitorCompany getMonitorCompany(MonitorCompany monitorCompany);
	
	public MonitorCompany getMonitorCompany(String accountId,String companyName);
	
	/**
	 * 
		* @Title: getMonitorGroupComInfo
		* @Description: TODO
		* @param  @param accountId
		* @param  @param groupName
		* @param  @param currentPageNo
		* @param  @param pageSize
		* @param  @return
		* @return Page<MonitorCompany>
		* @author jiang.zhou
		* @throws
		* @date 上午10:51:22
	 */
	public Page<MonitorCompany> getMonitorGroupComInfo(String sysAccount,String groupName,int currentPageNo, int pageSize);
	
	
 	/**
 	 * 
	* @Title: getMonitorComListByAccId
	* @Description: 获取该账号下对应名字的公司信息
	* @param  @param accountId
	* @param  @param nameList
	* @param  @return
	* @return List<MonitorCompany>
	* @author jiang.zhou
	* @throws
	* @date 下午3:35:02
	*/
	public List<MonitorCompany> getMonitorComListByAccId(String accountId,List<String> nameList);
	
	/** 
	* @Title: queryTodayMonitorCompany 
	* @Description: 查询今日加入动态监控的公司信息
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月3日 上午11:33:15
	*/
	public List<MonitorCompany> queryTodayMonitorCompany(String accountId);
	
	/** 
	* @Title: queryTodayMonitorCompanyNum 
	* @Description: 获取今日加入监控的企业数
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 下午7:40:16
	*/
	public int queryTodayMonitorCompanyNum(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryMonitorCompanyNum 
	* @Description: 根据父ID获取父账号和子账号监控企业数
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月29日 下午4:57:09
	*/
	public int queryMonitorCompanyNum(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryMonitorCompanyNumByAccountId 
	* @Description: 获取账号监控主体企业数
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月29日 下午5:11:10
	*/
	public int queryMonitorCompanyNumByAccountId(String accountId);
	
	/** 
	* @Title: findTodayMonitorCompanyPage 
	* @Description: 查询今日加入动态监控的企业页面
	* @param @param monitorCompany
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月9日 下午3:48:29
	*/
	public Page<MonitorCompany> findTodayMonitorCompanyPage(MonitorCompany monitorCompany, int currentPageNo, int pageSize);
	
	/** 
	* @Title: findMonitorCompanyPage 
	* @Description: 获取监控企业风险分页 
	* @param @param monitorCompany
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorCompanyRiskDTO> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月27日 上午11:13:37
	*/
	public Page<MonitorCompany> findMonitorCompanyPage(MonitorCompanyDTO monitorCompanyRiskDTO, int currentPageNo, int pageSize,String sortAttr,boolean isDesc);
	
	/** 
	* @Title: getMonitorCompanyList 
	* @Description: 根据条件获取动态监控企业列表
	* @param @param monitorCompanyRiskDTO
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午7:51:11
	*/
	public List<MonitorCompany> getMonitorCompanyList(MonitorCompanyDTO monitorCompanyRiskDTO);
	
	/**
	* @Title: queryCompanyByName
	* @Description: TODO(根据名称查询企业)
	* @param @param name
	* @param @param accountId
	* @param @return    设定文件
	* @return MonitorCompany    返回类型
	* @throws
	  @date 2016年6月1日 下午2:30:48
	*/
	public MonitorCompany queryCompanyByName(String name, String accountId);

	/**
	 * 更新分组状态
		* @Title: updateGroupStatus
		* @Description: TODO
		* @param  @param monitorId
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 上午10:02:41
	 */
	public boolean updateGroupStatus(String monitorId,String groupStatus);
	
	
	public List<MonitorCompany> getGroupCompanyList(String groupId);

	/** 
	* @Title: queryMonitorCompanySituation 
	* @Description: 查询监控企业概况
	* @param @param accountId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月25日 上午11:42:13
	*/
	public Map<String, Object> queryMonitorCompanySituation(String accountId);
	
	/** 
	* @Title: queryMonitorCompanySituation 
	* @Description: 根据父账号ID获取父账号和下一级子账号的监控概况
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 下午3:05:42
	*/
	public Map<String, Object> queryMonitorCompanySituation(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryMonitorCompanyDistributionList 
	* @Description: 查询企业分布情况列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorCompanyDistribution> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月26日 下午3:18:39
	*/
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(String accountId);
	
	/** 
	* @Title: queryMonitorCompanyDistributionList 
	* @Description: 获取账号列表中账号监控企业的分布情况列表
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return List<MonitorCompanyDistribution> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月12日 上午9:20:42
	*/
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryNewEventCompanyList 
	* @Description: 获取有新事件的企业信息列表
	* @param @param accountId
	* @param @param num
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午6:21:16
	*/
	public List<MonitorCompany> queryNewEventCompanyList(String accountId, int num);
	
	/** 
	* @Title: getRiskSituationList 
	* @Description: 获取账号列表中账号的风险状况
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return List<Map<String,Object>> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月12日 上午10:12:52
	*/
	public List<Map<String, Object>> getRiskSituationList(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: getSelfRiskSituation 
	* @Description: 获取账号自身的风险状况
	* @param @param accountId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月14日 下午2:27:29
	*/
	public Map<String, Object> getSelfRiskSituation(String accountId);
	
	/** 
	* @Title: getRiskSituationByParent 
	* @Description: 获取账号自身和所有子孙账号的风险状况
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月14日 下午2:27:32
	*/
	public Map<String, Object> getRiskSituationByParent(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: deleteMonitorCompanyById 
	* @Description: 根据ID逻辑删除监控企业
	* @param @param monitorId
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月20日 下午5:19:06
	*/
	public boolean deleteMonitorCompanyById(String monitorId);
}
