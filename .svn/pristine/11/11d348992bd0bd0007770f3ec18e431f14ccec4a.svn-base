package com.srd.ljzd.serviceImpl.monitor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.monitor.MonitorCompanyFinanceSituationDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceSituation;
import com.srd.ljzd.service.monitor.MonitorCompanyFinanceService;
import com.srd.ljzd.util.StringUtils;
@Service("monitorCompanyFinanceService")
public class MonitorCompanyFinanceServiceImpl implements
		MonitorCompanyFinanceService {
	
	@Autowired
	private MonitorCompanyFinanceSituationDao monitorCompanyFinanceSituationDao;
	
    private List<String> cateKeyList1 = null;
    private List<String> cateKeyList2 = null;
    private Map<String,String> cateKeyToRegionKeyMap = null;
    private DecimalFormat df = null;
    private static final String RED = "2"; 
    private static final String BLUE = "1"; 
    @PostConstruct
    private void init(){
    	if(cateKeyList1==null){
    		cateKeyList1 = Arrays.asList("assgro","liagro","vendinc","netinc","ratgro");
    	}
    	if(cateKeyList2==null){
    		cateKeyList2 = Arrays.asList("zcfzl","jlrl","zcjlrl","zzczzl");
    	}
    	if(cateKeyToRegionKeyMap==null){
    		cateKeyToRegionKeyMap = new HashMap<String, String>();
    		cateKeyToRegionKeyMap.put("assgro", "zzzepaiming");
    		cateKeyToRegionKeyMap.put("liagro", "fzzepaiming");
    		cateKeyToRegionKeyMap.put("vendinc", "yyzsrpeiming");
    		cateKeyToRegionKeyMap.put("netinc", "jlrpaiming");
    		cateKeyToRegionKeyMap.put("ratgro", "nszepaiming");
    	}
    	if(df==null){
    		df = new DecimalFormat("##0.00");
    	}
    }
	@Override
	public Map<String, Object> getSummaryAnalysisData(JSONObject source) {
		Map<String, Object> target = null;
		if(source!=null&&source.containsKey("gxzb")){
			target = new HashMap<String, Object>();
			JSONObject gxzb = source.getJSONObject("gxzb");
			JSONObject gxzbRecord = null;
			JSONObject gxzbRecordNbjs = null;//年报
			Double vendinc = null;
			Double progro = null;
			Double temp = null;
			Map<String, Object> item = null;
			
			for(String keyName : gxzb.keySet()){
				gxzbRecord = gxzb.getJSONObject(keyName);
				if(gxzbRecord!=null){
					item = new HashMap<String, Object>();
					if(gxzbRecord.containsKey("nbsj")){//年报数据
						gxzbRecordNbjs = gxzbRecord.getJSONObject("nbsj");
						if(gxzbRecordNbjs!=null){
							vendinc = null;
						    progro = null;
							if(gxzbRecordNbjs.containsKey("vendinc")){//营业收入
								vendinc = this.getDoubleValue((String)gxzbRecordNbjs.get("vendinc"),null);
								if(vendinc!=null){
									item.put("vendinc", vendinc);
								}
								
							}
							if(gxzbRecordNbjs.containsKey("netinc")){//净利润
								temp = this.getDoubleValue((String)gxzbRecordNbjs.get("netinc"),null);
								if(temp!=null){
									item.put("netinc", temp);
								}
							}
							if(gxzbRecordNbjs.containsKey("progro")){//利润总额
								progro = this.getDoubleValue((String)gxzbRecordNbjs.get("progro"),null);
								if(progro!=null){
									item.put("progro", progro);
								}
							}
							if(gxzbRecordNbjs.containsKey("totequ")){//股东权益
								temp = this.getDoubleValue((String)gxzbRecordNbjs.get("totequ"),null);
								if(temp!=null){
									item.put("totequ", temp);
								}
								
							}
							if(gxzbRecordNbjs.containsKey("registeredcapital")){//注册资本
								temp = this.getDoubleValue((String)gxzbRecordNbjs.get("registeredcapital"),null);
								if(temp!=null){
									item.put("registeredcapital", temp);
								}
							}
							//计算利润率
							if(vendinc!=null&&progro!=null&&vendinc!=0.0){
								item.put("lrv", progro/vendinc);
							}
						}
					}
					if(gxzbRecord.containsKey("zcfzl")&&gxzbRecord.getJSONObject("zcfzl")!=null){//资产负债率
						temp = this.getDoubleValue((String)gxzbRecord.getJSONObject("zcfzl").get("rate"),null);
						if(temp!=null){
							item.put("zcfzl", temp);
						}
						
					}
					if(gxzbRecord.containsKey("zzczzl")&&gxzbRecord.getJSONObject("zzczzl")!=null){//总资产周转率
						temp = this.getDoubleValue((String)gxzbRecord.getJSONObject("zzczzl").get("rate"),null);
						if(temp!=null){
							item.put("zzczzl", temp);
						}
					}
					target.put(keyName, item);
				}
			}
		}
		
		return target;
	}

	@Override
	public Map<String, Object> getEachItemAnalysisData(JSONObject source) {
		Map<String, Object> target = null;
		if(source!=null&&source.containsKey("gxzb")){
			target = new HashMap<String, Object>();
			JSONObject gxzb = source.getJSONObject("gxzb");
			Set<String> yearSet = gxzb.keySet();
			
			List<Integer> sortList = new ArrayList<Integer>(); 
			for(String year : yearSet){
				if(StringUtils.isNumeric(year)){
					sortList.add(Integer.parseInt(year));
				}
			}
			int length = sortList.size();
			if(length==0){
				return null;
			}
			Collections.sort(sortList);
			String yearKey = null;
			JSONObject yearData = null;
			JSONObject yearNbsjData = null;
			String regionKey = null;
			Double temp = null;
			Map<String, Object> item = null;
			Map<String, Object> itemCompont = null;
			
			Map<String, Object> cateTarget = null;
		    //基础数据指标
			for(String cateKey:cateKeyList1){
				cateTarget = new HashMap<String, Object>();
				for(int i=sortList.size()-1;i>=0;i--){
					yearKey = ""+sortList.get(i);
					yearData = gxzb.getJSONObject(yearKey);
					itemCompont = null;
					if(yearData.containsKey("nbsj")){
						yearNbsjData = yearData.getJSONObject("nbsj");
						if(yearNbsjData!=null){
							//if(yearNbsjData.containsKey(cateKey)){
								//从年报数据中取资产总额
								temp = this.getDoubleValue((String)yearNbsjData.get(cateKey),2);
								itemCompont = new HashMap<String, Object>();
								itemCompont.put("value", temp);
								if(cateKeyToRegionKeyMap==null){
									this.init();
								}
								regionKey = cateKeyToRegionKeyMap.get(cateKey);
								//从资产总额排名数据中取   区间
								temp=null;
								if(yearData.containsKey(regionKey)&&yearData.getJSONObject(regionKey)!=null){
									temp = this.getDoubleValue((String)yearData.getJSONObject(regionKey).get("region"),2);
								}
								itemCompont.put("region", temp);
							//}
						}
					}
					cateTarget.put(yearKey, itemCompont);
					
				}//yearKey 循环结束
				target.put(cateKey, cateTarget);
			}
			
			//能力指标
			for(String cateKey:cateKeyList2){
				cateTarget = new HashMap<String, Object>();
				for(int i=sortList.size()-1;i>=0;i--){
					yearKey = ""+sortList.get(i);
					yearData = gxzb.getJSONObject(yearKey);
					itemCompont = null;
					if(yearData!=null&&yearData.containsKey(cateKey)&&yearData.getJSONObject(cateKey)!=null){//
						temp = this.getDoubleValue((String)yearData.getJSONObject(cateKey).get("rate"),4);
						itemCompont = new HashMap<String, Object>();
						itemCompont.put("value", temp);
						temp = this.getDoubleValue((String)yearData.getJSONObject(cateKey).get("region"),2);
						itemCompont.put("region", temp);
					}
					cateTarget.put(yearKey, itemCompont);
				}//yearKey 循环结束
				target.put(cateKey, cateTarget);
			}
		}
		
		return target;
	}

	@Override
	public Map<String, Object> buildSummaryAnalysis(Map<String, Object> data) {
		
		if(data!=null){
			Set<String> yearSet = data.keySet();
			if(yearSet==null||yearSet.size()<2){//不足两年数据不进行分析
				return null;
			}
			List<Integer> sortList = new ArrayList<Integer>(); 
			for(String year : yearSet){
				if(StringUtils.isNumeric(year)){
					sortList.add(Integer.parseInt(year));
				}
			}
			if(sortList.size()<2){//不足两年数据不进行分析
				return null;
			}
			Collections.sort(sortList);
			if(sortList.get(sortList.size()-1)!=sortList.get(sortList.size()-2)+1){//无连续两年，不进行分析
				return null;
			}
			
			Map<String,Object> currentYearData = (Map<String,Object>)data.get(""+sortList.get(sortList.size()-1));
			Map<String,Object> preYearData = (Map<String,Object>)data.get(""+sortList.get(sortList.size()-2));
			String key = null;
			Map<String,Object> group2 = new HashMap<String, Object>();
			 //3 资产负债率 --> 资产负债率变动
			key = "zcfzl";
			Map<String,Object> cate3 = getCate3Analysis(key,currentYearData,preYearData);
			if(cate3!=null&&!cate3.isEmpty()){
				group2.put(key, cate3);
			}
			//4 总资产周转率 --> 总资产周转率变动率
			key="zzczzl";
			Map<String,Object> cate4 = getCate4Analysis(key,currentYearData,preYearData);
			if(cate4!=null&&!cate4.isEmpty()){
				group2.put(key, cate4);
			}
			//5 利润率 -->利润率变动率
			key="lrv";
			Map<String,Object> cate5 = getCate5Analysis(key,currentYearData,preYearData);
			if(cate5!=null&&!cate5.isEmpty()){
				group2.put(key, cate5);
			}
			if(group2.isEmpty()||group2.keySet().size()<2){
				//第二组展示时，必须至少有两个。如果缺失超过一个，则整个第二组就不展示。如果第二组没有展示，则不展示总结。
				return null;
			}
			
			Map<String,Object> group1 = new HashMap<String, Object>();
			//1 营业收入 计算 营业收入增长率
			key = "vendinc";
			Map<String,Object> cate1 = getCate1Analysis(key,currentYearData,preYearData);
			if(cate1!=null&&!cate1.isEmpty()){
				group1.put(key, cate1);
			}
			//2 净利润 --> 净利润增长率
			key = "netinc";
			Map<String,Object> cate2 = getCate2Analysis(key,currentYearData,preYearData);
			if(cate2!=null&&!cate2.isEmpty()){
				group1.put(key, cate2);
			}
			Map<String,Object> group3 = new HashMap<String, Object>();
			//6 股东权益
			Map<String,Object> cate6 = this.getCate6Analysis("totequ","registeredcapital","netinc",currentYearData,preYearData);
			if(cate6!=null&&!cate6.isEmpty()){
				group3.put("totequ", cate6);
			}
			
			Map<String,String> analysis = this.getAllAnalysis(sortList.get(sortList.size()-1),group1,group2,group3);
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("section1", analysis.get("section1"));
			result.put("section2", analysis.get("section2"));
			result.put("score", analysis.get("score"));
			return result;
			
		}
		return null;
	}

	private Map<String,String> getAllAnalysis(int curYear,Map<String, Object> group1,
			Map<String, Object> group2, Map<String, Object> group3) {
		Map<String, String> target = new HashMap<String, String>();
		StringBuffer targetBuf = new StringBuffer("该企业").append(curYear).append("年度");
		int score = -100;//初始化为不可能的值
		Map<String, Object> cateRecord = null;
		String desc = null;
		boolean isHasPre = false;//是否有前置种类，有则 加标点
		if(!group1.isEmpty()){
			
			//1 营业收入
			if(group1.containsKey("vendinc")){
				cateRecord = (Map<String, Object>)group1.get("vendinc");
				if(cateRecord!=null&&cateRecord.containsKey("score")){
					if(score==-100){
						score = (int)cateRecord.get("score");
					}else{
						score += (int)cateRecord.get("score");
					}
					desc = (String)cateRecord.get("desc");
					if(StringUtils.isNotEmpty(desc)){
						targetBuf.append(desc);
						isHasPre = true;
					}
					
				}
				//System.out.println("种类1，营业收入，param="+cateRecord);
			}
			//2 净利润 
			if(group1.containsKey("netinc")){
				cateRecord = (Map<String, Object>)group1.get("netinc");
				if(cateRecord!=null&&cateRecord.containsKey("score")){
					if(score==-100){
						score = (int)cateRecord.get("score");
					}else{
						score += (int)cateRecord.get("score");
					}
					
					desc = (String)cateRecord.get("desc");
					if(StringUtils.isNotEmpty(desc)){
						if(isHasPre){
							targetBuf.append("，");
						}
						targetBuf.append(desc);
					}
					//System.out.println("种类2， 净利润 ，param="+cateRecord);
				}
			}
			
		}
		StringBuffer group2DescBuf = new StringBuffer();
		if(!group2.isEmpty()){
			//3  资产负债率
			if(group2.containsKey("zcfzl")){
				cateRecord = (Map<String, Object>)group2.get("zcfzl");
				if(cateRecord!=null&&cateRecord.containsKey("score")){
					if(score==-100){
						score = (int)cateRecord.get("score");
					}else{
						score += (int)cateRecord.get("score");
					}
					desc = (String)cateRecord.get("desc");
					if(StringUtils.isNotEmpty(desc)){
						group2DescBuf.append(desc);
					}
					//System.out.println("种类3， 资产负债率，param="+cateRecord);
				}
				
			}
			//4 总资产周转率
			if(group2.containsKey("zzczzl")){
				cateRecord = (Map<String, Object>)group2.get("zzczzl");
				if(cateRecord!=null&&cateRecord.containsKey("score")){
					if(score==-100){
						score = (int)cateRecord.get("score");
					}else{
						score += (int)cateRecord.get("score");
					}
					
					desc = (String)cateRecord.get("desc");
					if(StringUtils.isNotEmpty(desc)){
						if(group2DescBuf.length()>0){
							group2DescBuf.append("，");
						}
						group2DescBuf.append(desc);
					}
					
					//System.out.println("种类4， 总资产周转率，param="+cateRecord);
				}
				
			}
			//5 利润率 
			if(group2.containsKey("lrv")){
				cateRecord = (Map<String, Object>)group2.get("lrv");
				if(cateRecord!=null&&cateRecord.containsKey("score")){
					if(score==-100){
						score = (int)cateRecord.get("score");
					}else{
						score += (int)cateRecord.get("score");
					}
					
					desc = (String)cateRecord.get("desc");
					if(StringUtils.isNotEmpty(desc)){
						if(group2DescBuf.length()>0){
							group2DescBuf.append("，");
						}
						group2DescBuf.append(desc);
					}
				}
				//System.out.println("种类5， 利润率，param="+cateRecord);
			}
		}
		if(group2DescBuf.length()>0){
			targetBuf.append("；").append(group2DescBuf);
		}
		if(!group3.isEmpty()){
			//6 股东权益
			if(group3.containsKey("totequ")){
				cateRecord = (Map<String, Object>)group3.get("totequ");
				if(cateRecord!=null&&cateRecord.containsKey("score")){
					if(score==-100){
						score = (int)cateRecord.get("score");
					}else{
						score += (int)cateRecord.get("score");
					}
					desc = (String)cateRecord.get("desc");
					if(StringUtils.isNotEmpty(desc)){
						if(group2DescBuf.length()>0){
							targetBuf.append("，");
						}else{
							targetBuf.append("；");
						}
						targetBuf.append(desc);
					}
					//System.out.println("种类6， 股东权益，param="+cateRecord);
				}
			}
		}
		targetBuf.append("。");
		target.put("section1", targetBuf.toString());
		StringBuffer targetBuf2 = new StringBuffer("综上，该企业");
		if(score<=-6){
			targetBuf2.append("经营状况值得严重关注");
		}else if(score<=-3){
			targetBuf2.append("经营状况值得关注");
		}else if(score<=0){
			targetBuf2.append("经营状况一般");
		}else{
			targetBuf2.append("经营状况良好");
		}
		
		targetBuf2.append("。");
		target.put("section2", targetBuf2.toString());
		target.put("score", ""+score);
		return target;
	}

	private Map<String, Object> getCate6Analysis(String totequName, String registeredcapitalName,String netincName,
			Map<String, Object> currentYearData, Map<String, Object> preYearData) {
		Double curTotequValue = null;
		Double preTotequValue = null;
		Double curRegisteredcapitalValue = null;
		Double curNetincValue = null;
		Double rateValue = null;
		
		if(currentYearData.containsKey(totequName)){
			curTotequValue =(Double)currentYearData.get(totequName);
		}
		if(preYearData.containsKey(totequName)){
			preTotequValue = (Double)preYearData.get(totequName);
		}
		if(curTotequValue!=null){
			Map<String,Object> cate6 = new HashMap<String, Object>();
			if(curTotequValue<=0){
				cate6.put("score", -2);
				cate6.put("desc", "当前已资不抵债，需要股东注资");
				cate6.put("title", "资不抵债");
				cate6.put("color", RED);
			}else{
				if(currentYearData.containsKey(registeredcapitalName)){
					curRegisteredcapitalValue =(Double)currentYearData.get(registeredcapitalName);
				}
				if(currentYearData.containsKey(netincName)){
					curNetincValue =(Double)currentYearData.get(netincName);
				}
				if(preTotequValue!=null&&curRegisteredcapitalValue!=null&&curNetincValue!=null&&curRegisteredcapitalValue!=0.0){
					rateValue = (curTotequValue-preTotequValue-curNetincValue)/curRegisteredcapitalValue;
					
					if(rateValue>=0.5){
						cate6.put("score", 2);
						if(rateValue==0.0){
							cate6.put("desc", "股东权益异动率已达0，可能有大额注资行为");
						}else{
							String tempFormat = df.format(rateValue*100);
							if("0.00".equals(tempFormat)){
								tempFormat = "0";
							}
							cate6.put("desc", "股东权益异动率已达"+tempFormat+"%，可能有大额注资行为");
						}
						cate6.put("title", "可能有大额注资");
						cate6.put("color", BLUE);
						cate6.put("rate", rateValue);
					}else if(rateValue<=-0.5){
						cate6.put("score", -2);
						if(rateValue==0.0){
							cate6.put("desc", "股东权益异动率已达0，可能有大额抽资行为");
						}else{
							String tempFormat = df.format(rateValue*100);
							if("0.00".equals(tempFormat)){
								tempFormat = "0";
							}
							cate6.put("desc", "股东权益异动率已达"+tempFormat+"%，可能有大额抽资行为");
						}
						cate6.put("title", "可能有大额抽资");
						cate6.put("color", RED);
						cate6.put("rate", rateValue);
					}
					
				}
				
				if(cate6.isEmpty()&&curTotequValue!=null&&curRegisteredcapitalValue!=null&&curRegisteredcapitalValue!=0.0){
					Double rateValue2 = curTotequValue/curRegisteredcapitalValue;
					if(rateValue2>=0&&rateValue2<0.1){
						cate6.put("score", -1);
						cate6.put("desc", "股东权益较少，有可能需要股东注资");
						cate6.put("title", "股东权益较少");
						cate6.put("color", RED);
					}
				}
				
			}
			return cate6;
		}
		
		return null;
		
	}

	private Map<String, Object> getCate5Analysis(String key,
			Map<String, Object> currentYearData, Map<String, Object> preYearData) {
		Double curValue = null;
		Double preValue = null;
		Double rateValue = null;
		if(currentYearData.containsKey(key)&&preYearData.containsKey(key)){
			curValue =(Double)currentYearData.get(key);
			preValue = (Double)preYearData.get(key);
		}
		if(curValue!=null&&preValue!=null&&preValue!=0.0){
			Map<String,Object> cate5 = new HashMap<String, Object>();
			rateValue = (curValue-preValue)/Math.abs(preValue);
			cate5.put("rate", rateValue);
			cate5.put("curValue", curValue);
			if(curValue<0||preValue<0){
				cate5.put("score", -1);
				cate5.put("desc", "");
				cate5.put("title", "");
				cate5.put("color", "");
			}else if(curValue>0&&preValue>0){
				if(rateValue>=0.3){
					cate5.put("score", 2);
					cate5.put("desc", "盈利能力增长较快");
					cate5.put("title", "盈利能力增长较快");
					cate5.put("color", BLUE);
				}else if(rateValue>0.1){
					cate5.put("score", 1);
					cate5.put("desc", "盈利能力有所增长");
					cate5.put("title", "盈利能力有所增长");
					cate5.put("color", BLUE);
				}else if(rateValue>-0.1){
					cate5.put("score", 0);
					cate5.put("desc", "");
					cate5.put("title", "");
					cate5.put("color", "");
				}else if(rateValue>-0.3){
					cate5.put("score", -1);
					cate5.put("desc", "盈利能力有所下降");
					cate5.put("title", "盈利能力有所下降");
					cate5.put("color", RED);
				}else{
					cate5.put("score", -2);
					cate5.put("desc", "盈利能力下降较快");
					cate5.put("title", "盈利能力下降较快");
					cate5.put("color", RED);
				}
			}
			return cate5;
		}
		
		return null;
	}

	private Map<String, Object> getCate4Analysis(String key,
			Map<String, Object> currentYearData, Map<String, Object> preYearData) {
		
		Double curValue = null;
		Double preValue = null;
		Double rateValue = null;
		if(currentYearData.containsKey(key)){
			curValue =(Double)currentYearData.get(key);
		}
		if(curValue!=null){
			Map<String,Object> cate4 = null;
			StringBuffer desc = new StringBuffer();
			StringBuffer title = new StringBuffer();
			String color = "";
			int score = -100;
			if(curValue<0.1){
				cate4 = new HashMap<String, Object>();
				if(curValue==0.0){
					desc.append("总资产周转率0，运营能力过低");
				}else{
					String tempFormat = df.format(curValue*100);
					if("0.00".equals(tempFormat)){
						tempFormat = "0";
					}
					desc.append("总资产周转率").append(tempFormat).append("%，运营能力过低");
				}
				title.append("运营能力过低");
				color = RED;
				score=-2;
			}else{
				if(preYearData.containsKey(key)){
					preValue = (Double)preYearData.get(key);
				}
				if(preValue!=null&&preValue!=0.0){
					if(cate4==null){
						cate4=new HashMap<String, Object>();
					}
					rateValue = (curValue-preValue)/preValue;
					cate4.put("rate", rateValue);
					cate4.put("curValue", curValue);
					
					if(rateValue<=-0.3){
						if(desc.length()>0){
							desc.append("，");
						}
						desc.append("运营能力下降较快");
						title.append("运营能力下降较快");
						color = RED;
						score=-2;
					}else if(rateValue<-0.1){
						if(desc.length()>0){
							desc.append("，");
						}
						desc.append("运营能力有所下降");
						title.append("运营能力有所下降");
						color = RED;
						if(score!=-2){
							score=-1;
						}
					}else if(rateValue<=0.1){
						if(score!=-2){
							score=0;
						}
					}else if(rateValue<0.3){
						if(desc.length()>0){
							desc.append("，");
						}
						desc.append("运营能力有所增长");
						title.append("运营能力有所增长");
						color = BLUE;
						if(score!=-2){
							score=1;
						}
					}else{
						if(desc.length()>0){
							desc.append("，");
						}
						desc.append("运营能力增长较快");
						title.append("运营能力增长较快");
						color = BLUE;
						if(score!=-2){
							score=2;
						}
					}
					
				}
			}
			
			if(cate4!=null){
				cate4.put("score", score);
				cate4.put("desc", desc.toString());
				cate4.put("title", title.toString());
				cate4.put("color", color);
				return cate4;
			}
		}
		
		return null;
	}

	private Map<String, Object> getCate3Analysis(String key,
			Map<String, Object> currentYearData, Map<String, Object> preYearData) {
		
		Double curValue = null;
		Double preValue = null;
		Double rateValue = null;
		if(currentYearData.containsKey(key)){
			curValue =(Double)currentYearData.get(key);
		}
		if(curValue!=null){
			Map<String,Object> cate3 = null;
			StringBuffer desc = new StringBuffer();
			StringBuffer title = new StringBuffer();
			String color = "";
			int score = -100;
			if(curValue>=0.7){
				cate3 = new HashMap<String, Object>();
				desc.append("资产负债率偏高");
				title.append("资产负债率偏高");
				color = RED;
				score=-2;
			}else{
				if(preYearData.containsKey(key)){
					preValue = (Double)preYearData.get(key);
				}
				if(preValue!=null){
					if(cate3==null){
						cate3 = new HashMap<String, Object>();
					}
					rateValue = curValue-preValue;
					cate3.put("rate", rateValue);
					cate3.put("curValue", curValue);
					
					if(rateValue>=0.05){
						if(desc.length()>0){
							desc.append("，");
						}
						desc.append("资产负债率有一定比例上调");
						title.append("资产负债率上升");
						color = RED;
						score=-2;
					}else if(rateValue>0.02){
						
						if(score!=-2){
							score=-1;
						}
					}else if(rateValue>=-0.02){
						if(score!=-2){
							score=0;
						}
					}else if(rateValue>-0.05){
						
						if(score!=-2){
							score=1;
						}
					}else{
						if(desc.length()>0){
							desc.append("，");
						}
						desc.append("资产负债率有一定比例下调");
						title.append("资产负债率下降");
						color = BLUE;
						if(score!=-2){
							score=2;
						}
					}
				}
			}
			
			if(cate3!=null){
				cate3.put("score", score);
				cate3.put("desc", desc.toString());
				cate3.put("title", title.toString());
				cate3.put("color", color);
				return cate3;
			}
		}
		
		return null;
	}

	@Override
	public Map<String, Object> buildEachItemAnalysis(Map<String, Object> data) {
		Map<String, Object> target = new HashMap<String, Object>();
		if(data==null||data.isEmpty()){
			return null;
		}
		
		if(cateKeyList1==null||cateKeyList2==null){
			this.init();
		}
		Map<String,Object> cateData = null;
		
		String desc = null;
		//基础数据
		for(String cateKey:cateKeyList1){
			desc = null;
			if(data.containsKey(cateKey)){
				cateData = (Map<String,Object>)data.get(cateKey);
			}
			if(cateData!=null){
				desc = getCateKeyList1RecordDesc(cateData);
			}
			if(desc!=null&&desc.length()>0){
				target.put(cateKey, desc);
			}else{
				target.put(cateKey, "--");
			}
		}
		//能力指标
		for(String cateKey:cateKeyList2){
			desc = null;
			if(data.containsKey(cateKey)){
				cateData = (Map<String,Object>)data.get(cateKey);
			}
			if(cateData!=null){
				desc = getCateKeyList2RecordDesc(cateKey,cateData);
			}
			if(desc!=null&&desc.length()>0){
				target.put(cateKey, desc);
			}else{
				target.put(cateKey, "--");
			}
			
		}
		if(target!=null&&!target.isEmpty()){
			return target;
		}
		return null;
	}
	private String getCateKeyList2RecordDesc(String cateKey,Map<String, Object> cateData) {
		List<Integer> sortList = null;
		int length = 0;
		StringBuffer descBuf =  new StringBuffer();
		String tempRegionDesc = null;
		Map<String,Object> curYearCateData  = null;
		Map<String,Object> preYearCateData  = null;
		Map<String,Object> prePreYearCateData  = null;
		
		Double curYearCateDataValue  = null;
		Double preYearCateDataValue  = null;
		Double prePreYearCateDataValue  = null;
		
		if(cateData!=null&&!cateData.isEmpty()){
			sortList = new ArrayList<Integer>(); 
			for(String year : cateData.keySet()){
				if(StringUtils.isNumeric(year)){
					sortList.add(Integer.parseInt(year));
				}
			}
			Collections.sort(sortList);
			length = sortList.size();
			if(length>0){
				if(length==1){
					curYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-1));
					if(curYearCateData!=null){
						curYearCateDataValue = (Double)curYearCateData.get("value");
					}
					tempRegionDesc = this.getRegionDesc(curYearCateData);
					if(tempRegionDesc!=null&&tempRegionDesc.length()>0){
						descBuf.append(tempRegionDesc);
					}
				}else{ 
					if(sortList.get(length-1)-sortList.get(length-2)!=1){//最近的两年必须是连续的两年
						return null;
					}
					Integer trend = null;//趋势，增长或者下降
					 if(length==2){
						 
						curYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-1));
						preYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-2));
						
						if(curYearCateData!=null){
							curYearCateDataValue = (Double)curYearCateData.get("value");
						}
						if(preYearCateData!=null){
							preYearCateDataValue = (Double)preYearCateData.get("value");
						}
						if(curYearCateDataValue!=null&&preYearCateDataValue!=null){
							if(curYearCateDataValue!=0||preYearCateDataValue!=0){
								if(curYearCateDataValue-preYearCateDataValue>0){
									descBuf.append("近两年呈增长趋势");
									trend = 1;
								}else if(curYearCateDataValue-preYearCateDataValue<0){
									descBuf.append("近两年呈下降趋势");
									trend = -1;
								}else{
									descBuf.append("近两年保持不变");
								}
							}
							
						}
					}else if(length>=3){
						curYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-1));
						preYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-2));
						prePreYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-3));
						if(curYearCateData!=null){
							curYearCateDataValue = (Double)curYearCateData.get("value");
						}
						if(preYearCateData!=null){
							preYearCateDataValue = (Double)preYearCateData.get("value");
						}
						if(prePreYearCateData!=null){
							prePreYearCateDataValue = (Double)prePreYearCateData.get("value");
						}
						if((curYearCateDataValue!=null&&curYearCateDataValue!=0)
								||(preYearCateDataValue!=null&&preYearCateDataValue!=0)
								||(prePreYearCateDataValue!=null&&prePreYearCateDataValue!=0)){//三年数据至少有一年不为空且不为0
							//三年都不为空
							if(curYearCateDataValue!=null&&preYearCateDataValue!=null&&prePreYearCateDataValue!=null){
								if(curYearCateDataValue-preYearCateDataValue>0&&preYearCateDataValue-prePreYearCateDataValue>0){
									descBuf.append("近三年持续增长");
									trend = 1;
								}else if(curYearCateDataValue-preYearCateDataValue<0&&preYearCateDataValue-prePreYearCateDataValue<0){
									descBuf.append("近三年持续下降");
									trend = -1;
								}else if(curYearCateDataValue-preYearCateDataValue==0&&preYearCateDataValue-prePreYearCateDataValue==0
										&&curYearCateDataValue!=0){//排除三年都为0的情况
									descBuf.append("近三年保持不变");
								}
							}
							//最近两年都不为空
							if(curYearCateDataValue!=null&&preYearCateDataValue!=null
									&&descBuf.length()==0){//没有三年都不为空的评价
								if(curYearCateDataValue!=0||preYearCateDataValue!=0){
									if(curYearCateDataValue-preYearCateDataValue>0){
										descBuf.append("近两年呈增长趋势");
										trend = 1;
									}else if(curYearCateDataValue-preYearCateDataValue<0){
										descBuf.append("近两年呈下降趋势");
										trend = -1;
									}else{
										descBuf.append("近两年保持不变");
									}
								}
							}
							
						}
						
						
					}
					
					//标准值评价&& 有趋势变化
					if(curYearCateDataValue!=null&&trend!=null&&trend!=0){
						switch(cateKey){
							case "zcfzl"://资产负债率
								if(descBuf.length()>0){
									descBuf.append("；");
								}
								if(curYearCateDataValue>=0.7){
									if(trend>0){
										descBuf.append("且");
									}else{
										descBuf.append("但");
									}
									descBuf.append("近期负债水平偏高");
								}else if(curYearCateDataValue>=0.5){
									if(trend>0){
										descBuf.append("但");
									}else{
										descBuf.append("且");
									}
									descBuf.append("近期该指标值表现尚好");
								}else{
									if(trend>0){
										descBuf.append("但");
									}else{
										descBuf.append("且");
									}
									descBuf.append("近期负债水平较低");
								}
								
								break;
							case "jlrl"://净利润率
								if(descBuf.length()>0){
									descBuf.append("；");
								}
								if(curYearCateDataValue<0.1){
									if(trend>0){
										descBuf.append("但");
									}else{
										descBuf.append("且");
									}
									descBuf.append("近期该指标值偏低");
								}else{
									if(trend>0){
										descBuf.append("且");
									}else{
										descBuf.append("但");
									}
									
									descBuf.append("近期该企业盈利能力较强");
								}
								break;
							case "zcjlrl"://资产净利润率
								if(descBuf.length()>0){
									descBuf.append("；");
								}
								if(curYearCateDataValue<0.08){
									if(trend>0){
										descBuf.append("但");
									}else{
										descBuf.append("且");
									}
									descBuf.append("近期企业获利能力较弱");
								}else{
									if(trend>0){
										descBuf.append("且");
									}else{
										descBuf.append("但");
									}
									descBuf.append("近期企业获利能力较强");
								}
								
								break;
							case "zzczzl"://总资产周转率
								if(descBuf.length()>0){
									descBuf.append("；");
								}
								if(curYearCateDataValue<0.8){
									if(trend>0){
										descBuf.append("但");
									}else{
										descBuf.append("且");
									}
									descBuf.append("近期企业资产利用率较低");
								}else{
									if(trend>0){
										descBuf.append("且");
									}else{
										descBuf.append("但");
									}
									descBuf.append("近期企业运营情况较好");
								}
								break;
							default:
								break;
						}
					} 
				}
			}
		}
		if(descBuf!=null&&descBuf.length()>0){
			return descBuf.toString();
		}else{
			return null;
		}
	}
	private String getCateKeyList1RecordDesc(Map<String,Object> cateData){
		
		List<Integer> sortList = null;
		int length = 0;
		StringBuffer descBuf =  new StringBuffer();
		String tempRegionDesc = null;
		Map<String,Object> curYearCateData  = null;
		Map<String,Object> preYearCateData  = null;
		Map<String,Object> prePreYearCateData  = null;
		
		Double curYearCateDataValue  = null;
		Double preYearCateDataValue  = null;
		Double prePreYearCateDataValue  = null;
		
		
		if(cateData!=null&&!cateData.isEmpty()){
			sortList = new ArrayList<Integer>(); 
			for(String year : cateData.keySet()){
				if(StringUtils.isNumeric(year)){
					sortList.add(Integer.parseInt(year));
				}
			}
			Collections.sort(sortList);
			length = sortList.size();
			if(length>0){
				if(length==1){
					curYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-1));
					tempRegionDesc = this.getRegionDesc(curYearCateData);
					if(tempRegionDesc!=null&&tempRegionDesc.length()>0){
						descBuf.append(tempRegionDesc);
					}
				}else if(length==2){
					if(sortList.get(length-1)-sortList.get(length-2)!=1){//必须是连续的两年数据
						return null;
					}
					curYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-1));
					preYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-2));
					
					if(curYearCateData!=null){
						curYearCateDataValue = (Double)curYearCateData.get("value");
					}
					if(preYearCateData!=null){
						preYearCateDataValue = (Double)preYearCateData.get("value");
					}
					
					if(curYearCateDataValue!=null
							&&preYearCateDataValue!=null){
						if(curYearCateDataValue!=0||preYearCateDataValue!=0){
							if(curYearCateDataValue-preYearCateDataValue>0){
								descBuf.append("近两年呈增长趋势");
							}else if(curYearCateDataValue-preYearCateDataValue<0){
								descBuf.append("近两年呈下降趋势");
							}else{
								descBuf.append("近两年保持不变");
							}
							if(preYearCateDataValue!=0.0&&Math.abs((curYearCateDataValue-preYearCateDataValue)/preYearCateDataValue)>=0.2){
								descBuf.append("；同时波动较大，请关注");
							}
						}
						
					}
				}else if(length>=3){
					curYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-1));
					preYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-2));
					prePreYearCateData = (Map<String,Object>)cateData.get(""+sortList.get(length-3));
					if(curYearCateData!=null){
						curYearCateDataValue = (Double)curYearCateData.get("value");
					}
					if(preYearCateData!=null){
						preYearCateDataValue = (Double)preYearCateData.get("value");
					}
					if(prePreYearCateData!=null){
						prePreYearCateDataValue = (Double)prePreYearCateData.get("value");
					}
					if((curYearCateDataValue!=null&&curYearCateDataValue!=0)
							||(preYearCateDataValue!=null&&preYearCateDataValue!=0)
							||(prePreYearCateDataValue!=null&&prePreYearCateDataValue!=0)){//三年数据至少有一年不为空且不为0
						//三年都不为空
						if(curYearCateDataValue!=null&&preYearCateDataValue!=null&&prePreYearCateDataValue!=null){
							if(curYearCateDataValue-preYearCateDataValue>0&&preYearCateDataValue-prePreYearCateDataValue>0){
								descBuf.append("近三年持续增长");
							}else if(curYearCateDataValue-preYearCateDataValue<0&&preYearCateDataValue-prePreYearCateDataValue<0){
								descBuf.append("近三年持续下降");
							}else if(curYearCateDataValue-preYearCateDataValue==0&&preYearCateDataValue-prePreYearCateDataValue==0
									&&curYearCateDataValue!=0){//三年都为0，不评论
								descBuf.append("近三年保持不变");
							}
						}
						//最近两年都不为空
						if(curYearCateDataValue!=null&&preYearCateDataValue!=null){
							if(descBuf.length()==0 //即没有三年不为空的评价
									&& ( curYearCateDataValue!=0||preYearCateDataValue!=0)){
								if(curYearCateDataValue-preYearCateDataValue>0){
									descBuf.append("近两年呈增长趋势");
								}else if(curYearCateDataValue-preYearCateDataValue<0){
									descBuf.append("近两年呈下降趋势");
								}else{
									descBuf.append("近两年保持不变");
								}
							}
							if(preYearCateDataValue!=0){//preYearCateDataValue为0则公式无法计算
								if(Math.abs((curYearCateDataValue-preYearCateDataValue)/preYearCateDataValue)>=0.2){
									descBuf.append("；同时波动较大，请关注");
								}
							}
						}
						
					}
					
				}
			}
		}
		if(descBuf!=null&&descBuf.length()>0){
			return descBuf.toString();
		}else{
			return null;
		}
	}
	
	private String getRegionDesc(Map<String, Object> cateData){
		if(cateData==null){
			return null;
		}
		if(cateData.containsKey("region")&&cateData.get("region")!=null){
			Double region = (Double)cateData.get("region");
			if(region!=null){
				StringBuffer targetBuf = new StringBuffer("该指标处于在营企业");
				if(region>0.9){
					targetBuf.append("最后10%");
				}else if(region>0.8){
					targetBuf.append("80%-90%之间");
				}else if(region>0.7){
					targetBuf.append("70%-80%之间");
				}else if(region>0.6){
					targetBuf.append("60%-70%之间");
				}else if(region>0.5){
					targetBuf.append("50%-60%之间");
				}else if(region>0.4){
					targetBuf.append("40%-50%之间");
				}else if(region>0.3){
					targetBuf.append("30%-40%之间");
				}else if(region>0.2){
					targetBuf.append("20%-30%之间");
				}else if(region>0.1){
					targetBuf.append("10%-20%之间");
				}else if(region>=0){
					targetBuf.append("前10%");
				}
				targetBuf.append("的位置");
				return targetBuf.toString();
			}
		}
		return null;
	}
	
	private Map<String,Object> getCate2Analysis(String key,Map<String,Object> currentYearData,Map<String,Object> preYearData){
		Double curValue = null;
		Double preValue = null;
		Double rateValue = null;
		if(currentYearData.containsKey(key)&&preYearData.containsKey(key)){
			curValue =(Double)currentYearData.get(key);
			preValue = (Double)preYearData.get(key);
		}
		if(curValue!=null&&preValue!=null&&preValue!=0.0){
			Map<String,Object> cate2 = new HashMap<String, Object>();
			rateValue = (curValue-preValue)/Math.abs(preValue);
			cate2.put("rate", rateValue);
			cate2.put("curValue", curValue);
			if(preValue<0){
				if(rateValue<0){
					cate2.put("score", -2);
					cate2.put("desc", "亏损持续扩大");
					cate2.put("title", "亏损持续扩大");
					cate2.put("color", RED);
				}else if(rateValue<0.5){
					cate2.put("score", -1);
					cate2.put("desc", "亏损有所收窄");
					cate2.put("title", "亏损有所收窄");
					cate2.put("color", BLUE);
				}else if(rateValue<1.0){
					cate2.put("score", 0);
					cate2.put("desc", "亏损大幅收窄");
					cate2.put("title", "亏损大幅收窄");
					cate2.put("color", BLUE);
				}else{
					cate2.put("score", 1);
					cate2.put("desc", "扭亏为盈");
					cate2.put("title", "扭亏为盈");
					cate2.put("color", BLUE);
				}
			}else if(preValue>0){
				if(rateValue<-1.0){
					cate2.put("score", -1);
					cate2.put("desc", "转盈为亏");
					cate2.put("title", "转盈为亏");
					cate2.put("color", RED);
				}else if(rateValue<0){
					cate2.put("score", 0);
				    cate2.put("desc", "净利润减少");
				    cate2.put("title", "净利润减少");
					cate2.put("color", RED);
				}else if(rateValue<=0.5){
					cate2.put("score", 1);
				    cate2.put("desc", "净利润有所增长");
				    cate2.put("title", "净利润有所增长");
					cate2.put("color", BLUE);
				}else{
					cate2.put("score", 2);
				    cate2.put("desc", "净利润增长较快");
				    cate2.put("title", "净利润增长较快");
					cate2.put("color", BLUE);
				}
			}
			return cate2;
	    }
		return null;
	}
	private Map<String,Object> getCate1Analysis(String key,Map<String,Object> currentYearData,Map<String,Object> preYearData){
		
		Double curValue = null;
		Double preValue = null;
		Double rateValue = null;
		if(currentYearData.containsKey(key)&&preYearData.containsKey(key)){
			curValue =(Double)currentYearData.get(key);
			preValue = (Double)preYearData.get(key);
		}
		if(curValue!=null&&preValue!=null&&preValue!=0.0){
			Map<String,Object> cate1 = new HashMap<String, Object>();
			rateValue = (curValue-preValue)/preValue;
			cate1.put("rate", rateValue);
			cate1.put("curValue", curValue);
			if(rateValue>=0.5){
				cate1.put("score", 1);
				cate1.put("desc", "营业收入增长较快");
				cate1.put("title", "营业收入增长较快");
				cate1.put("color", BLUE);
			}else if(rateValue>=0){
				cate1.put("score", 0);
				cate1.put("desc", "");
				cate1.put("title", "");
				cate1.put("color", "");
			}else if(rateValue>=-0.5){
				cate1.put("score", -1);
				cate1.put("desc", "营业收入有所下降");
				cate1.put("title", "营业收入有所下降");
				cate1.put("color", RED);
			}else{
				cate1.put("score", -2);
				cate1.put("desc", "营业收入下降较快");
				cate1.put("title", "营业收入下降较快");
				cate1.put("color", RED);
			}
			return cate1;
		}
		return null;
	}
	
	private Double getDoubleValue(String source,Integer limit){
		try{
			if(StringUtils.isEmpty(source)||"--".equals(source)||"null".equals(source)||"\\N".equals(source)){
				return null;
			}
			
			Double dd = Double.parseDouble(source);
			if(limit!=null){
				Double   tempTarget   =   new   BigDecimal(dd).setScale(limit.intValue(),   BigDecimal.ROUND_HALF_UP).doubleValue();  
				
				return tempTarget;
			}
			return dd;
		}catch(Exception e){
			return null;
		}
	}
	
	
	@Override
	public MonitorCompanyFinanceSituation getCompanyFinanceSituationByCompanyId(String companyId) {
		
		return monitorCompanyFinanceSituationDao.getCompanyFinanceSituationByCompanyId(companyId);
	}
}
