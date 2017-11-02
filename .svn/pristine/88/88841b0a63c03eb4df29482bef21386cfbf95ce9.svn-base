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

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupService;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.StringUtils;

@Controller
@RequestMapping("/monitorGroup")
public class MonitorGroupController extends BaseController {
 
	@Autowired
	private MonitorGroupService monitorGroupService;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private MonitorGroupCompanyService monitorGroupCompanyService;
	
	protected static Logger log = LogManager.getLogger(MonitorGroupController.class.getName());
	
	/** 
	* @Title: queryMonitorGroupListByAccount 
	* @Description: 获取某个账号所有的动态监控分组
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月18日 上午11:30:54
	*/
	@RequestMapping("/queryMonitorGroupListByAccount")
	@ResponseBody
	public List<MonitorGroup> queryMonitorGroupListByAccount(HttpServletRequest request, HttpServletResponse response){
		
		String accountId = getAccountIdFromSession(request);
		
		List<MonitorGroup> monitorGroupList = monitorGroupService.queryMonitorGroupListByAccount(accountId);
		
		return monitorGroupList;
	}
	
	/** 
	* @Title: queryMonitorGroupListByAccountExceptAll 
	* @Description: 获取某个账号全部分组之外的所有动态监控分组
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return List<MonitorGroup> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月10日 下午4:56:49
	*/
	@RequestMapping("/queryMonitorGroupListByAccountExceptAll")
	@ResponseBody
	public List<MonitorGroup> queryMonitorGroupListByAccountExceptAll(HttpServletRequest request, HttpServletResponse response){
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		List<MonitorGroup> monitorGroupList = monitorGroupService.queryMonitorGroupListByAccountExceptAll(accountId);
		
		return monitorGroupList;
	}
	
	/** 
	* @Title: addMonitorGroup 
	* @Description: 新增动态监控分组 
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 下午1:27:27
	*/
	@RequestMapping("/addMonitorGroup")
	@ResponseBody
	public ResultBean addMonitorGroup(HttpServletRequest request, HttpServletResponse response){
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String groupName = request.getParameter("groupName").trim();
		String remark = request.getParameter("remark").trim();
		
		//判断分组名称是否已存在
		boolean isHave = monitorGroupService.isHaveGroup(account.getAccountId(), groupName);
		
		ResultBean result = new ResultBean();
		
		if(isHave){
			result.setResultCode("2");
			result.setResultMsg("分组名称已存在！");
		}else{
			MonitorGroup monitorGroup = new MonitorGroup();
			monitorGroup.setGroupName(groupName);
			monitorGroup.setClientAccount(account);
			monitorGroup.setRemark(remark);
			monitorGroup.setCanDeleteFlag("1");
			monitorGroup.setCompanyNum(0);
			monitorGroup.setCreateTime(new Date());
			monitorGroup.setModifyTime(new Date());
			monitorGroup.setDeleteFlag("0");
			
			result = monitorGroupService.addMonitorGroup(monitorGroup);
		}
		
		return result;
	}
	
	/** 
	* @Title: deleteMonitorGroup 
	* @Description: 删除动态监控分组 
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月5日 下午7:13:55
	*/
	@ResponseBody
	@RequestMapping("/deleteMonitorGroup")
	public ResultBean deleteMonitorGroup(HttpServletRequest request,HttpServletResponse response){
		String groupId = request.getParameter("groupId");
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		//删除分组之前需要获取该分组下所有公司名称
		List<MonitorCompany> comList = monitorCompanyService.getGroupCompanyList(groupId);
		
		//删除关联的公司以及个人之后才能删除组
		monitorGroupCompanyService.deleteMonitorReal(groupId);
		
		//删除对应分组
		ResultBean result = monitorGroupService.deleteMonitorGroupById(groupId);
		
		//获取所有的分组
		List<MonitorGroup> monitorGroupList=monitorGroupService.queryMonitorGroupListByAccount(accountId);
		List<String> groupIdList=new ArrayList<String>();
				
		for(int i=0;i<monitorGroupList.size();i++){
			if(!"全部".equals(monitorGroupList.get(i).getGroupName())){
				groupIdList.add(monitorGroupList.get(i).getGroupId());
			}
		}
		
		boolean flag = true;
		
		for(int i=0;i<comList.size();i++){
			
			if(monitorGroupList.size()==1){
				//如果只有一个分组那么直接进行取消
				monitorCompanyService.updateGroupStatus(comList.get(i).getMonitorID(), "0");
			}else{
				//查看对应分组中是否有对应的公司
				flag = monitorGroupCompanyService.getGroupCompanyList(groupIdList, comList.get(i).getMonitorID());
				
				if(!flag){
					//如果没有那么需要改变下对应的状态
					monitorCompanyService.updateGroupStatus(comList.get(i).getMonitorID(), "0");
				}
			}
		}
		
		return result;
	}
	
	/** 
	* @Title: updateMonitorGroup 
	* @Description: 更新分组信息
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月5日 下午7:44:59
	*/
	@ResponseBody
	@RequestMapping("/updateMonitorGroup")
	public ResultBean updateMonitorGroup(HttpServletRequest request,HttpServletResponse response){
		
		ResultBean result = new ResultBean();
		
		String groupId = request.getParameter("groupId");
		String groupName = request.getParameter("groupName").trim();
		
		if(StringUtils.isEmpty(groupName)){
			result.setResultCode("1");
			result.setResultMsg("分组名称不能为空！");
		}
		
		try {
			if(StringUtils.isNotEmpty(groupName)){
				groupName = URLDecoder.decode(groupName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("修改分组名称解析错误，", e);
		}
		
		ClientAccount account = getClientAccountFromSession(request);
		
		MonitorGroup monitorGroup = new MonitorGroup();
		monitorGroup.setGroupId(groupId);
		monitorGroup.setClientAccount(account);
		monitorGroup.setGroupName(groupName);
		
		return monitorGroupService.updateGroup(monitorGroup);
	}
	
	/** 
	* @Title: updateMonitorCompanyGroupBatch 
	* @Description: 批量更新主体企业分组
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午3:35:56
	*/
	@ResponseBody
	@RequestMapping("/updateMonitorCompanyGroupBatch")
	public ResultBean updateMonitorCompanyGroupBatch(HttpServletRequest request,HttpServletResponse response){
		
		String monitorIds = request.getParameter("monitorIds");
		String groupIds = request.getParameter("groupIds");
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
	
		List<String> monitorIdList = new ArrayList<String>();
		List<String> groupIdList = new ArrayList<String>();
		
		if(StringUtils.isNotEmpty(monitorIds)){
			String [] monitorIdArr = monitorIds.split(",");
			monitorIdList = Arrays.asList(monitorIdArr);
		}
		
		if(StringUtils.isNotEmpty(groupIds)){
			String [] groupIdArr = groupIds.split(",");
			groupIdList = Arrays.asList(groupIdArr);
		}
		
		boolean result = monitorGroupCompanyService.updateMonitorCompanyGroupBatch(accountId, monitorIdList, groupIdList);
		
		if(StringUtils.isNotEmpty(groupIds)){
			for(int i=0;i<monitorIdList.size();i++){
				monitorCompanyService.updateGroupStatus(monitorIdList.get(i), "1");
			}
		}else{
			for(int i=0;i<monitorIdList.size();i++){
				monitorCompanyService.updateGroupStatus(monitorIdList.get(i), "0");
			}
		}
		
		ResultBean resultBean = new ResultBean();
		
		if(result){
			resultBean.setResultCode("0");
			resultBean.setResultMsg("批量修改分组成功！");
		}else{
			resultBean.setResultCode("1");
			resultBean.setResultMsg("批量修改分组失败！");
		}
		
		return resultBean;
	}
	
}
