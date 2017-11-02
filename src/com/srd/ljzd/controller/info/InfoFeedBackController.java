/**   
* @Title: InfoFeedBackController.java 
* @Package com.srd.ljzd.controller.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 上午10:35:07 
* @version V1.0   
*/
package com.srd.ljzd.controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.info.InfoFeedback;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.info.InfoFeedbackService;
import com.srd.ljzd.util.ErrorCode;

/** 
 * @ClassName: InfoFeedBackController
 * @Description: 反馈Controller
 * @author shiyong
 * @date 2016年11月22日 上午10:35:07
 *  
 */
@Controller
@RequestMapping("/feedBack")
public class InfoFeedBackController {

	protected static Logger log = LogManager.getLogger(InfoFeedBackController.class.getName());
	
	@Autowired
	InfoFeedbackService infoFeedbackService;
	
	/** 
	* @Title: toFeedBack 
	* @Description: 跳转到意见反馈新增页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 上午10:56:13
	*/
	@RequestMapping("/toFeedBack")
	public String toFeedBack(Integer deep,HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("deep", deep);
		
		return "/modules/info/feedBack/feedBackAdd";
	}
	
	/** 
	* @Title: addFeedBack 
	* @Description: 添加意见反馈
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 上午11:35:38
	*/
	@RequestMapping("/addFeedBack")
	@ResponseBody
	public ResultBean addFeedBack(HttpServletRequest request, HttpServletResponse response){
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		String feedbackContent = request.getParameter("feedbackContent");
		
		InfoFeedback feedback = new InfoFeedback();
		feedback.setAccountID(accountId);
		feedback.setFeedbackContent(feedbackContent);
		feedback.setReplyStatus("0");
		
		ResultBean result = infoFeedbackService.saveFeedback(feedback);
		
		return result;
	}
	
	/** 
	* @Title: queryFeedBackList 
	* @Description: 获取意见反馈列表
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return List<InfoFeedback> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 上午11:35:49
	*/
	@RequestMapping("/queryFeedBackList")
	@ResponseBody
	public List<InfoFeedback> queryFeedBackList(HttpServletRequest request, HttpServletResponse response){
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		List<InfoFeedback> feedbackList = infoFeedbackService.queryFeedbackList(accountId);
		
		return feedbackList;
	}
	
	
	
	
	
}
