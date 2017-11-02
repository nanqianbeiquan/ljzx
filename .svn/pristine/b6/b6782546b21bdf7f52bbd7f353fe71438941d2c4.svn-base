/**   
* @Title: MonitorUserConfigureServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月19日 下午3:51:50 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorUserConfigureDao;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorUserConfigure;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.entity.client.ClientAccount;

/** 
 * @ClassName: MonitorUserConfigureServiceImpl
 * @Description: 动态监控用户设置信息Service实现类
 * @author shiyong
 * @date 2016年11月19日 下午3:51:50
 *  
 */
@Service("monitorUserConfigureService")
public class MonitorUserConfigureServiceImpl implements MonitorUserConfigureService{

	@Autowired
	private MonitorUserConfigureDao monitorUserConfigureDao;

	@Override
	public ResultBean saveMonitorUserConfigure(MonitorUserConfigure monitorUserConfigure) {
		ResultBean result = new ResultBean();
		
		boolean flag = monitorUserConfigureDao.save(monitorUserConfigure);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("保存动态监控用户设置信息成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("保存动态监控用户设置信息失败！");
		}
		
		return result;
	}

	@Override
	public ResultBean updateMonitorUserConfigure(MonitorUserConfigure monitorUserConfigure) {
		ResultBean result = new ResultBean();
		
		boolean flag = monitorUserConfigureDao.update(monitorUserConfigure);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("更新动态监控用户设置信息成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("更新动态监控用户设置信息失败！");
		}
		
		return result;
	}

	@Override
	public MonitorUserConfigure getMonitorUserConfigureByAccountId(String accountId) {
		
		return monitorUserConfigureDao.getMonitorUserConfigureByAccountId(accountId);
	}

	@Override
	public List<String> getUserAttentionEventClass(String accountId) {
		List<String> target = new ArrayList<String>();
		//获取该账号动态监控配置
		MonitorUserConfigure userConf = monitorUserConfigureDao.getMonitorUserConfigureByAccountId(accountId);
		if(userConf!=null&&userConf.getAttentionEventClass()!=null){
			String attentionEventClass = userConf.getAttentionEventClass();
			if("1".equals(attentionEventClass.substring(0, 1))){
				target.add("01");
			}
			if("1".equals(attentionEventClass.substring(1, 2))){
				target.add("02");
			}
			if("1".equals(attentionEventClass.substring(2, 3))){
				target.add("03");
			}
			if("1".equals(attentionEventClass.substring(3, 4))){
				target.add("04");
			}
			if("1".equals(attentionEventClass.substring(4, 5))){
				target.add("05");
			}
			if("1".equals(attentionEventClass.substring(5, 6))){
				target.add("06");
			}
			if("1".equals(attentionEventClass.substring(6, 7))){
				target.add("07");
			}
			if("1".equals(attentionEventClass.substring(7, 8))){
				target.add("08");
			}
			if("1".equals(attentionEventClass.substring(8, 9))){
				target.add("09");
			}
			if("1".equals(attentionEventClass.substring(9, 10))){
				target.add("10");
			}
			if("1".equals(attentionEventClass.substring(10, 11))){
				target.add("11");
			}
			if("1".equals(attentionEventClass.substring(11, 12))){
				target.add("12");
			}
			if("1".equals(attentionEventClass.substring(12, 13))){
				target.add("13");
			}
			if("1".equals(attentionEventClass.substring(13, 14))){
				target.add("14");
			}
			if("1".equals(attentionEventClass.substring(14, 15))){
				target.add("15");
			}
			if("1".equals(attentionEventClass.substring(15, 16))){
				target.add("16");
			}
			if("1".equals(attentionEventClass.substring(16, 17))){
				target.add("17");
			}
			if("1".equals(attentionEventClass.substring(17, 18))){
				target.add("18");
			}
			if("1".equals(attentionEventClass.substring(18, 19))){
				target.add("19");
			}
			if("1".equals(attentionEventClass.substring(19, 20))){
				target.add("20");
			}
			if("1".equals(attentionEventClass.substring(20, 21))){
				target.add("21");
			}
			if("1".equals(attentionEventClass.substring(21, 22))){
				target.add("22");
			}
			if("1".equals(attentionEventClass.substring(22, 23))){
				target.add("23");
			}
			if("1".equals(attentionEventClass.substring(23, 24))){
				target.add("24");
			}
			if("1".equals(attentionEventClass.substring(24, 25))){
				target.add("25");
			}
			if("1".equals(attentionEventClass.substring(25, 26))){
				target.add("27");
			}
			if("1".equals(attentionEventClass.substring(26, 27))){
				target.add("28");
			}
			if("1".equals(attentionEventClass.substring(27))){
				target.add("29");
			}
		}
		
		return target;
	}
	
	@Override
	public ResultBean initMonitorUserConfigure(String accountId) {
		ClientAccount account = new ClientAccount();
		account.setAccountId(accountId);
		
		MonitorUserConfigure monitorUserConfigure = new MonitorUserConfigure();
		monitorUserConfigure.setClientAccount(account);
		monitorUserConfigure.setAttentionEventClass("1111111111111111111111111111");
		monitorUserConfigure.setMonitorCycle("1");
		monitorUserConfigure.setNotifyType("1");
		monitorUserConfigure.setEmails("");
		monitorUserConfigure.setCreateTime(new Date());
		monitorUserConfigure.setModifyTime(new Date());
		
		boolean flag = monitorUserConfigureDao.save(monitorUserConfigure);
		
		ResultBean result = new ResultBean();
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("初始化动态监控用户设置成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("初始化动态监控用户设置失败！");
		}
		
		return result;
	}
	
	
}
