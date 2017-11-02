/**   
* @Title: MonitorCompanyDistributionServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月16日 上午10:37:23 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorCompanyDistributionDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.service.monitor.MonitorCompanyDistributionService;

/** 
 * @ClassName: MonitorCompanyDistributionServiceImpl
 * @Description: 动态监控风险公司分布信息Service实现
 * @author shiyong
 * @date 2016年11月16日 上午10:37:23
 *  
 */
@Service("monitorCompanyDistributionService")
public class MonitorCompanyDistributionServiceImpl implements MonitorCompanyDistributionService{

	@Autowired
	private MonitorCompanyDistributionDao monitorCompanyDistributionDao;
	
	@Override
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(String reportId) {
		
		List<MonitorCompanyDistribution> list = monitorCompanyDistributionDao.queryMonitorCompanyDistributionList(reportId);
		
		String province = "";
		
		for(MonitorCompanyDistribution monitorCompanyDistribution : list){
			province = monitorCompanyDistribution.getAreaName();
			
			province = province.replaceAll("省", "");
			province = province.replaceAll("市", "");
			province = province.replaceAll("壮族", "");
			province = province.replaceAll("回族", "");
			province = province.replaceAll("维吾尔", "");
			province = province.replaceAll("自治区", "");
			
			monitorCompanyDistribution.setAreaName(province);
		}
		
		return list;
	}
	
}
