package com.srd.ljzd.dao.monitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.util.Page;

/** 
* @ClassName: MonitorRelationPersonDao
* @Description: 动态监控关联个人信息Dao接口
* @author shiyong
* @date 2017年5月15日 上午11:45:54
*  
*/
public interface MonitorRelationPersonDao extends BaseDao<MonitorRelationPerson,String>{
	
	/** 
	* @Title: queryMonitorRelationPersonListByMonitorId 
	* @Description: 根据主体企业监控id获取关系个人列表
	* @param @param monitorId
	* @param @return 设定文件 
	* @return List<MonitorRelationPerson> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月15日 上午11:46:43
	*/
	public List<MonitorRelationPerson> queryMonitorRelationPersonListByMonitorId(String monitorId, Date monitorDate);
	
	/** 
	* @Title: getMonitorRelationPersonPage 
	* @Description: 获取关系自然人分页
	* @param @param monitorId
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorRelationPerson> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午10:15:34
	*/
	public Page<MonitorRelationPerson> getMonitorRelationPersonPage(String monitorId, int currentPageNo, int pageSize);
	
	/**
	 * 
		* @Title: addRealPerson
		* @Description: 追加关联个人信息
		* @param  @param person
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 下午5:13:22
	 */
	boolean addRealPerson(MonitorRelationPerson person);
	
	/** 
	* @Title: deleteMonitorRelationPersonByMonitorId 
	* @Description: 根据主体企业id删除关系自然人
	* @param @param monitorId
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午4:10:51
	*/
	public boolean deleteMonitorRelationPersonByMonitorId(String monitorId);
	
	/** 
	* @Title: deleteMonitorRelationPersonById 
	* @Description: 根据id删除关系自然人
	* @param @param id
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月20日 下午5:35:36
	*/
	public boolean deleteMonitorRelationPersonById(String id);
	
	/**
	 * 
		* @Title: getReaPersonInfo
		* @Description: 获取关联个人的相关信息
		* @param  @param monitorRelationPerson
		* @param  @return
		* @return MonitorRelationPerson
		* @author jiang.zhou
		* @throws
		* @date 下午3:00:08
	 */
	public MonitorRelationPerson getReaPersonInfo(MonitorRelationPerson monitorRelationPerson);
	
	/** 
	* @Title: getRelationPersonNum 
	* @Description: 获取关系自然人数量
	* @param @param monitorCompanyList
	* @param @return 设定文件 
	* @return Map<String,Integer> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月13日 下午1:34:54
	*/
	public Map<String, Integer> getRelationPersonNum(List<MonitorCompany> monitorCompanyList);
}
