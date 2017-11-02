package com.srd.ljzd.controller.relationMap;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.service.relationMap.RelationMapService;

@Controller
@RequestMapping("/Access")
public class RelationMapController {
    @Autowired
	RelationMapService relationMapService;
	
	//获取一度关系
	@RequestMapping("/function1ForAjax")
	@ResponseBody
	public Object function1ForAjax(String compNameAjax){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compNameAjax", compNameAjax);
		return relationMapService.invoke("/function1ForAjax", params);
	}
	
	//修剪节点
	@RequestMapping("/function7ForAjax")
	public void function7ForAjax(String nodeId,String relationships,HttpServletResponse response){
		Map<String,String> params = new HashMap<String,String>();
		params.put("nodeId", nodeId);
		params.put("relationships", relationships);
		String result = relationMapService.invokeReturnString("/function7ForAjax", params);
		if(result==null){
			result = "";
		}
		
		try { 
			response.setContentType("type=text/html;charset=UTF-8"); 
			response.getWriter().write(result); 
		} catch (Exception e) { 
		    e.printStackTrace(); 
		} 
	}
	
	//合并人节点 基本每次调用都会用到
	@RequestMapping("/function8ForAjax")
	@ResponseBody
	public Object function8ForAjax(String companyNodeIdArrText,String companyIdPersonName2PersonIdText,
			String old2NewText,String personIdArr1Text){
		String tmp = null;
		
		try {
			if(companyIdPersonName2PersonIdText!=null){
				tmp = URLDecoder.decode(companyIdPersonName2PersonIdText, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			tmp = companyIdPersonName2PersonIdText ;
		}
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("companyNodeIdArrText",  companyNodeIdArrText);
		params.put("companyIdPersonName2PersonIdText", tmp);
		params.put("old2NewText", old2NewText);
		params.put("personIdArr1Text", personIdArr1Text);
		return relationMapService.invoke("/function8ForAjax", params);
	}
	
	//双击获取选中节点的关系
	@RequestMapping("/function10ForAjax")
	@ResponseBody
	public Object function10ForAjax(String nodeIds){
		Map<String,String> params = new HashMap<String,String>();
		params.put("nodeIds", nodeIds);
		return relationMapService.invoke("/function10ForAjax", params);
	}
	
	//词云接口
	@RequestMapping("/news/getWordCloud")
	@ResponseBody
	public Object getWordCloud(String companyName,String num){
		Map<String,String> params = new HashMap<String,String>();
		params.put("companyName", companyName);
		params.put("num", num);
		return relationMapService.invokeReturnArray("/news/getWordCloud", params);
	}
	
	//词语关联新闻列表接口
	@RequestMapping("/news/getWordCloudList")
	@ResponseBody
	public Object getWordCloudList(String companyName,String key){
		Map<String,String> params = new HashMap<String,String>();
		params.put("companyName", companyName);
		params.put("key", key);
		return relationMapService.invoke("/news/getWordCloudList", params);
	}
	
	//词语关联新闻详情接口
	@RequestMapping("/news/getWordCloudNewsDetail")
	@ResponseBody
	public Object getWordCloudNewsDetail(String key){
		Map<String,String> params = new HashMap<String,String>();
		params.put("key", key);
		return relationMapService.invoke("/news/getWordCloudNewsDetail", params);
	}
	
	//修剪相关接口
	@RequestMapping("/relativeRisk")
	@ResponseBody
	public Object getrelativeRisk(String companyNames){
		Map<String,String> params = new HashMap<String,String>();
		params.put("companyNames", companyNames);
		return relationMapService.invokeReturnArray("/relativeRisk", params);
	}
	
	//关联公司接口
	@RequestMapping("/function2ForAjax")
	@ResponseBody
	public Object function2ForAjax(String compName21Ajax,String compName22Ajax,String depthAjax){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName21Ajax", compName21Ajax);
		params.put("compName22Ajax", compName22Ajax);
		params.put("depthAjax", depthAjax);
		return relationMapService.invoke("/function2ForAjax", params);
	}
	
	//批量公司接口
	@RequestMapping("/function3ForAjax")
	@ResponseBody
	public Object function3ForAjax(String compNameAjax,Integer depthAjax){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compNameAjax", compNameAjax);
		params.put("depthAjax", depthAjax.toString());
		return relationMapService.invoke("/function3ForAjax", params);
	}
}
