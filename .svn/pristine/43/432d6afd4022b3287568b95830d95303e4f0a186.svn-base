package com.srd.ljzd.serviceImpl.news;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.entity.news.NewsOpinion;
import com.srd.ljzd.service.news.NewsService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringTool;
import com.srd.ljzd.util.StringUtils4Dev;



/**   
 * 版权所有：2017
 * 项目名称：ljzx   
 *
 * 类描述：
 * 类名称：com.srd.ljzd.serviceImpl.news.NewsServiceImpl     
 * 创建人：裴子辉
 * 创建时间：2017年1月3日 上午9:54:22   
 * 修改人：
 * 修改时间：2017年1月3日 上午9:54:22   
 * 修改备注：   
 * @version   V1.3    
 */
  
@Service("newService")
public class NewsServiceImpl implements NewsService {
	protected static Logger logger = LogManager.getLogger(NewsServiceImpl.class.getName());

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
//	@Autowired
//	private CompanyAdaptorService companyAdaptorService;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	public NewsOpinion resolveNewsOpinionList(JSONObject jsonObject) {
		NewsOpinion newsSentime = new NewsOpinion();

		if (jsonObject.containsKey("title")) {
			String title = jsonObject.getString("title");
			newsSentime.setTitle(StringTool.formartForPdf(title));
		}
		if (jsonObject.containsKey("newsid")) {
			newsSentime.setNewsId(jsonObject.getString("newsid"));
		}
		if (jsonObject.containsKey("newsId")) {
			newsSentime.setNewsId(jsonObject.getString("newsId"));
		}
		if (jsonObject.containsKey("pubtime")) {
			newsSentime.setPubtime(jsonObject.getString("pubtime").substring(0,12));
		}
		if (jsonObject.containsKey("source")) {
			newsSentime.setSource(jsonObject.getString("source"));
		}
		if (jsonObject.containsKey("key")) {
			newsSentime.setOpinionKey(jsonObject.getString("key"));
		}
		return newsSentime;
	}
	
	 public static String[] splitt(String stocks){
	        String strr = stocks;
	        String[] stocksData = strr.split("\t");
	        return stocksData;
	   }
	public Map<String,String[]> readTxt(){
		String[] stocksData = null;
		Map<String,String[]> keyDataSources = null;
		try {
            String encoding="UTF-8";
            String filePath="classpath:resources"+File.separator+"data"+File.separator+"stocks.txt";
            File file=ResourceUtils.getFile(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                keyDataSources=new HashMap<String,String[]>();
                while((lineTxt = bufferedReader.readLine()) != null){
                	stocksData=splitt(lineTxt);
                	if(null!=stocksData[stocksData.length-1]&&!"".equals(stocksData[stocksData.length-1])){
                		keyDataSources.put(stocksData[stocksData.length-1],stocksData);
                	}
                	else if(null!=stocksData[stocksData.length-2]&&!"".equals(stocksData[stocksData.length-2])){
                		keyDataSources.put(stocksData[stocksData.length-2],stocksData);
                	}
                }
                redisTemplate.opsForValue().set("keyDataSources", keyDataSources);
                
                read.close();
	        }else{
	        	LoggerUtils.error("找不到指定的文件");
	        }
		} catch (Exception e) {
			LoggerUtils.error("读取文件内容出错", e);
		}
		
		return keyDataSources;
	}
	
	public String  resolveCompanyName(String companyName){
		StringBuffer cName=new StringBuffer(companyName);
		
		Pattern p = Pattern.compile("[(（）)]");
		
		Matcher m = p.matcher(companyName);
		StringBuffer target=null;
		int offset=0;
		while(m.find()){
				
			target = new StringBuffer("[").append(cName.substring(m.start()+offset,m.end()+offset)).append("]");
			cName = cName.replace(m.start()+offset,m.end()+offset,target.toString());
			offset+=2;
		}
		return cName.toString();
	}
	
	public NewsOpinion resolveNewsDetailsList(JSONObject jsonObject,String companyName) {
		
		companyName = resolveCompanyName(companyName);
		
		NewsOpinion newsSentime = new NewsOpinion();
		
		@SuppressWarnings("unchecked")
		Map<String,String[]> keyDataSources=(Map<String,String[]>)redisTemplate.opsForValue().get("keyDataSources");
		
		if(null==keyDataSources){
			keyDataSources = readTxt();
		}
		
		String SENSITIVEWORDS = Global.getConfig("constant_sensitive_words");
		String COMPETITORNAME = Global.getConfig("constant_competitor_name");
		
		StringBuffer keyWordPattern = new StringBuffer("");
		
		Pattern pattern = null;
		Matcher matcher = null;
		
		if (jsonObject.containsKey("newsInfo:content")) {
			
			String tempContent = "";
			
			String content = jsonObject.getString("newsInfo:content");
			
			StringBuffer newsContent = new StringBuffer();
			
			newsContent.append(content);
			
			//在新闻舆情详情页面关键字高亮显示，供测试用
			if(!"".equals(newsContent) && !"--".equals(newsContent)){
				
				//隐藏舆情中竟对的名称
				keyWordPattern = new StringBuffer("(" + COMPETITORNAME + ")");
				pattern = Pattern.compile(keyWordPattern.toString());
				matcher = pattern.matcher(newsContent);
				tempContent = matcher.replaceAll("XXXXX");
				newsContent = new StringBuffer(tempContent);
				
				//高亮显示关键字
				keyWordPattern = new StringBuffer("(" + SENSITIVEWORDS + ")");
				pattern = Pattern.compile(keyWordPattern.toString());
				matcher = pattern.matcher(newsContent);
				tempContent = matcher.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
				newsContent = new StringBuffer(tempContent);
				
				//高亮企业名称
				keyWordPattern = new StringBuffer("("+companyName+")");
				pattern = Pattern.compile(keyWordPattern.toString());
				matcher = pattern.matcher(newsContent);
				tempContent = matcher.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
				newsContent = new StringBuffer(tempContent);
				
				//高亮显示股票代码和股票简称
				if(null != keyDataSources.get(companyName)){
					keyWordPattern = new StringBuffer("(");
					
					for (int j = 0; j < keyDataSources.get(companyName).length; j++) {
						if (!companyName.equals(keyDataSources.get(companyName)[j]) && null != keyDataSources.get(companyName)[j] && !"".equals(keyDataSources.get(companyName)[j].trim())) {
							keyWordPattern.append(keyDataSources.get(companyName)[j] + "|");
						}
					}
					
					//去掉最后的竖线
					keyWordPattern = new StringBuffer(keyWordPattern.toString().substring(0, keyWordPattern.toString().length()-1));
					
					keyWordPattern.append(")");
					
					pattern = Pattern.compile(keyWordPattern.toString());
					matcher = pattern.matcher(newsContent);
					tempContent = matcher.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
					newsContent = new StringBuffer(tempContent);
				}
			}
			
			newsSentime.setContent(newsContent.toString());
		}
		if (jsonObject.containsKey("newsInfo:title")) {
			String title = jsonObject.getString("newsInfo:title");
			
			//隐藏标题中竟对的名称
			keyWordPattern = new StringBuffer("(" + COMPETITORNAME + ")");
			pattern = Pattern.compile(keyWordPattern.toString());
			matcher = pattern.matcher(title);
			title = matcher.replaceAll("XXXXX");
			title = StringTool.formartForPdfName(title);
			newsSentime.setTitle(title);
		}
		if (jsonObject.containsKey("newsInfo:pubtime")) {
			newsSentime.setPubtime(jsonObject.getString("newsInfo:pubtime").substring(0,12));
		}

		return newsSentime;
	}
	
	@Override
	public NewsOpinion queryNewsDetails(String newsKey,String newsid, String companyName,String opinionType) {
		Calendar cal = Calendar.getInstance();
		Date stopTime=null;
		stopTime = cal.getTime();
		
		cal.add(Calendar.MONTH, -6);
		Date startTime = null;
		startTime = cal.getTime();
		
		String url = Global.getConfig("dataService")+ Global.getConfig("newsOpinionDetail");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);
		params.put("newsid", newsid);
		params.put("cols","content,pubtime,title");
		params.put("key", newsKey);
		params.put("type", opinionType);
		params.put("startTime", sdf.format(startTime));
		params.put("stopTime", sdf.format(stopTime));
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		NewsOpinion newsDetails = new NewsOpinion();
		
		String  redisKey= companyName+"_newsDetails_"+newsDetails.getTitle();
		newsDetails =(NewsOpinion)redisTemplate.opsForValue().get(redisKey);
		if(newsDetails!=null&&!StringTool.isNotNullAndBlack(newsDetails.getTitle())){
			return newsDetails;
		}else{
			if (jsonResult != null) {
				if (jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")) {
					if (jsonResult.get("data") != null && !jsonResult.get("data").equals("")) {
						newsDetails = resolveNewsDetailsList((JSONObject)jsonResult.getJSONObject("data"),companyName);
					}
				} else {
					logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
				}
			}
				
			if(null!=newsDetails){
			 	redisTemplate.opsForValue().set(companyName+"_newsDetails_"+newsDetails.getTitle(), newsDetails, 2*60*60, TimeUnit.SECONDS);//2小时
			}
		}
	
		return newsDetails;
	
	}

	public Map<String, List<NewsOpinion>> queryAllOpinionList(String companyName) {
		readTxt();
		Map<String, List<NewsOpinion>> newsMap = new HashMap<String, List<NewsOpinion>>();
		
		
		List<NewsOpinion>  newsOpinionList = new ArrayList<NewsOpinion>();
		List<NewsOpinion>  negativeOpinionList = new ArrayList<NewsOpinion>();
		
		
		
		Calendar cal = Calendar.getInstance();
		Date stopTime=null;
		
		stopTime = cal.getTime();
		
		cal.add(Calendar.MONTH, -12);
		cal.add(Calendar.DATE, 1); //完整一年的概念； 20160206/20170207
		Date startTime = null;
		
		startTime = cal.getTime();
		String url = Global.getConfig("dataService") + Global.getConfig("newsOpinion");
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyName", companyName);
		params.put("cols","newsid,pubtime,title,source");
		params.put("startTime", sdf.format(startTime));
		params.put("stopTime", sdf.format(stopTime));
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);

		if (jsonResult != null) {
			if (jsonResult.getString("respCode").equals("200") && jsonResult.getString("respMsg").equals("success")) {
				if (jsonResult.get("data") != null && !jsonResult.get("data").equals("")) {
					JSONObject jsonObject = jsonResult.getJSONObject("data");
					
					if (jsonObject.containsKey("norisk")) {
						JSONArray jsonArray = jsonObject.getJSONArray("norisk");
						for (int i = 0; i < jsonArray.size(); i++) {
							NewsOpinion newsOpinion = resolveNewsOpinionList((JSONObject) jsonArray.get(i));
							newsOpinionList.add(newsOpinion);
							//去掉标题相同的新闻舆情
							for (int j=0;j<(newsOpinionList.size()-1);j++) {
								if(newsOpinionList.get(j).getTitle().equals(newsOpinion.getTitle())){
									newsOpinionList.remove(newsOpinion);
								}
							}
						}
					}
					
					
					if (jsonObject.containsKey("risk")) {
						JSONArray jsonArray = jsonObject.getJSONArray("risk");
						for (int i = 0; i < jsonArray.size(); i++) {
							NewsOpinion newsOpinion = resolveNewsOpinionList((JSONObject) jsonArray.get(i));
							negativeOpinionList.add(newsOpinion);
							//去掉标题相同的新闻舆情
							for (int j=0;j<(negativeOpinionList.size()-1);j++) {
								if(negativeOpinionList.get(j).getTitle().equals(newsOpinion.getTitle())){
									negativeOpinionList.remove(newsOpinion);
								}
							}
						}
					}
				}
			} else {
				logger.error("respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		NewsOpinionComparator newsOpinionComparator = new NewsOpinionComparator();
		try {
			Collections.sort(newsOpinionList, newsOpinionComparator);
			
		} catch (Exception e) {
			logger.error("newsOpinionList 分组排序。。。"+e, e);
		}
		
		try {
			Collections.sort(negativeOpinionList, newsOpinionComparator);
		} catch (Exception e) {
			logger.error("negativeOpinionList 分组排序。。。"+e, e);
			
		}

		newsMap.put("newsOpinionList", newsOpinionList);
		newsMap.put("negativeOpinionList", negativeOpinionList);
		return newsMap;
	}
	
	@Override
	public int getNewsNum(String companyName) {
		int result = 0;
		
		Map<String, List<NewsOpinion>> newsMap = queryAllOpinionList(companyName);
		
		List<NewsOpinion> newsOpinionList = newsMap.get("newsOpinionList");
		List<NewsOpinion> negativeOpinionList = newsMap.get("negativeOpinionList");
		
		result = newsOpinionList.size() + negativeOpinionList.size();
		return result;
	}

	@Override
	public Page<NewsOpinion> queryNewsOpinionList(String companyName,String type, Integer currentPageNo, Integer pageSize) {
		
		 Page<NewsOpinion> page = new Page<NewsOpinion>();
		 List<NewsOpinion> list = new ArrayList<NewsOpinion>();
		 String lableKey = "0".equals(type)?"_mediaPage_":"_negativePage_";
		 String redisKey = companyName+lableKey;
			// 分页使用缓存；
			if(currentPageNo>1){
			// 非首次查询，去缓存中查找，找不到，到
				List<NewsOpinion> redisNewsOpinion =(List<NewsOpinion>)redisTemplate.opsForValue().get(redisKey);
				if(redisNewsOpinion!=null&&redisNewsOpinion.size()>0){
					list = redisNewsOpinion;
				}else{
					list = getAllOpinionListByInterfaceAndCached(companyName,type, redisKey);
				}
			}else{
				 	list = getAllOpinionListByInterfaceAndCached(companyName, type,redisKey);
			}
		

		 // 查询条件；
			if(list!=null&&!list.isEmpty()){
				int totalSize = list.size();
				page.setPageSize(pageSize);
				page.setCurrentPageNo(currentPageNo);
				int size = list.size();
				int pageSum = (size%pageSize==0)?(size/pageSize):(size/pageSize+1);
				page.setPageSum(pageSum);
				page.setRecordSum(Long.valueOf(size));
				
				int fromIndex = (currentPageNo-1)*pageSize;
				int toIndex = 0;
				
				if(currentPageNo==pageSum){
					toIndex = list.size();
					list = list.subList(fromIndex, toIndex);
				}else{
					toIndex = fromIndex+6;
					list = list.subList(fromIndex, toIndex);
				}
				page.setResults(list);
			}
			
		 return page;
	}

	/**
	* @Title: getAllOpinionListByInterfaceAndCached
	* @Description: TODO()
	* @param  @param companyName
	* @param  @param type
	* @param  @param redisKey
	* @param  @return  
	* @return List<NewsOpinion>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月10日 上午11:29:35
	*/
	private List<NewsOpinion> getAllOpinionListByInterfaceAndCached(String companyName, String type, String redisKey) {
		List<NewsOpinion> list = new ArrayList<NewsOpinion>();
		Map<String, List<NewsOpinion>> newsMap = queryAllOpinionList(companyName);
		if(newsMap!=null){
			 if("0".equals(type)){
				 list = newsMap.get("newsOpinionList");
			 }else{
				 list = newsMap.get("negativeOpinionList");
			 }
			 
			 if(null!=list&&list.size()>0){
				 	redisTemplate.opsForValue().set(redisKey, list, 60*15, TimeUnit.SECONDS);//15 分钟；
			 }
		}
		return list;
	}

	private List<NewsOpinion> buildTestList(String type) {
		List<NewsOpinion> opinionListResult = new ArrayList<NewsOpinion>();
		Date d = new Date();
		for(int i=0;i<100;i++){
			NewsOpinion opinion = new NewsOpinion();
			opinion.setOpinionType(type);
			opinion.setContent(i+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容新闻内容@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+i);
			opinion.setNewsId(type+"_"+i);
			d = DateUtils.getStartDateOfNextMonth(d);
			opinion.setPubtime(DateUtils.dateToString(d));
			opinion.setSource("瞎编乱造自媒体============================="+i);
			opinion.setTitle("<<好好学习，天天向上>>"+i);
			
			opinionListResult.add(opinion);
		}
		return opinionListResult;
	}
	
	
class NewsOpinionComparator implements Comparator<NewsOpinion>{
		
		@Override
		public int compare(NewsOpinion newsOpinion1, NewsOpinion newsOpinion2){
			int result = 0;
			
			Date date1 = DateUtils.getLocalDate(newsOpinion1.getPubtime());
			Date date2 = DateUtils.getLocalDate(newsOpinion2.getPubtime());
			
			if(date1==null&&date2!=null){
				result = 1;
			}
			if(date2==null&&date1!=null){
				result = -1;
			}
			if(date1!=null&&date2!=null){
				if(date1.before(date2)){//返回true，说明date1 比date2早，
					result = 1;
				}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
					if(DateUtils.getLocalStr("yyyy年MM月dd日", date1).equals(DateUtils.getLocalStr("yyyy年MM月dd日", date2))){
						result = 0;
					}else{
						result = -1;
					}
				}
			}
			
			return result;
		}
	}
}
