package com.srd.ljzd.service.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.srd.ljzd.entity.biz.BizChangeRecordHolder;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.util.ThirdPartChannelEnum;

public interface BizService {
	public BizMsg getBrefCompany(String companyName,ThirdPartChannelEnum channel);
	//构造工商信息数据
	public BizMsg getGongShangMsg(String companyName,ThirdPartChannelEnum channel);
	
	public BizMsg getOriginalGongShangMsg(String companyName);
	
	//各模块排序
	public void sort(BizMsg company);
	//缓存工商信息
	public void cache(String companyName,BizMsg company,BizChangeRecordHolder holder,ThirdPartChannelEnum channel);
	//初始化分页
	public Map<String, Object> initPage(int curPage,int pageSize,ArrayList<HashMap<String, String>> array);
	//获取缓存中的工商信息
	public BizMsg getCachedBizMsg(String companyName,ThirdPartChannelEnum channel);
	//构造并缓存工商信息各模块分页信息
	public void buildPage(BizMsg company, int curPage,int pageSize, Model model);
	public void buildPageBref(BizMsg company, int curPage,int pageSize, Model model);
	//变更记录 “变更事项”处理()及去重复，变更事项和变更时间都一样，认为是重复
	public  ArrayList<HashMap<String, String>> categoryAndRepeatedRemoveOfBiangGeng(ArrayList<HashMap<String, String>> sourceArray);
	/**
	 * 变更记录的 变更事项名称  归类
	 * @param sourceArray
	 * @return
	 */
	ArrayList<HashMap<String, String>> categoryBiangGeng(ArrayList<HashMap<String, String>> sourceArray);
	//高亮显示
	public void hightLightWords(ArrayList<HashMap<String, String>> bianGengJiLuArray);
	
	//构造变更记录时间轴数据结构
	public BizChangeRecordHolder buildTimeAxisDataStructure(ArrayList<HashMap<String, String>> sourceArray,String companyName);
	//获取商标
	public ArrayList<HashMap<String,String>> getBrand(String companyName)
	;
	public Map<String, Object> getCachedTimeAxis(String companyName,ThirdPartChannelEnum channel);
	public Map<String, Integer> getBianGengJiLuCachedCategory(String companyName,ThirdPartChannelEnum channel);
	public Map<String, Integer> getCachedSortedDate(String companyName,ThirdPartChannelEnum channel);
	public Map<String, Integer> getChageRecordCount(String companyName,
			Integer timeLimit, List<String> modules,ThirdPartChannelEnum channel);
	/**
	 * 获取企业的经营状态
	 * @param companyName
	 * @return
	 */
	public String getEntStatus(String companyName);
}
