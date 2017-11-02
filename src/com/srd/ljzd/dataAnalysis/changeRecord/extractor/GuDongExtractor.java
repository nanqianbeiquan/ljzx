package com.srd.ljzd.dataAnalysis.changeRecord.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.srd.ljzd.dataAnalysis.changeRecord.extractor.base.ExtractorBase;
/**
 * 股东提取器
 * @author weitao.liu
 *
 */
public class GuDongExtractor  extends ExtractorBase{

	private static List<String> searchWords = new ArrayList<String>(Arrays.asList("股东：","姓名：","股东（发起人）名称：","企业名称：","法人名称：","投资者：",
			                                                                   "股东:","姓名:","股东（发起人）名称:","企业名称:","法人名称:","投资者:","姓名/名称："));

	private static GuDongExtractor instance;
	private GuDongExtractor(){}
	
	public static synchronized GuDongExtractor getInstance(){
		if (instance == null) {  
			  instance = new GuDongExtractor();  
		}  
		return instance; 
	}
	//如果是英文名字，可能包含","或者 " "(空格)
	public static List<Character> exceptionalSeparatorExt =  new ArrayList<Character>(Arrays.asList(' ','.'));
	
	public static List<Character> exceptionalSeparator =  new ArrayList<Character>(Arrays.asList('(',')','（','）','.'));
	@Override
	public List<Map<String, Object>> extract(String text) {

		if(!isValid(text)){
			return null;
		}
		//根据关键词查找，向右查找
		List<Map<String,Object>> matchedWords = this.matchedWords(text,searchWords);
		if(matchedWords!=null&&matchedWords.size()>0){
			//如果是英文名字，可能包含","或者 " "(空格)
			return super.findFromLeftToRight(matchedWords, text, null,exceptionalSeparatorExt,null);
		}
		
		//是否包含“自然人股东”、“法人股东”，包含则向左搜索
		
		List<String> words = new ArrayList<String>(Arrays.asList("自然人股东","法人股东"));
		
		List<String> exceptional = new ArrayList<String>();
		exceptional.addAll(words);
		exceptional.add("股东名录");
		
		List<Map<String, Object>> target = findFromRightToLeft(false,words,text,null,exceptional);
		if(target!=null&&target.size()>0){
			return target;
		}
		
		//如果使用前面的规则没有查找到结果，则按此规则继续查找，"出资"，向左搜索，
		words = new ArrayList<String>(Arrays.asList("出资"));
		
		exceptional = new ArrayList<String>();
		exceptional.addAll(words);
		//exceptional.add("股东名录");
		target = findFromRightToLeft(false,words,text,null,exceptional);
		if(target!=null&&target.size()>0){
			return target;
		}
		
		//“公司”、“有限合伙人”、“（有限合伙）”，向左搜索，且包含其本身
		words.clear();
		words.addAll(Arrays.asList("公司","有限合伙人","（有限合伙）"));
		target = findFromRightToLeft(true,words,text,null,null);
		if(target!=null&&target.size()>0){
			return target;
		}
		
		//“厂”、“店”、“中心”,向左搜索，且包含其本身,关键词后面必须是个分隔符
		words.clear();
		words.addAll(Arrays.asList("中心","厂","店"));
		target = findFromRightToLeft(true,words,text,null,null);
		if(target!=null&&target.size()>0){
			return target;
		}
		
		return null;
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
				//如果搜索关键词后面紧跟的不是分隔符，则不处理;如果需要将搜索关键词拼接到目标文本的末尾，则没有这个限制
				if(m.end()<text.length()-1&&
						(!assembleSelf&&!super.isSeparatorChar(text.charAt(m.end()), separator, exceptionalSeparator))){
					continue;
				}
				beginIndex = -1;
				//从搜索到的关键词的起始位置，往左查找
				for(i=m.start()-1;i>lastEnd;i--){
					if(super.isSeparatorChar(text.charAt(i), separator,exceptionalSeparator)){
						if(beginIndex>0&&beginIndex>lastEnd){
							if(assembleSelf){
								beginIndex += word.length();
							}
							//如果第一位为'.',则舍弃第一位
							if('.'==text.charAt(i+1)){
								i = i+1;
							}
							//如果最后一位为'.',则舍弃
                            if('.'==text.charAt(beginIndex)){
                            	beginIndex = beginIndex-1;
							}
                            if(i+1>-1&&i+1<beginIndex+1){
                            	record = new HashMap<String, Object>(3);
        						record.put("start", i+1);
        						record.put("end", beginIndex);
        						record.put("word", text.substring(i+1, beginIndex+1));
        						if(exceptional==null||!exceptional.contains(record.get("word"))){
        							target.add(record);
        						}
                            }
							
    						break;
						}
					}else{
						if(beginIndex==-1){
							beginIndex = i;
						}else if(i==lastEnd+1){//查找到末尾还未发现分隔符，这个末尾在最左边
							if(assembleSelf){
								beginIndex += word.length();
							}
							//如果第一位为'.',则舍弃第一位
							if('.'==text.charAt(i+1)){
								i = i+1;
							}
							//如果最后一位为'.',则舍弃
                            if('.'==text.charAt(beginIndex)){
                            	beginIndex = beginIndex-1;
							}
                            if(i>-1&&i<beginIndex+1){
                            	record = new HashMap<String, Object>(3);
        						record.put("start", i);
        						record.put("end", beginIndex);
        						record.put("word",text.substring(i, beginIndex+1));
        						if(exceptional==null||!exceptional.contains(record.get("word"))){
        							target.add(record);
        						}
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
	
}
