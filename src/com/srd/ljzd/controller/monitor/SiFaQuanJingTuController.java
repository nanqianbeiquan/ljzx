package com.srd.ljzd.controller.monitor;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.service.monitor.ThirdPartInterfaceService;

@Controller
@RequestMapping("/sfGraph")
public class SiFaQuanJingTuController {

	//通用
    @Autowired
    ThirdPartInterfaceService thirdPartInterfaceService;
	
	@RequestMapping("/graph38")
	@ResponseBody
	public Object graph38(String startTime,String compName,String stopTime){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName", compName);
		params.put("startTime", startTime);
		params.put("stopTime", stopTime);
		return thirdPartInterfaceService.invokeReturnArray("/sfGraph/graph38", params);
	}
	
	@RequestMapping("/graph37")
	@ResponseBody
	public Object graph37(String compName){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName", compName);
		return thirdPartInterfaceService.invokeReturnArray("/sfGraph/graph37", params);
	}
	
	@RequestMapping("/graph4")
	@ResponseBody
	public Object graph4(String compName){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName", compName);
		return thirdPartInterfaceService.invokeReturnArray("/sfGraph/graph4", params);
	}
	
	@RequestMapping("/graph3")
	@ResponseBody
	public Object graph3(String startTime,String compName,String stopTime){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName", compName);
		params.put("startTime", startTime);
		params.put("stopTime", stopTime);
		return thirdPartInterfaceService.invokeReturnArray("/sfGraph/graph3", params);
	}
	
	@RequestMapping("/graph2")
	@ResponseBody
	public Object graph2(String startTime,String compName,String stopTime){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName", compName);
		params.put("startTime", startTime);
		params.put("stopTime", stopTime);
		return thirdPartInterfaceService.invokeReturnArray("/sfGraph/graph2", params);
	}
	
	@RequestMapping("/graph1")
	@ResponseBody
	public Object graph1(String compName){
		Map<String,String> params = new HashMap<String,String>();
		params.put("compName", compName);
		return thirdPartInterfaceService.invoke("/sfGraph/graph1", params,MediaType.APPLICATION_FORM_URLENCODED_TYPE);
	}
}
