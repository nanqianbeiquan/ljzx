package com.srd.ljzd.dataAnalysis.changeRecord.extractor.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.srd.ljzd.util.StringTool;

public abstract class ExtractorBase {
	
	 //分隔符集合
	 public static List<Character> defaultSeparator =  new ArrayList<Character>(Arrays.asList('。','，',' ',' ',';','；','`',','));
	
	
	 /**
	  * 从变更记录的变更前和变更后文本中解析出关键词， 
	  * @param text，源文本
	  * @return  识别出来的关键词列表，
	  *  每个关键词为一个map,包含关键的文本，开始索引，结束索引
	  *  {"word":"xxxx","start":"0","end":"8"}
	  */
     public abstract List<Map<String,Object>> extract(String text);
     /**
      * 
      * @param text
      * @param oldCate 去重前的变更事项名称
      * @return
      */
     public List<Map<String,Object>> extract(String text,String oldCate){return null;}
     
     //是否是有效文本，是返回true，不是返回false
     public boolean isValid(String text){
    	 
    	//不解析
 		if(text==null){
 			return false;
 		}
 		text = text.trim();
 		//“空格”、“空白”、“无”、单纯数字,不解析
 		if(StringUtils.isEmpty(text)||"无".equals(text)||"空白".equals(text)||"空格".equals(text)||StringTool.isNumeric(text)){
 			return false;
 		}
    	 
    	return true;
     }
     
    
 	//判断是否是分隔字符
 	public boolean isSeparatorChar(char c,List<Character> separator,List<Character> exceptional){
 		
 		//例外情况，
        if(exceptional!=null&&exceptional.size()>0){
        	if(exceptional.contains(c)){
        		return false;
        	}
        }
 		if(separator==null||separator.size()==0){
 			separator = defaultSeparator;
 		}
 		
 		if(separator.contains(c)){//是默认的分隔符
 			return true;
 		}
 		
 		if(isChineseChar(c+"")||isEnglishChar(c+"")){
 			return false;
 		}
 		if(c=='·'){//英文翻译过来的中文名字中有·  不是分隔符
 			return false;
 		}
 		return true;
 	}
 	
 	public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
        Matcher m=p.matcher(str); 
        if(m.find()){ 
            temp =  true;
        }
        return temp;
    }
    public static boolean isEnglishChar(String str){
 	       boolean temp = false;
 	       Pattern p=Pattern.compile("[a-zA-Z]"); 
 	       Matcher m=p.matcher(str); 
 	       if(m.find()){ 
 	           temp =  true;
 	       }
 	       return temp;
 	}
    //从搜索关键词 ，向后查找
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
    			
    			if(sourceRecord==null||"".equals(sourceRecord.trim())){
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
    						if(exceptional==null||!exceptional.contains(record.get("word"))){
    							target.add(record);
    							break;
    						}
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
    						if(exceptional==null||!exceptional.contains(record.get("word"))){
    							target.add(record);
    							break;
    						}
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
    public List<Map<String,Object>> matchedWords(String text,List<String> searchWords){
		List<Map<String,Object>> matched = null;
		Map<String,Object> prefixMap = null;
		int index = -1;
		for(String word:searchWords){
			index = text.indexOf(word);
			if(index>-1){
				prefixMap = new HashMap<String, Object>();
				prefixMap.put("offset", index);
				prefixMap.put("prefix", word);
				if(matched==null){
					matched = new ArrayList<Map<String,Object>>();
				}
				matched.add(prefixMap);
			}
		}
		
		return matched;
	}
    
    public  List<Map<String, Object>> findBySimpleSplit(String text,List<Character> expSeparater){
    	List<Map<String, Object>> target = new ArrayList<Map<String,Object>>();
    	
		Map<String, Object> record = null;
		
		int length = text.length();
		int startIndex = -1;
		for(int i=0;i<length;i++){
			if(this.isSeparatorChar(text.charAt(i), null, expSeparater)){
				if(startIndex>=0){
					record = new HashMap<String, Object>(3);
					record.put("start", startIndex);
					record.put("end", i-1);//当前位是分隔符，所以要前移一位才是当前关键词的结束位
					record.put("word", text.substring(startIndex, i));
					target.add(record);
					//将开始位置为-1，继续下一个关键词的截取
					startIndex = -1;
				}
			}else{
				if(startIndex<0){
					startIndex = i;
				}else{
					//遍历到最后一位，
					if(i==length-1){
						record = new HashMap<String, Object>(3);
						record.put("start", startIndex);
						record.put("end", i);
						record.put("word", text.substring(startIndex, i+1));
						target.add(record);
					}
				}
			}
		}
    	if(target.size()>0){
			return target;
		}
		return null;
    }
}
