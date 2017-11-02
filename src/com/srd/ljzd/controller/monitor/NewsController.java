/**  
* @Title: RiskAnalysisController.java
* @Package com.srd.ljzd.controller.monitor
* @Description: TODO(用一句话描述该文件做什么)
* @author zihui.pei  
* @date 2016年12月29日 下午1:18:03
* @version V1.3
*/
package com.srd.ljzd.controller.monitor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.srd.ljzd.entity.news.NewsOpinion;
import com.srd.ljzd.service.news.NewsService;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringTool;


  

/**   
 * 版权所有：2016
 * 项目名称：ljzx   
 *
 * 类描述：新闻详情
 * 类名称：com.srd.ljzd.controller.monitor.NewsController     
 * 创建人：裴子辉
 * 创建时间：2016年12月30日 下午5:04:31   
 * 修改人：
 * 修改时间：2016年12月30日 下午5:04:31   
 * 修改备注：   
 * @version   V1.3    
 */
  
@Controller
@RequestMapping("/newsCtl")
public class NewsController {
	

	@Autowired
	NewsService newsService;
	/**
	* @Title: toNews
	* @Description: TODO(新闻舆情首页)
	* @param  @param request
	* @param  @param response
	* @param  @param model
	* @param  @return  
	* @return String    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2016年12月30日 下午5:06:48
	*/
	@RequestMapping("/toNews")
	public String toNews(HttpServletRequest request, HttpServletResponse response, Model model){
	
		String companyName = request.getParameter("companyName");
		model.addAttribute("companyName", companyName);
		return "/modules/company/detail/news/news";
	}
	
	
	
	/**
	* @Title: getMediaOpinion
	* @Description: TODO(媒体资讯)
	* @param  @param request
	* @param  @param response
	* @param  @param model
	* @param  @return  
	* @return String    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月3日 上午11:37:33
	*/
	@RequestMapping("/getMediaOpinion")
	public ModelAndView getMediaOpinion(HttpServletRequest request, HttpServletResponse response){
	
		ModelAndView model = new ModelAndView();
		String companyName = request.getParameter("companyName");
		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<NewsOpinion> mediaPage = newsService.queryNewsOpinionList(companyName, "0", currentPageNo, pageSize);
		model.addObject("mediaPage", mediaPage);
		model.setViewName("/modules/company/detail/news/mediaOpinion");
		model.addObject("companyName", companyName);
		return model;
	}
	
	/**
	* @Title: getNegativeOpinion
	* @Description: TODO(负面舆情)
	* @param  @param request
	* @param  @param response
	* @param  @param model
	* @param  @return  
	* @return String    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月3日 上午11:37:48
	*/
	@RequestMapping("/getNegativeOpinion")
	public ModelAndView getNegativeOpinion(HttpServletRequest request, HttpServletResponse response){
	
		ModelAndView model = new ModelAndView();
		String companyName = request.getParameter("companyName");
		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		// 0 媒体资讯，1 负面舆情，2 全部
		Page<NewsOpinion> negativePage  = newsService.queryNewsOpinionList(companyName, "1", currentPageNo, pageSize);
		model.addObject("negativePage", negativePage);
		model.setViewName("/modules/company/detail/news/negativeOpinion");
		model.addObject("companyName", companyName);
		
		return model;
	}
	
	/**
	* @Title: getOpinionDetailResult
	* @Description: TODO(获取公司详情)
	* @param  @param request
	* @param  @param response
	* @param  @return  
	* @return ModelAndView    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月9日 上午10:43:38
	*/
	@RequestMapping("/getOpinionDetailResult")
	public ModelAndView getOpinionDetailResult(HttpServletRequest request, HttpServletResponse response){
	
		ModelAndView model = new ModelAndView();
		String companyName = request.getParameter("companyName");
		String newsKey = request.getParameter("newsKey");
		String newsId = request.getParameter("newsId");
		String opinionType = request.getParameter("opinionType");
		

		// 0 媒体资讯，1 负面舆情，2 全部
		NewsOpinion newsOpinion  = newsService.queryNewsDetails(newsKey, newsId, companyName,opinionType);
		model.addObject("newsOpinion", newsOpinion);
		model.setViewName("/modules/company/detail/news/opinionDetail");
		model.addObject("companyName", companyName);
		
		return model;
	}
	
}
