package com.srd.ljzd.serviceImpl.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.srd.ljzd.dao.thirdparty.biz.BizDAO;
import com.srd.ljzd.dataAnalysis.changeRecord.comparator.FaRenComparator;
import com.srd.ljzd.dataAnalysis.changeRecord.comparator.GaoGuanComparator;
import com.srd.ljzd.dataAnalysis.changeRecord.comparator.GuDongComparator;
import com.srd.ljzd.dataAnalysis.changeRecord.comparator.JingYingFanWeiComparator;
import com.srd.ljzd.dataAnalysis.changeRecord.extractor.FaRenExtractor;
import com.srd.ljzd.dataAnalysis.changeRecord.extractor.GaoGuanExtrator;
import com.srd.ljzd.dataAnalysis.changeRecord.extractor.GuDongExtractor;
import com.srd.ljzd.dataAnalysis.changeRecord.extractor.JingYingFanWeiExtractor;
import com.srd.ljzd.dataAnalysis.changeRecord.replacer.Replacer;
import com.srd.ljzd.entity.biz.BizChangeRecordHolder;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entityparser.BizJsonBrandParser;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.util.BizComparator;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ModuleEnum;
import com.srd.ljzd.util.PropertiesLoader;
import com.srd.ljzd.util.ThirdPartChannelEnum;
@Service("bizService")
public class BizServiceImpl implements BizService {
	protected static Logger log = LogManager.getLogger(BizServiceImpl.class.getName());
	@Autowired
	BizDAO bizDAO;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private HttpSession session;
	@Autowired
	BizJsonBrandParser bizJsonBrandParser;
	
	@Override
	public BizMsg getBrefCompany(String companyName,ThirdPartChannelEnum channel){
		BizMsg company = null;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel.toString())){
			company = bizDAO.remoteGongShangMsgWithQiChaCha(companyName);
		}else{
			company = bizDAO.remoteGongShangMsg(companyName);
		}
		
		if(company!=null&&company.getBrefCompany()!=null){
			
			//返回的公司名称中如果包含英文的小括号，则转为中文的小括号
	        String innerName = company.getBrefCompany().get("enterprisename");
			if(innerName!=null&&(innerName.contains("(")||innerName.contains(")"))){//如果包含英文小括号
				StringBuffer buf = new StringBuffer(innerName);
				int index = -1;
				if((index=buf.indexOf("(")) >-1){
					buf.replace(index, index+1, "（");
				}
				index=-1;
				if((index=buf.indexOf(")"))>-1){
					buf.replace(index, index+1, "）");
				}
				company.getBrefCompany().put("enterprisename", buf.toString());
				companyName = buf.toString();
			}
			if(innerName==null){
				log.error("返回的公司名称为空，sourceName="+companyName);
			}
		}
		return company;
	}
	
	@Override
	public BizMsg getGongShangMsg(String companyName,ThirdPartChannelEnum channel) {
		
		BizMsg company = null;
		if(channel!=null&&ThirdPartChannelEnum.QI_CHA_CHA.toString().equals(channel.toString())){
			company = bizDAO.remoteGongShangMsgWithQiChaCha(companyName);
		}else{
			company = bizDAO.remoteGongShangMsg(companyName);
		}
		
		if(company!=null&&company.getBrefCompany()!=null){
			
			//返回的公司名称中如果包含英文的小括号，则转为中文的小括号
	        String innerName = company.getBrefCompany().get("enterprisename");
			if(innerName.contains("(")||innerName.contains(")")){//如果包含英文小括号
				StringBuffer buf = new StringBuffer(innerName);
				int index = -1;
				if((index=buf.indexOf("(")) >-1){
					buf.replace(index, index+1, "（");
				}
				index=-1;
				if((index=buf.indexOf(")"))>-1){
					buf.replace(index, index+1, "）");
				}
				company.getBrefCompany().put("enterprisename", buf.toString());
				companyName = buf.toString();
			}
			
			BizChangeRecordHolder holder = null;
			if(company.getBianGengJiLuArray()!=null&&company.getBianGengJiLuArray().size()>0){
				//变更记录 “变更事项”处理()及去重复，变更事项和变更时间都一样，认为是重复
//				company.setBianGengJiLuArray(this.categoryAndRepeatedRemoveOfBiangGeng(company.getBianGengJiLuArray()));
				
				this.categoryBiangGeng(company.getBianGengJiLuArray());
				
				//高亮显示
				this.hightLightWords(company.getBianGengJiLuArray());
				
				//构造变更记录时间轴数据结构
				holder = this.buildTimeAxisDataStructure(company.getBianGengJiLuArray(),companyName);
				
			}
			
			//商标
			company.setBrandArray(this.getBrand(companyName));
			
			this.sort(company);
			
			this.cache(companyName, company, holder,channel);
			
		}
		
		return company;
	}
	/**
	 * 获取原始的工商信息（不进行变更记录去重，排序，缓存等等）
	 */
	@Override
	public BizMsg getOriginalGongShangMsg(String companyName) {
		return bizDAO.remoteGongShangMsg(companyName);
	}
	/**
	 * 变更记录的 变更事项名称  归类
	 * @param sourceArray
	 * @return
	 */
	@Override
	public ArrayList<HashMap<String, String>> categoryBiangGeng(
			ArrayList<HashMap<String, String>> sourceArray) {

		if(sourceArray!=null&&sourceArray.size()>0){
			//变更记录对照表
			Properties changeCategoryPro = (Properties) redisTemplate.opsForValue().get("changeCategoryPro");
			
			if(changeCategoryPro==null){
				PropertiesLoader loader = new PropertiesLoader("/resources/data/biz_chang_category_map.properties");
				changeCategoryPro = loader.getProperties();
				redisTemplate.opsForValue().set("changeCategoryPro", changeCategoryPro, 24*60*60, TimeUnit.SECONDS);
			}
			String sourceCategory = null;
			String targetCategory = null;//新的变更事项
			for(HashMap<String, String> record : sourceArray){
				
				sourceCategory = record.get("changedannouncement_events")!=null?record.get("changedannouncement_events").trim():null;
				//log.info("source item="+record);
				//日期和变更事项 不为空 ,且变更记录对照表中要有该变更事项的对照
				if(StringUtils.isEmpty(sourceCategory)){
					record.put("changedannouncement_events", "--");
					record.put("changedannouncement_events_category", "--");
					log.info("sourceCategory is null,params="+record);
					continue;
				}
				//从对照表中读取新的变更事项的名称
				if(changeCategoryPro.containsKey(sourceCategory)){
					targetCategory = (String)changeCategoryPro.get(sourceCategory);
				}
				
				if(!StringUtils.isEmpty(targetCategory)){
						record.put("changedannouncement_events_category", targetCategory);
				}else{
					log.info("targetCategory is not map,params="+record);
				}
				targetCategory = null;
				
			}
			
			
		}
		return sourceArray;
	}
	
	//变更记录 “变更事项”处理()及去重复，变更事项和变更时间都一样，认为是重复
	/**
	 * 业务逻辑变更，不需要去重逻辑
	 */
	@Deprecated
	@Override
	public ArrayList<HashMap<String, String>> categoryAndRepeatedRemoveOfBiangGeng(
			ArrayList<HashMap<String, String>> sourceArray) {
        HashMap<String, HashMap<String, String>> noRepeatTempHandler = new HashMap<String, HashMap<String,String>>();
		
		//变更记录对照表
		Properties changeCategoryPro = (Properties) redisTemplate.opsForValue().get("changeCategoryPro");
		
		if(changeCategoryPro==null){
			PropertiesLoader loader = new PropertiesLoader("/resources/data/biz_chang_category_map.properties");
			changeCategoryPro = loader.getProperties();
			redisTemplate.opsForValue().set("changeCategoryPro", changeCategoryPro, 24*60*60, TimeUnit.SECONDS);
		}
		
		Date tmpDate = null;//变更日期
		String targetCategory = null;//新的变更事项
		StringBuffer key = null;//变更事项和变更时间的简单拼接作为key,放到map中，去掉重复
		String sourceCategory = null;
		for(HashMap<String, String> record : sourceArray){
			
			tmpDate = DateUtils.getLocalDate(record.get(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr()));
			sourceCategory = record.get("changedannouncement_events")!=null?record.get("changedannouncement_events").trim():null;
			//log.info("source item="+record);
			//日期和变更事项 不为空 ,且变更记录对照表中要有该变更事项的对照
			if(tmpDate==null||StringUtils.isEmpty(sourceCategory)
					||!changeCategoryPro.containsKey(sourceCategory)){
				log.warn("data or targetCategory is null,params="+record);
				continue;
			}
			//从对照表中读取新的变更事项的名称
			targetCategory = (String)changeCategoryPro.get(sourceCategory);
			
			if(!StringUtils.isEmpty(targetCategory)){
				key = new StringBuffer(targetCategory);
				key.append(DateUtils.getLocalStr("yyyy-MM-dd", tmpDate));
				if(!noRepeatTempHandler.containsKey(key)){
					record.put("changedannouncement_events_source", sourceCategory);
					record.put("changedannouncement_events", targetCategory);
					noRepeatTempHandler.put(key.toString(), record);
					//log.info("target item="+record);
				}else{
					log.warn("repeat occurred,params="+record);
				}
			}else{
				log.warn("targetCategory is not map,params="+record);
			}
		}
		
		return new ArrayList(noRepeatTempHandler.values());
	}
	//高亮显示变更记录
	@Override
	public void hightLightWords(
			ArrayList<HashMap<String, String>> bianGengJiLuArray) {
		if(bianGengJiLuArray!=null&&bianGengJiLuArray.size()>0){
			String category = null;
			List<Map<String, Object>> beforeWords= null;
			List<Map<String, Object>> afterWords= null;
			Set<String> highWords =null;
			String targetStr = null;
			for(HashMap<String,String> record : bianGengJiLuArray){
				category = record.get("changedannouncement_events_category");
				if(category!=null&&!"".equals(category.trim())&&!"--".equals(category.trim())){
					
					switch(category){
					   case "法定代表人":
						   
						   beforeWords = FaRenExtractor.getInstance().extract(record.get("changedannouncement_before"));
						   afterWords = FaRenExtractor.getInstance().extract(record.get("changedannouncement_after"));
						   //法定代表人，只有一个
						   if(beforeWords!=null&&beforeWords.size()==1){
							   //求两个集合差集
							   highWords = FaRenComparator.getInstance().setDiff(beforeWords, afterWords);
							   if(highWords!=null&&highWords.size()>0){
								   targetStr = Replacer.getInstance().replace(record.get("changedannouncement_before"), highWords);
								   record.put("changedannouncement_before",targetStr);
							   }
						   }
						   
						   break;
						   
                       case "股东":
                    	   beforeWords = GuDongExtractor.getInstance().extract(record.get("changedannouncement_before"));
						   afterWords = GuDongExtractor.getInstance().extract(record.get("changedannouncement_after"));
						   
						   if(beforeWords!=null){
							   //求两个集合差集
							   highWords = GuDongComparator.getInstance().setDiff(beforeWords, afterWords);
							   if(highWords!=null&&highWords.size()>0){
								   targetStr = Replacer.getInstance().replace(record.get("changedannouncement_before"), highWords);
								   record.put("changedannouncement_before",targetStr);
							   }
						   }
						   break;
                       case "董事备案":
                       case "经理备案":
                       case "高管":
                    	   beforeWords = GaoGuanExtrator.getInstance().extract(record.get("changedannouncement_before"), 
                    			   record.get("changedannouncement_events"));
                    	   afterWords = GaoGuanExtrator.getInstance().extract(record.get("changedannouncement_after"), 
                    			   record.get("changedannouncement_events"));
                    	   
                    	   if(beforeWords!=null){
							   //求两个集合差集
							   highWords = GaoGuanComparator.getInstance().setDiff(beforeWords, afterWords);
							   if(highWords!=null&&highWords.size()>0){
								   targetStr = Replacer.getInstance().replace(record.get("changedannouncement_before"), highWords);
								   record.put("changedannouncement_before",targetStr);
							   }
						   }
                    	   
                    	   break;
						   
                       case "经营范围":
                    	   beforeWords = JingYingFanWeiExtractor.getInstance().extract(record.get("changedannouncement_before"));
						   afterWords = JingYingFanWeiExtractor.getInstance().extract(record.get("changedannouncement_after"));
						   
						   if(beforeWords!=null&&afterWords!=null){
							   //求两个集合差集
							   highWords = JingYingFanWeiComparator.getInstance().setDiff(beforeWords, afterWords);
							   if(highWords!=null&&highWords.size()>0){
								   targetStr = Replacer.getInstance().replace(record.get("changedannouncement_before"), highWords);
								   record.put("changedannouncement_before",targetStr);
							   }
						   }
                    	   break;
					
					}
				}
			}
			
		}
	}
	//构造变更记录时间轴数据结构
	@Override
	public BizChangeRecordHolder buildTimeAxisDataStructure(
			ArrayList<HashMap<String, String>> sourceArray,String companyName) {
		BizChangeRecordHolder holder = new BizChangeRecordHolder();
		
		String dateKey = null;
		String categoryKey = null;
		TreeMap<String,List<HashMap<String,String>>> categoryMap = null;
		List<HashMap<String,String>> recordList = null;
		for(HashMap<String, String> record:sourceArray){
			//变更记录的时间字段，在之前已经处理过了，这里的时间字段肯定不为空且有效
			dateKey = DateUtils.getLocalStrNew("yyyy-MM-dd", DateUtils.getLocalDate(record.get(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr())));
			if(StringUtils.isEmpty(dateKey)){
				log.info("dateKey is null,companyName="+companyName+",record="+record);
				continue;
			}
			//构造timeMap
			if(holder.getTimeAxis().containsKey(dateKey)){
				categoryMap = holder.getTimeAxis().get(dateKey);
			}else{
				categoryMap = new TreeMap<String, List<HashMap<String,String>>>();
				holder.getTimeAxis().put(dateKey, categoryMap);
			}
			
			categoryKey = record.get("changedannouncement_events_category");
			if(StringUtils.isEmpty(categoryKey)){
				log.info("new categoryKey is null,companyName"+companyName+",record="+record);
				categoryKey = record.get("changedannouncement_events");
				if(StringUtils.isEmpty(categoryKey)){
					log.info("source categoryKey is still null,companyName"+companyName);
					continue;
				}
			}
			if(categoryMap.containsKey(categoryKey)){
				recordList = categoryMap.get(categoryKey);
			}else{
				recordList = new ArrayList<HashMap<String,String>>();
				categoryMap.put(categoryKey, recordList);
			}
			recordList.add(record);
			//构造holder.getTimeAxis()结束
			
			//构造sortedDate
			if(holder.getSortedDate().containsKey(dateKey)){
				holder.getSortedDate().put(dateKey, holder.getSortedDate().get(dateKey)+1);
			}else{
				holder.getSortedDate().put(dateKey, 1);
			}
			
			//构造category
			if(holder.getCategory().containsKey(categoryKey)){
				holder.getCategory().put(categoryKey, holder.getCategory().get(categoryKey)+1);
			}else{
				holder.getCategory().put(categoryKey, 1);
			}
		}
	    
		return holder;
	}

	@Override
	public void sort(BizMsg company) {
        BizComparator bizComparator = new BizComparator();
		
		if(company.getBianGengJiLuArray()!=null&&company.getBianGengJiLuArray().size()>0){
			Collections.sort(company.getBianGengJiLuArray(), bizComparator);
		}
		if(company.getDongChanDiYaArray()!=null&&company.getDongChanDiYaArray().size()>0){
			Collections.sort(company.getDongChanDiYaArray(), bizComparator);
		}
		if(company.getShareholderArray()!=null&&company.getShareholderArray().size()>0){
			Collections.sort(company.getShareholderArray(), bizComparator);
		}
		if(company.getJingYingYiChangArray()!=null&&company.getJingYingYiChangArray().size()>0){
			Collections.sort(company.getJingYingYiChangArray(), bizComparator);
		}
		if(company.getAdministrativePenaltyArray()!=null&&company.getAdministrativePenaltyArray().size()>0){
			Collections.sort(company.getAdministrativePenaltyArray(), bizComparator);
		}
		
		if(company.getBrandArray()!=null&&company.getBrandArray().size()>0){
			Collections.sort(company.getBrandArray(), bizComparator);
		}
		
	}

	@Override
	public void cache(String companyName,BizMsg company, BizChangeRecordHolder holder,ThirdPartChannelEnum channel) {
		if(company!=null&&company.getBrefCompany()!=null){
			
			StringBuffer keyBuf = new StringBuffer(companyName).append(channel.toString());
			redisTemplate.delete(keyBuf.toString()+"_bizMsg");
			redisTemplate.opsForValue().set(keyBuf.toString()+"_bizMsg", company, 2*60*60, TimeUnit.SECONDS);//2小时
			if(holder!=null){
				redisTemplate.delete(keyBuf.toString()+"_category");
				redisTemplate.opsForValue().set(keyBuf.toString()+"_category", holder.getCategory(), 2*60*60, TimeUnit.SECONDS);//2小时
				
				redisTemplate.delete(keyBuf.toString()+"_sortedDate");
				redisTemplate.opsForValue().set(keyBuf.toString()+"_sortedDate", holder.getSortedDate(), 2*60*60, TimeUnit.SECONDS);//2小时
				
				redisTemplate.delete(keyBuf.toString()+"_timeAxis");
				redisTemplate.opsForValue().set(keyBuf.toString()+"_timeAxis", holder.getTimeAxis(), 2*60*60, TimeUnit.SECONDS);//2小时
			}
		}
		
	}
	@Override
	public Map<String, Object> initPage(int curPage, int pageSize,
			ArrayList<HashMap<String, String>> array) {
		if (curPage <= 0) {
			curPage = 1;
		}
		if (pageSize < 0) {
			pageSize = 5;
		}

		Map<String, Object> page = new HashMap<String, Object>();
		page.put("curPage", curPage);
		page.put("pageSize", pageSize);
		int size = 0;
		if (array != null && array.size() > 0) {
			size = array.size();
		}
		page.put("size", size);
		page.put("pageNum", (size % pageSize == 0) ? (size / pageSize) : (size
				/ pageSize + 1));
		page.put("startIndex", pageSize * (curPage - 1));
		page.put("endIndex", pageSize * curPage - 1);
		return page;
	}
	@Override
	public BizMsg getCachedBizMsg(String companyName,ThirdPartChannelEnum channel) {
		
		StringBuffer keyBuf = new StringBuffer(companyName).append(channel.toString());
		BizMsg company = (BizMsg) redisTemplate.opsForValue().get(keyBuf.append("_bizMsg").toString());
		
		if(company == null){
			company = this.getGongShangMsg(companyName,channel);
		}
		
		return company;
	}
	@Override
	public void buildPageBref(BizMsg company, int curPage, int pageSize,
			Model model) {
		//股东
		Map<String,Object> guDongPage = this.initPage(curPage,pageSize, company==null?null:company.getShareholderArray());
		model.addAttribute("guDongArray",  company==null?null:company.getShareholderArray());
		session.setAttribute("guDongPage", guDongPage);
		
		//主要人员
		Map<String,Object> zhuYaoRenYuanPage = this.initPage(curPage,pageSize,  company==null?null:company.getKeyPersonArray());
		model.addAttribute("zhuYaoRenYuanArray",  company==null?null:company.getKeyPersonArray());
		session.setAttribute("zhuYaoRenYuanPage", zhuYaoRenYuanPage);
		
		//变更记录
		Map<String,Object> bianGengJiLuPage = this.initPage(curPage,pageSize,  company==null?null:company.getBianGengJiLuArray());
		model.addAttribute("bianGengJiLuArray",  company==null?null:company.getBianGengJiLuArray());
		session.setAttribute("bianGengJiLuPage", bianGengJiLuPage);
		
		//变更记录的变更事项列表
		if(company!=null&&company.getBianGengJiLuArray()!=null&&company.getBianGengJiLuArray().size()>0){
			Map<String,Object> category = this.getBianGengJiLuShowCategory(company.getBrefCompany().get("enterprisename"),ThirdPartChannelEnum.QI_CHA_CHA);
			model.addAttribute("bianGengJiLuShowCategory", category);
			
			Map<String,Integer> sortedDate = this.getCachedSortedDate(company.getBrefCompany().get("enterprisename"),ThirdPartChannelEnum.QI_CHA_CHA);
			Map<String, Object> timeAxis = this.getCachedTimeAxis(company.getBrefCompany().get("enterprisename"),ThirdPartChannelEnum.QI_CHA_CHA);
			
			model.addAttribute("bianGengJiLuSortedDate", sortedDate);
			model.addAttribute("bianGengJiLuTimeAxis", timeAxis);
		}
	}
	@Override
	public void buildPage(BizMsg company, int curPage,int pageSize, Model model) {
		
		//股东
		Map<String,Object> guDongPage = this.initPage(curPage,pageSize, company.getShareholderArray());
		model.addAttribute("guDongArray", company.getShareholderArray());
		session.setAttribute("guDongPage", guDongPage);
		
		//主要人员
		Map<String,Object> zhuYaoRenYuanPage = this.initPage(curPage,pageSize, company.getKeyPersonArray());
		model.addAttribute("zhuYaoRenYuanArray", company.getKeyPersonArray());
		session.setAttribute("zhuYaoRenYuanPage", zhuYaoRenYuanPage);
		
		//分支机构
		Map<String,Object> fiLiationPage = this.initPage(curPage,pageSize, company.getFiLiationArray());
		model.addAttribute("fiLiationArray", company.getFiLiationArray());
		session.setAttribute("fiLiationPage", fiLiationPage);
		
		//变更记录
		Map<String,Object> bianGengJiLuPage = this.initPage(curPage,pageSize, company.getBianGengJiLuArray());
		model.addAttribute("bianGengJiLuArray", company.getBianGengJiLuArray());
		session.setAttribute("bianGengJiLuPage", bianGengJiLuPage);
		
		//变更记录的变更事项列表
		if(company.getBianGengJiLuArray()!=null&&company.getBianGengJiLuArray().size()>0){
			Map<String,Object> category = this.getBianGengJiLuShowCategory(company.getBrefCompany().get("enterprisename"),ThirdPartChannelEnum.ZHONG_SHU);
			model.addAttribute("bianGengJiLuShowCategory", category);
			
			Map<String,Integer> sortedDate = this.getCachedSortedDate(company.getBrefCompany().get("enterprisename"),ThirdPartChannelEnum.ZHONG_SHU);
			Map<String, Object> timeAxis = this.getCachedTimeAxis(company.getBrefCompany().get("enterprisename"),ThirdPartChannelEnum.ZHONG_SHU);
			
			model.addAttribute("bianGengJiLuSortedDate", sortedDate);
			model.addAttribute("bianGengJiLuTimeAxis", timeAxis);
		}
		
		//对外投资
		Map<String,Object> entInvPage = this.initPage(curPage,pageSize, company.getEntInvArray());
		model.addAttribute("entInvArray", company.getEntInvArray());
		session.setAttribute("entInvPage", entInvPage);
		
		//股权出质
		Map<String,Object> shareSIMPAWNPage = this.initPage(curPage,pageSize, company.getShareSIMPAWNArray());
		model.addAttribute("shareSIMPAWNArray", company.getShareSIMPAWNArray());
		session.setAttribute("shareSIMPAWNPage", shareSIMPAWNPage);
		
		//动产质押
		Map<String,Object> dongChanDiYaPage = this.initPage(curPage,pageSize, company.getDongChanDiYaArray());
		model.addAttribute("dongChanDiYaArray", company.getDongChanDiYaArray());
		session.setAttribute("dongChanDiYaPage", dongChanDiYaPage);
		
		//经营异常
		Map<String,Object> jingYingYiChangPage = this.initPage(curPage,pageSize, company.getJingYingYiChangArray());
		model.addAttribute("jingYingYiChangArray", company.getJingYingYiChangArray());
		session.setAttribute("jingYingYiChangPage", jingYingYiChangPage);
		
		//行政处罚 
		Map<String,Object> administrativePenaltyPage = this.initPage(curPage,pageSize, company.getAdministrativePenaltyArray());
		model.addAttribute("administrativePenaltyArray", company.getAdministrativePenaltyArray());
		session.setAttribute("administrativePenaltyPage", administrativePenaltyPage);
		
		//商标
		Map<String,Object> brandPage = this.initPage(curPage,pageSize, company.getBrandArray());
		model.addAttribute("brandArray", company.getBrandArray());
		session.setAttribute("brandPage", brandPage);
		
		
	}
	private Map<String, Object> getBianGengJiLuShowCategory(String companyName,ThirdPartChannelEnum channel) {
	    Map<String,Integer> sourceCategory =  this.getBianGengJiLuCachedCategory(companyName,channel);
		
		Map<String,Object> targetCategory = new HashMap<String, Object>();
		
		
		for(String name : Constant.sortedName){
			if(sourceCategory!=null&&sourceCategory.get(name)!=null){
				targetCategory.put(name, sourceCategory.get(name));
			}else{
				targetCategory.put(name, 0);
			}
		}
		//统计 “其他”
		int otherNum = 0;
		if(sourceCategory!=null){
			for(String name : sourceCategory.keySet()){
				if(name!=null&&!"".equals(name)){
					if(! Constant.sortedName.contains(name)){
						otherNum += (sourceCategory.get(name)==null?0:sourceCategory.get(name));
					}
				}
			}
		}
		//sortedName.add("其他");
		targetCategory.put("其他", otherNum);
		targetCategory.put("sortedName",  Constant.sortedName);
		return targetCategory;
	}
	@Override
	public Map<String, Integer> getBianGengJiLuCachedCategory(String key,ThirdPartChannelEnum channel) {
		StringBuffer keyBuf = new StringBuffer(key).append(channel.toString());
	    
		Map<String, Integer> category = (Map<String, Integer>)redisTemplate.opsForValue().get(keyBuf.append("_category").toString());
		
		if(category==null){
			getGongShangMsg(key,channel);
			category = (Map<String, Integer>)redisTemplate.opsForValue().get(keyBuf.toString());
		}
		return category;
	}
	//从缓存中读取变更记录时间轴主键（时间）列表
	@Override
	public Map<String, Integer> getCachedSortedDate(String key,ThirdPartChannelEnum channel) {
		StringBuffer keyBuf =new StringBuffer(key).append(channel.toString());
	    
		Map<String, Integer> sortedDate = (Map<String, Integer>)redisTemplate.opsForValue().get(keyBuf.append("_sortedDate").toString());
		if(sortedDate==null){
			getGongShangMsg(key,channel);
			sortedDate = (Map<String, Integer>)redisTemplate.opsForValue().get(keyBuf.toString());
		}
		return sortedDate;
	}
	//从缓存中读取时间轴数据
	@Override
	public Map<String, Object> getCachedTimeAxis(String key,ThirdPartChannelEnum channel) {
		StringBuffer keyBuf = new StringBuffer(key).append(channel.toString());
	    
		Map<String, Object> timeAxis = (Map<String, Object>)redisTemplate.opsForValue().get(keyBuf.append("_timeAxis").toString());
		if(timeAxis==null){
			getGongShangMsg(key,channel);
			timeAxis = (Map<String, Object>)redisTemplate.opsForValue().get(keyBuf.toString());
		}
		return timeAxis;
	}
	
	@Override
	public ArrayList<HashMap<String, String>> getBrand(String companyName) {
		// TODO Auto-generated method stub
		JSONArray array = bizDAO.getBrand(companyName);
		if(array!=null&&array.size()>0){
			 return bizJsonBrandParser.parse(array).getBrandArray();
		}
	    return null;
	}
	@Override
	public Map<String, Integer> getChageRecordCount(String companyName,
			Integer timeLimit, List<String> modules,ThirdPartChannelEnum channel) {
		Map<String, Object> timeAxis = this.getCachedTimeAxis(companyName,channel);
		Map<String,Integer> sortedDate = this.getCachedSortedDate(companyName,channel);
		
		if(timeAxis==null||sortedDate==null){
			return null;
		}
		if(timeLimit==null){
			timeLimit = -2;
		}
		if(timeLimit>0){
			timeLimit = -1*timeLimit;
		}
		Map<String,Integer> result = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, timeLimit);
		Date limitDate = cal.getTime();
		TreeMap<String,List<HashMap<String,String>>> categoryMap = null;
		List<HashMap<String,String>> recordList = null;
		for (String dateKey : sortedDate.keySet()) {  
			  if(DateUtils.getLocalDate(DateUtils.formatPattern, dateKey).after(limitDate)){
				  if(timeAxis.containsKey(dateKey)){
					  categoryMap = (TreeMap<String,List<HashMap<String,String>>>)timeAxis.get(dateKey);
					  if(categoryMap!=null){
                          for(String module : modules){
                        	  recordList = categoryMap.get(module);
                        	  if(recordList!=null){
                        		  if(result.containsKey(module)){
                        			  result.put(module, recordList.size()+result.get(module));
                        		  }else{
                        			  result.put(module, recordList.size());
                        		  }
                        	  }
                          }
					  }
				  }
			  }else{
				  //因为sortedDate是按时间的倒序排序的，所以剩下的不用遍历
				  break;
			  }
				  
		}
		return result;
	}

	@Override
	public String getEntStatus(String companyName) {
		
		BizMsg company = getBrefCompany(companyName,ThirdPartChannelEnum.ZHONG_SHU);
		String sourceStatus = null;
		if(company!=null&&company.getBrefCompany()!=null){
			sourceStatus = company.getBrefCompany().get("registrationstatus");
			if(sourceStatus==null
					||"".equals(sourceStatus.trim())
					||"null".equals(sourceStatus.trim())){
				sourceStatus = company.getBrefCompany().get("entstatus");
			}
		}
		String targetStatus = null;
		if(sourceStatus!=null){
			//企业状态对照表
			Properties bizStatusMap = (Properties) redisTemplate.opsForValue().get("bizStatusMap");
			if(bizStatusMap==null){
				PropertiesLoader loader = new PropertiesLoader("/resources/data/biz_entstatus_map.properties");
				bizStatusMap = loader.getProperties();
				redisTemplate.opsForValue().set("bizStatusMap", bizStatusMap, 24*60*60, TimeUnit.SECONDS);
			}
			targetStatus = bizStatusMap.getProperty(sourceStatus);
		}
		
		return targetStatus;
	}
}

