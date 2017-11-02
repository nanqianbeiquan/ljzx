/**   
* @Title: InfoIntroductionController.java 
* @Package com.srd.ljzd.controller.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月24日 下午4:02:11 
* @version V1.0   
*/
package com.srd.ljzd.controller.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/** 
 * @ClassName: InfoIntroductionController
 * @Description: 介绍类静态页面Controller
 * @author shiyong
 * @date 2016年11月24日 下午4:02:11
 *  
 */
@Controller
@RequestMapping("/introduction")
public class InfoIntroductionController {
	
	/** 
	* @Title: toAboutUs 
	* @Description: 跳转到关于我们页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月24日 下午4:25:31
	*/
	@RequestMapping(value="/toAboutUs")
	public String toAboutUs(Integer deep,HttpServletRequest request, HttpServletResponse response, Model model){
	    model.addAttribute("deep", deep);
	    
		return "/modules/info/introduction/aboutus";
	}
	
	/** 
	* @Title: toDisclaimer 
	* @Description: 跳转到版权申明页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月24日 下午4:25:44
	*/
	@RequestMapping(value="/toDisclaimer")
	public String toDisclaimer(Integer deep,HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("deep", deep);
		
		return "/modules/info/introduction/disclaimer";
	}
	
	@RequestMapping(value="/toQuesttions")
	public String toQuesttions(Integer deep,HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("deep", deep);
		
		return "/modules/info/questions/questions";
	}
	
	
}
