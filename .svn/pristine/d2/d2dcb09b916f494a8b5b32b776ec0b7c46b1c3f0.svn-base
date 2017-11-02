package com.srd.ljzd.dataAnalysis.changeRecord.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.srd.ljzd.dataAnalysis.changeRecord.extractor.base.ExtractorBase;

/**
 * 法定代表人提取器
 * @author weitao.liu
 *
 */
public class FaRenExtractor extends ExtractorBase{
	
	private static List<String> searchWords = new ArrayList<String>(Arrays.asList("姓名：","姓名:"));
	
	
	private static FaRenExtractor instance;
	private FaRenExtractor(){}
	
	public static synchronized FaRenExtractor getInstance(){
		if (instance == null) {  
			  instance = new FaRenExtractor();  
		}  
		return instance; 
	}
	
	/**
	  * 从变更记录的变更前或变更后文本中解析出关键词， 
	  * @param text，源文本
	  * @return  识别出来的关键词列表，
	  *  每个关键词为一个map,包含关键的文本，开始索引，结束索引
	  *  {"word":"xxxx","start":"0","end":"8"}
	  */
	@Override
	public List<Map<String, Object>> extract(String text) {
		
		
		if(!isValid(text)){
			return null;
		}
		
		//如果长度小于等于4
		if(text.length()<=4){
			//不包含*
			if(text.indexOf("*")<0){
				Map<String, Object> record = new HashMap<String, Object>(3);
				record.put("word",text);
				record.put("start", 0);
				record.put("end", text.length()-1);
				List<Map<String, Object>> target = new ArrayList<Map<String,Object>>();
				target.add(record);
				return target;
			}
			
		}
		
		if(text.contains("·")){//英文名字
			Map<String, Object> record = new HashMap<String, Object>(3);
			record.put("word",  text);
			record.put("start", 0);
			record.put("end", text.length()-1);
			List<Map<String, Object>> target = new ArrayList<Map<String,Object>>();
			target.add(record);
			return target;
		}
		
		List<Map<String,Object>> matchedWords = this.matchedWords(text,searchWords);
		
		//以"姓名："或"姓名:"为搜索关键词
		if(matchedWords!=null&&matchedWords.size()>0){
			return super.findFromLeftToRight(matchedWords, text, null,null,null);
		}
		
		return null;
	}

}
