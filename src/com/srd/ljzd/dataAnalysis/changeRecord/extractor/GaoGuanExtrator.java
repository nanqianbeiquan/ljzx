package com.srd.ljzd.dataAnalysis.changeRecord.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.srd.ljzd.dataAnalysis.changeRecord.extractor.base.ExtractorBase;

public class GaoGuanExtrator extends ExtractorBase{
	
	private static List<String> searchWords = new ArrayList<String>(Arrays.asList("姓名：","姓名:"));
	
	//需要解析的职务类型
	private  List<String> selectedWords = new ArrayList<String>(Arrays.asList("董事长","执行董事长","总经理","董事长兼总经理"));
	//职务类型
	private  List<String> positionWords = new ArrayList<String>(Arrays.asList("董事长","执行董事长","总经理","董事长兼总经理",
			"执行董事长","监事","副经理","经理","监事会主席","董事长兼总经理","执行董事"));
	
	List<String> exceptional = new ArrayList<String>(Arrays.asList("男","女","男性","女性","执行","董事长兼","兼总经理"));
	
	List<Character> expSeparator = new ArrayList(Arrays.asList('·'));
	
	private static GaoGuanExtrator instance;
	private GaoGuanExtrator(){}
	
	public static synchronized GaoGuanExtrator getInstance(){
		if (instance == null) {  
			  instance = new GaoGuanExtrator();  
		}  
		return instance; 
	}
	
	
	
	@Override
	public List<Map<String, Object>> extract(String text) {
		
		
		
		return null;
	}
	 /**
     * 
     * @param text
     * @param oldCate 去重前的变更事项名称
     * @return
     */
	@Override
	public List<Map<String, Object>> extract(String text, String oldCate) {
		if(!isValid(text)){
			return null;
		}
		
		if(text.startsWith("姓名")
				||(text.indexOf("姓名：")<=5&&text.indexOf("姓名：")>-1)
				||(text.indexOf("姓名:")<=5&&text.indexOf("姓名:")>-1)){//姓名类型,以之开头，或前8位包含之
			/**
			 * 以姓名开头按姓名类型来解析
			 * 1）“姓名：”格式字段判断方法：如果前三个字符为“姓名：”，则到第二次出现“姓名：”前为一条信息，第二个“姓名：”至第三个“姓名：”为二条信息，
			 * 依次类推，若“姓名：”后再无发现“姓名：”则为最后一条信息。每条信息里面紧跟“姓名：”后前几个非符号（逗号，分号，空格）的汉字或字母则为人员名称。
			 * 从此条信息中抓取抓取“董事长”、“执行董事长”、“总经理”、“董事长兼总经理”。
			 */
			if(this.contains(text, this.selectedWords)){//包含被关注职位的关键词
				List<Map<String,Object>> matchedWords = this.matchedWords(text,searchWords);
				if(matchedWords!=null&&matchedWords.size()>0){
					//
					return this.findFromLeftToRight(matchedWords, text, null,null,exceptional);
				}
			}
		}else if(this.contains(text, this.positionWords)){//职务类型
			List<Map<String, Object>> target = new ArrayList<Map<String,Object>>();
			if(this.startWith(text, positionWords)){//职务在前，人名在后
				//从关键词位置往右查找
				List<Map<String,Object>> matchedWords = this.matchedWords(text,selectedWords);
				if(matchedWords!=null&&matchedWords.size()>0){
					//如果是英文名字，可能包含","或者 " "(空格)
					return super.findFromLeftToRight(matchedWords, text, null,null,exceptional);
				}
				
			}else{//人名在前，职务在后
				//从关键词位置往左查找
				return findFromRightToLeft(false,this.selectedWords,text,null,exceptional);
			}
		}else{
			return findBySimpleSplit(text,expSeparator);
		}
		
		return null;
	}
	//是否包含关键词
	private boolean contains(String source,List<String> words){
		
		for(String word : words){
			if(source.contains(word)){
				return true;
			}
		}
		return false;
		
	}
	//是否以关键词开头
	private boolean startWith(String source,List<String> words){
		
		for(String word : words){
			if(source.startsWith(word)){
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param assembleSelf 是否将搜索关键词拼接到目标文本的末尾
	 * @param words
	 * @param text
	 * @param separator 分隔符
	 * @param exceptional  例外关键词集合，查找到的结果如果在这个集合中，就不返回
	 * @return
	 */
	private List<Map<String,Object>> findFromRightToLeft(boolean assembleSelf,List<String> words,String text,List<Character> separator,List<String> exceptional){
		if(words==null||text==null||words.size()==0||"".equals(text.trim())){
			return null;
		}
		List<Map<String, Object>> target = new ArrayList<Map<String,Object>>();
		Pattern p = null;
		Matcher m = null;
		int beginIndex = -1;
		int lastEnd = 0;
		int i=0;
		Map<String, Object> record = null;
		for(String word : words){
			p = Pattern.compile(word);
			m = p.matcher(text);
			lastEnd = -1;
			while(m.find()){
				//如果搜索关键词前面紧跟的不是分隔符，则不处理
				if(m.end()<text.length()-1&&!super.isSeparatorChar(text.charAt(m.start()-1), separator, null)){
					continue;
				}
				beginIndex = -1;
				//从搜索到的关键词的起始位置，往左查找
				for(i=m.start()-1;i>lastEnd;i--){
					if(super.isSeparatorChar(text.charAt(i), separator,null)){
						if(beginIndex>0&&beginIndex>lastEnd){
							if(assembleSelf){
								beginIndex += word.length();
							}
							record = new HashMap<String, Object>(3);
    						record.put("start", i+1);
    						record.put("end", beginIndex);
    						record.put("word", text.substring(i+1, beginIndex+1));
    						if(exceptional==null||!exceptional.contains(record.get("word"))){
    							target.add(record);
    							break;
    						}
    						//没有找到继续往左查找
    						beginIndex = -1;
						}
					}else{
						if(beginIndex==-1){
							beginIndex = i;
						}else if(i==lastEnd+1){//查找到末尾还未发现分隔符，这个末尾在最左边
							if(assembleSelf){
								beginIndex += word.length();
							}
							record = new HashMap<String, Object>(3);
    						record.put("start", i);
    						record.put("end", beginIndex);
    						record.put("word",text.substring(i, beginIndex+1));
    						if(exceptional==null||!exceptional.contains(record.get("word"))){
    							target.add(record);
    						}
    						break;
						}
						
					}
				}
				lastEnd = m.end();
			}
		}
		return target;
	}
	
	//从搜索关键词 ，向后查找
	@Override
    public List<Map<String, Object>> findFromLeftToRight(List<Map<String,Object>> matchedWords,String text,List<Character> separator,List<Character> exceptionalSeparator
    		,List<String> exceptional){
    	List<Map<String, Object>> target = new ArrayList<Map<String,Object>>();
		
    	if(matchedWords==null||matchedWords.size()==0){
    		return null;
    	}
    	int offset = 0;
    	String prefix = null;//搜索关键词，可认为是目标字符串的前缀
    	String[] sourceRecordArray = null;//使用搜索关键词截取源文本之后得到的数组
    	String sourceRecord = null;//截取后的每一项字符串
    	for(Map<String,Object> prefixMap : matchedWords){
    		
    		prefix = (String)prefixMap.get("prefix");
    		offset = (int)prefixMap.get("offset")+prefix.length();
    		
    		sourceRecordArray = text.split(prefix);
    		
    		Map<String, Object> record = null;
    		for(int i =1;i<sourceRecordArray.length;i++){
    			sourceRecord = sourceRecordArray[i];
    			
    			if(sourceRecord==null||"".equals(sourceRecord.trim())
    					|| !this.contains(sourceRecord.trim(), selectedWords)){
    				continue;
    			}
    			
    			int beginIndex = -1;
    			//截取
    			for(int j=0;j<sourceRecord.length();j++){
    				if(isSeparatorChar(sourceRecord.charAt(j),separator,exceptionalSeparator)){
    					
    					if(beginIndex>=0){//从非分隔符开始 计数 起始索引
    						record = new HashMap<String, Object>(3);
    						record.put("start", offset+beginIndex);
    						record.put("end", offset+j-1);
    						record.put("word",sourceRecord.substring(beginIndex, j));
    						target.add(record);
    						break;
    					}
    					
    				}else{
    					if(beginIndex==-1){
    						beginIndex = j;
    					}else if(j==sourceRecord.length()-1){//之前有非分隔符索引，且已经遍历到该字符串末尾
    						record = new HashMap<String, Object>(3);
    						record.put("start", offset+beginIndex);
    						record.put("end", offset+j-1);
    						record.put("word",sourceRecord.substring(beginIndex, j));
    						target.add(record);
    						break;
    					}
    					
    				}
    			}
    			offset += sourceRecord.length()+prefix.length();
    		}
    	}
		
		if(target.size()>0){
			return target;
		}
		return null;
    }
	
}
