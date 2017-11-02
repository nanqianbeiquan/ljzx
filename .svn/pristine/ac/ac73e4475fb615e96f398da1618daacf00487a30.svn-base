/**   
* @Title: InfoMessageController.java 
* @Package com.srd.ljzd.controller.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 上午10:43:20 
* @version V1.0   
*/
package com.srd.ljzd.controller.info;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.info.InfoMessage;
import com.srd.ljzd.entity.info.InfoMessageAccount;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.info.InfoMessageAccountService;
import com.srd.ljzd.service.info.InfoMessageService;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: InfoMessageController
 * @Description: 站内信Controller
 * @author shiyong
 * @date 2016年11月22日 上午10:43:20
 *  
 */
@Controller
@RequestMapping("/message")
public class InfoMessageController {
	
	@Autowired
	private InfoMessageService infoMessageService;
	
	@Autowired
	private InfoMessageAccountService infoMessageAccountService;
	
	/** 
	* @Title: toMessageList 
	* @Description: 跳转到消息列表
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 下午2:19:25
	*/
	@RequestMapping("/toMessageList")
	public String toMessageList(String deep,HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("deep", deep);
		return "/modules/info/message/messageList";
	}
	
	/** 
	* @Title: queryMessageList 
	* @Description: 获取消息列表
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return Page<InfoMessage> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 下午3:26:41
	*/
	@RequestMapping("/queryMessageList")
	@ResponseBody
	public Page<InfoMessageAccount> queryMessageList(HttpServletRequest request, HttpServletResponse response){
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		String sender = request.getParameter("sender");
		
		InfoMessageAccount infoMessageAccount = new InfoMessageAccount();
		InfoMessage infoMessage = new InfoMessage();
		
		if(StringUtils.isNotEmpty(sender)){
			infoMessage.setSender(sender);
		}
		
		infoMessageAccount.setInfoMessage(infoMessage);
		infoMessageAccount.setAccountId(accountId);
		
		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		
		int pageSize = 10;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<InfoMessageAccount> messagePageAccount = infoMessageAccountService.findMessageAccountPage(infoMessageAccount, currentPageNo, pageSize);
		
		return messagePageAccount;
	}
	
	/** 
	* @Title: getMessage 
	* @Description: 获取消息详情
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return InfoMessage 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 上午9:34:19
	*/
	@RequestMapping("/getMessage")
	@ResponseBody
	public InfoMessage getMessage(HttpServletRequest request, HttpServletResponse response){
		
		String messageId = request.getParameter("messageId");
		String readStatus = request.getParameter("readStatus");
		String messageAccountId = request.getParameter("messageAccountId");
		
		//标记为已读
		if("0".equals(readStatus)){
			InfoMessageAccount infoMessageAccount = new InfoMessageAccount();
			
			infoMessageAccount.setId(messageAccountId);
			
			infoMessageAccountService.updateMessageReadStatus(infoMessageAccount);
			
			ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
			if(account==null||account.getAccountId()==null){
				throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
			}
			String accountId = account.getAccountId();
			
			//提示未读消息数量
			int unreadMessageNum = infoMessageAccountService.getUnreadMessageNum(accountId);
			
			request.getSession().setAttribute("unreadMessageNum", unreadMessageNum);
		}
		
		return infoMessageService.getMessageById(messageId);
	}
	
	/** 
	* @Title: readAllMessage 
	* @Description: 将站内信全部置为已读 
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月16日 上午10:53:40
	*/
	@RequestMapping("/readAllMessage")
	@ResponseBody
	public ResultBean readAllMessage(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		String sender = request.getParameter("sender");
		
		InfoMessageAccount infoMessageAccount = new InfoMessageAccount();
		infoMessageAccount.setAccountId(accountId);
		infoMessageAccount.setReadStatus("0");
		
		InfoMessage infoMessage = new InfoMessage();
		infoMessage.setSender(sender);
		
		infoMessageAccount.setInfoMessage(infoMessage);
		
		List<InfoMessageAccount> infoMessageAccountList = infoMessageAccountService.queryMessageAccountList(infoMessageAccount);
		
		if(infoMessageAccountList.size() > 0){
			result = infoMessageAccountService.updateMessageReadStatus(infoMessageAccountList);
			
			//提示未读消息数量
			int unreadMessageNum = infoMessageAccountService.getUnreadMessageNum(accountId);
			
			request.getSession().setAttribute("unreadMessageNum", unreadMessageNum);
		}else{
			result.setResultCode("0");
		}
		
		return result;
	}
	
	@RequestMapping("/deleteMessages")
	@ResponseBody
	public ResultBean deleteMessages(HttpServletRequest request, HttpServletResponse response){
		
		String[] messageIds = request.getParameter("messageIds").split(",");
		
		ResultBean result = infoMessageAccountService.deleteMessageAccounts(messageIds);
		
		return result;
	}
	
}
