package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupService;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.StringUtils;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：动态监控公司列表页
 * 类名称：monitor_events  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月18日上午11:06:04
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */

@Controller
@RequestMapping("/monitorEvents")
public class MonitorEventsController {

	@Autowired
	MonitorCompanyService monitorCompanyService;
	@Autowired
	MonitorGroupService monitorGroupService;
	@Autowired
	MonitorGroupCompanyService monitorGroupCompanyService;
	@Autowired
	private ClientAccountService clientAccountService;
	
	protected static Logger log = LogManager.getLogger(MonitorGroupController.class.getName());

	private static final String UN_GROUPED="0";
	private static final String GROUPED="1";
	
	/**
	 * 
		* @Title: getMonitorGroups
		* @Description: 获取动态监控分组列表
		* @param  @param request
		* @param  @param response
		* @param  @return
		* @return List<MonitorGroup>
		* @author jiang.zhou
		* @throws
		* @date 下午4:14:13
	 */
	@ResponseBody
	@RequestMapping("/getMonitorGroups.do")
	List getMonitorGroupList(String showGroupType,String currentAccountId,HttpServletRequest request,HttpServletResponse response){
		
		ClientAccount loginAccount = (ClientAccount) request.getSession().getAttribute("account");
		if(loginAccount==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		List<MonitorGroup> target  = null;
		if(StringUtils.isNotEmpty(showGroupType)&&"ACCOUNT".equals(showGroupType)){
			List<ClientAccount> accountList = clientAccountService.getChildAccountListByParentId(currentAccountId);
			if(accountList!=null&&accountList.size()>0){
				target = new ArrayList<MonitorGroup>();
				target.add(new MonitorGroup("","全部"));
				ClientAccount currentAccount = clientAccountService.getClientAccountInfoById(currentAccountId);
				target.add(new MonitorGroup(currentAccount.getAccountId(),currentAccount.getAccountName()));
				for(ClientAccount acc : accountList){
					target.add(new MonitorGroup(acc.getAccountId(),acc.getAccountName()));
				}
			}else{
				//如果没有子账户，依然显示真实分组
				target = monitorGroupService.queryMonitorGroupListByAccount(currentAccountId);
			}
		}else{
			target = monitorGroupService.queryMonitorGroupListByAccount(currentAccountId);
		}
		return target;
	}
	
	
	@RequestMapping("/addMonitorGroup.do")
	@ResponseBody
	public ResultBean addMonitorGroup(HttpServletRequest request, HttpServletResponse response){
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
	
		String groupName = request.getParameter("groupName");
		String remark = request.getParameter("remark");
		try {
			groupName = URLDecoder.decode(groupName, "UTF-8");
			remark = URLDecoder.decode(remark, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		MonitorGroup monitorGroup = new MonitorGroup();
		monitorGroup.setGroupName(groupName);
		monitorGroup.setClientAccount(account);
		monitorGroup.setRemark(remark);
		monitorGroup.setCanDeleteFlag("1");
		monitorGroup.setCompanyNum(0);
		monitorGroup.setCreateTime(new Date());
		monitorGroup.setModifyTime(new Date());
		monitorGroup.setDeleteFlag("0");
		
		ResultBean result = monitorGroupService.addMonitorGroup(monitorGroup);
		
		return result;
	}
	
	/**
	 * 
		* @Title: batchDelete
		* @Description: 批量删除分组中的公司
		* @param  @param request
		* @param  @param response
		* @param  @return
		* @return ResultBean
		* @author jiang.zhou
		* @throws
		* @date 下午1:30:12
	 */
	@ResponseBody
	@RequestMapping("/batchDelete.do")
	public ResultBean batchDelete(HttpServletRequest request,HttpServletResponse response){

		String companyListStr=request.getParameter("companyListStr");
		String groupId=request.getParameter("groupId");
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId=account.getAccountId();

		List<String> monitorIdList=new ArrayList<String>();
		
		if(companyListStr!=null){
			
			String [] comArr=companyListStr.split(",");
			monitorIdList=Arrays.asList(comArr);
		}
		
		Boolean result=monitorGroupCompanyService.deleteBatch(groupId, monitorIdList);
		
		//获取所有的分组
		List<MonitorGroup> monitorGroupList=monitorGroupService.queryMonitorGroupListByAccount(accountId);
		List<String> groupIdList=new ArrayList<String>();
		
		for(int i=0;i<monitorGroupList.size();i++){
			if(!"全部".equals(monitorGroupList.get(i).getGroupName())){
				groupIdList.add(monitorGroupList.get(i).getGroupId());
			}
		}
		for(int i=0;i<monitorIdList.size();i++){
			//查看对应分组中是否有对应的公司
			Boolean flag=monitorGroupCompanyService.getGroupCompanyList(groupIdList, monitorIdList.get(i));
			if(flag==false){
				//如果没有那么需要改变下对应的状态
				monitorCompanyService.updateGroupStatus( monitorIdList.get(i), UN_GROUPED);
			}
		}
		
		ResultBean resultBean = new ResultBean();
		if(result){
			resultBean.setResultCode("0");
			resultBean.setResultMsg("批量删除成功！");
		}else{
			resultBean.setResultCode("1");
			resultBean.setResultMsg("批量删除失败！");
		}
		return resultBean;
	}
	
	/**
	 * 
		* @Title: deleteGroup
		* @Description: 删除分组
		* @param  @param request
		* @param  @param response
		* @param  @return
		* @return ResultBean
		* @author jiang.zhou
		* @throws
		* @date 上午11:37:59
	 */
	@ResponseBody
	@RequestMapping("/deleteGroup.do")
	public ResultBean deleteGroup(HttpServletRequest request,HttpServletResponse response){
		
		String groupId=request.getParameter("groupId");
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId=account.getAccountId();
		//删除分组之前需要获取该分组下所有公司名称
		List<MonitorCompany> comList=monitorCompanyService.getGroupCompanyList(groupId);
		//删除关联的公司以及个人之后才能删除组
		monitorGroupCompanyService.deleteMonitorReal(groupId);
		//删除对应分组
		ResultBean result=	monitorGroupService.deleteMonitorGroupById(groupId);		
		//获取所有的分组
		List<MonitorGroup> monitorGroupList=monitorGroupService.queryMonitorGroupListByAccount(accountId);
		List<String> groupIdList=new ArrayList<String>();
				
		for(int i=0;i<monitorGroupList.size();i++){
			if(!"全部".equals(monitorGroupList.get(i).getGroupName())){
				groupIdList.add(monitorGroupList.get(i).getGroupId());
			}
		}
		for(int i=0;i<comList.size();i++){
			
			if(monitorGroupList.size()==1){
				//如果只有一个分组那么直接进行取消
				monitorCompanyService.updateGroupStatus(comList.get(i).getMonitorID(), UN_GROUPED);
			}else{
				//查看对应分组中是否有对应的公司
				Boolean flag=monitorGroupCompanyService.getGroupCompanyList(groupIdList, comList.get(i).getMonitorID());
				if(flag==false){
				//如果没有那么需要改变下对应的状态
				monitorCompanyService.updateGroupStatus(comList.get(i).getMonitorID(), UN_GROUPED);
				}
			}
		}
		return result;
	}
	/**
	 * 
		* @Title: updateGroupName
		* @Description: 更新组名
		* @param  @param request
		* @param  @param response
		* @param  @return
		* @return ResultBean
		* @author jiang.zhou
		* @throws
		* @date 下午4:14:09
	 */
	@ResponseBody
	@RequestMapping("/updateGroupName.do")
	public ResultBean updateGroupName(HttpServletRequest request,HttpServletResponse response){
		
		String groupId = request.getParameter("groupId");
		String groupName = request.getParameter("groupName");
		String description = request.getParameter("description");
		
		try {
			if(StringUtils.isNotEmpty(groupName)){
				groupName = URLDecoder.decode(groupName, "UTF-8");
			}
			if(StringUtils.isNotEmpty(description)){
				description = URLDecoder.decode(description, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		
		MonitorGroup monitorGroup= monitorGroupService.getMonitorGroupById(groupId);
		monitorGroup.setGroupName(groupName);
		monitorGroup.setRemark(description);
		
		return monitorGroupService.updateGroup(monitorGroup);

	}
}
