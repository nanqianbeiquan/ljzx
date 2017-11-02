/**   
* @Title: MonitorDefaultCompanyServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月6日 下午8:37:57 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorDefaultCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorDefaultGroupDao;
import com.srd.ljzd.dao.monitor.MonitorGroupCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorGroupDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorDefaultCompany;
import com.srd.ljzd.entity.monitor.MonitorDefaultGroup;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.entity.monitor.MonitorGroupCompany;
import com.srd.ljzd.service.monitor.MonitorDefaultCompanyService;
import com.srd.ljzd.serviceImpl.base.BaseServiceImpl;
import com.srd.ljzd.util.LoggerUtils;

/** 
 * @ClassName: MonitorDefaultCompanyServiceImpl
 * @Description: 默认动态监控公司Service实现类
 * @author shiyong
 * @date 2017年9月6日 下午8:37:57
 *  
 */
@Service("monitorDefaultCompanyService")
public class MonitorDefaultCompanyServiceImpl extends BaseServiceImpl implements MonitorDefaultCompanyService {
	@Autowired
	private MonitorDefaultGroupDao monitorDefaultGroupDao;
	
	@Autowired
	private MonitorDefaultCompanyDao monitorDefaultCompanyDao;
	
	@Autowired
	private MonitorGroupDao monitorGroupDao;
	
	@Autowired
	private MonitorGroupCompanyDao monitorGroupCompanyDao;
	
	@Autowired
	private MonitorCompanyDao monitorCompanyDao;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public ResultBean initDefaultGroupAndCompany(String accountId) {
		ResultBean result = new ResultBean();
		
		boolean flag = false;
		
		ClientAccount clientAccount = new ClientAccount();
		clientAccount.setAccountId(accountId);
		
		//初始化默认监控分组
		List<MonitorDefaultGroup> monitorDefaultGroupList = monitorDefaultGroupDao.getMonitorDefaultGroupList();
		
		MonitorGroup monitorGroup = null;
		
//		MonitorGroup groupOfAll = new MonitorGroup();
		
		for (MonitorDefaultGroup monitorDefaultGroup : monitorDefaultGroupList) {
			monitorGroup = new MonitorGroup();
			monitorGroup.setClientAccount(clientAccount);
			monitorGroup.setGroupName(monitorDefaultGroup.getGroupName());
			monitorGroup.setCanDeleteFlag(monitorDefaultGroup.getCanDeleteFlag());
			monitorGroup.setSort(monitorDefaultGroup.getSort());
			monitorGroup.setIcon(monitorDefaultGroup.getIcon());
			monitorGroup.setRemark("");
			monitorGroup.setCreateTime(monitorDefaultGroup.getCreateTime());
			monitorGroup.setModifyTime(monitorDefaultGroup.getModifyTime());
			monitorGroup.setDeleteFlag("0");
			
			flag = monitorGroupDao.save(monitorGroup);
			
			if(!flag){
				result.setResultCode("1");
				result.setResultMsg("初始化默认监控分组失败！");
				
				return result;
			}
			
//			if("全部".equals(monitorGroup.getGroupName())){
//				groupOfAll = monitorGroup;
//			}
		}
		
		//初始化默认监控企业
//		List<MonitorDefaultCompany> monitorDefaultCompanyList = monitorDefaultCompanyDao.getMonitorDefaultCompanyList();
//		
//		MonitorCompany monitorCompany = null;
//		
//		MonitorGroupCompany monitorGroupCompany = null;
		
//		for (MonitorDefaultCompany monitorDefaultCompany : monitorDefaultCompanyList) {
//			monitorCompany = new MonitorCompany();
//			monitorCompany.setClientAccount(clientAccount);
//			monitorCompany.setCompanyId(monitorDefaultCompany.getCompanyBasicInfo().getCompanyId());
//			monitorCompany.setCompanyName(monitorDefaultCompany.getCompanyBasicInfo().getCompanyName());
//			monitorCompany.setProvince(monitorDefaultCompany.getCompanyBasicInfo().getProvince());
//			monitorCompany.setAreaName(monitorDefaultCompany.getCompanyBasicInfo().getAreaName());
//			monitorCompany.setRiskSituation("");
//			monitorCompany.setCustomRiskFlag("0");
//			monitorCompany.setCustomRiskSituation("");
//			monitorCompany.setRiskStatus("");
//			monitorCompany.setRiskLabel("");
//			monitorCompany.setCustomRiskLabel("");
//			monitorCompany.setNewEventFlag("");
//			monitorCompany.setEventLevel("");
//			monitorCompany.setNormalEventNum(0);
//			monitorCompany.setAttentionEventNum(0);
//			monitorCompany.setWarnEventNum(0);
//			monitorCompany.setGroupStatus("0");
//			monitorCompany.setRenameStatus("0");
//			monitorCompany.setMonitorDate(new Date());
//			
//			try {
//				monitorCompany.setInfoShowDate(sdf.parse("2015-12-01 00:00:00"));
//			} catch (ParseException e) {
//				LoggerUtils.error("初始化默认监控企业时，事件展示时间格式化失败，", e);
//			}
//			
//			monitorCompany.setRiskResult("");
//			monitorCompany.setCustomRiskResult("");
//			monitorCompany.setEventCheckFlag("");
//			monitorCompany.setCompanyBackgroundIndex(0);
//			monitorCompany.setJudicialLitigationIndex(0);
//			monitorCompany.setOperateExceptionIndex(0);
//			monitorCompany.setNegativeNewsIndex(0);
//			monitorCompany.setDevelopmentPotentialIndex(0);
//			monitorCompany.setCreateTime(new Date());
//			monitorCompany.setLastModifiedTime(new Date());
//			
//			flag = monitorCompanyDao.save(monitorCompany);
//			
//			if(!flag){
//				result.setResultCode("1");
//				result.setResultMsg("初始化默认监控企业失败！");
//				
//				return result;
//			}
//			
//			monitorGroupCompany = new MonitorGroupCompany();
//			monitorGroupCompany.setMonitorGroup(groupOfAll);
//			monitorGroupCompany.setMonitorCompany(monitorCompany);
//			monitorGroupCompany.setCreateTime(new Date());
//			
//			flag = monitorGroupCompanyDao.save(monitorGroupCompany);
//			
//			if(!flag){
//				result.setResultCode("1");
//				result.setResultMsg("初始化默认监控企业与分组关系失败！");
//				
//				return result;
//			}
//		}
		
		result.setResultCode("0");
		result.setResultMsg("初始化默认监控分组和企业成功！");
		
		return result;
	}
}
