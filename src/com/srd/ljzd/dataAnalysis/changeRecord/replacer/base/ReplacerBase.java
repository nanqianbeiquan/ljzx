package com.srd.ljzd.dataAnalysis.changeRecord.replacer.base;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public abstract class ReplacerBase {

	public String replace(String source,Set<String> words){
		if(StringUtils.isEmpty(source)||words==null||words.size()==0){
			return source;
		}
		StringBuffer targetBuf = new StringBuffer(source);
		
		StringBuffer wordPattern = null;
		Pattern p=null; 
	    Matcher m=null; 
	    int index = -1;
	    
	    int offset = 0;
	    String preStr = "<span class=\"highWord\">";
	    String sufStr = "</span>";
	    String targetStr = null;
	    int lastStartIndex = -1;
		for(String word : words){
			
			wordPattern = new StringBuffer(word);
			targetStr = new StringBuffer(word).insert(0, preStr).append(sufStr).toString();
			//转义
			index = wordPattern.indexOf("(");
			if(index>-1){
				wordPattern.replace(index, index+1, "[(]");
			}
			index = wordPattern.indexOf(")");
			if(index>-1){
				wordPattern.replace(index, index+1, "[)]");
			}
			try{
				p = Pattern.compile(wordPattern.toString());
			}catch(Exception e){
				//如果关键词不符合正则表达式规则
				continue;
			}
			
			m = p.matcher(targetBuf);
			lastStartIndex = -1;
			while(m.find()){
				//确保搜索到的不是上一次的搜索位置
				if(lastStartIndex!=-1&&m.start()-lastStartIndex==preStr.length()){
					continue;
				}
				targetBuf.replace(m.start()+offset, m.end()+offset,targetStr);
				lastStartIndex = m.start();
			}
		
		}
		
		return targetBuf.toString();
	}
}
