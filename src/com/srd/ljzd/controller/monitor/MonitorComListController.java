package com.srd.ljzd.controller.monitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 * 
 * 版权所有：
 * 项目名称：lengjingzd2.0 
 *
 * 类描述：动态监控列表项
 * 类名称：com.srd.ljzd.controller.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月4日下午3:19:03
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
@Controller
@RequestMapping("/monitorComList")
public class MonitorComListController {

	/**
	 * 
		* @Title: showDetails
		* @Description: 展示动态监控详情页面
		* @param  @param request
		* @param  @param response
		* @param  @return
		* @return ModelAndView
		* @author jiang.zhou
		* @throws
		* @date 下午3:25:14
	 */
	@RequestMapping("/showMonitorComPage.do")
	ModelAndView showDetails(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView model=new ModelAndView();
		
		model.setViewName("modules/monitor/monitor_company");
		return model;	
	}
	
	
	
	
	
}
