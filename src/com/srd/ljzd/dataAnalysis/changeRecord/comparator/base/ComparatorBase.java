package com.srd.ljzd.dataAnalysis.changeRecord.comparator.base;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ComparatorBase {

	//求差集
	public Set<String> setDiff(List<Map<String, Object>> a,List<Map<String, Object>> b){
		
		if(a==null){
			return null;
		}
		Set<String> aSet = getWordSet(a);
		if(b==null){
			return aSet;
		}
		Set<String> bSet = getWordSet(b);
		
		aSet.removeAll(bSet);
        
		
		return aSet;
		
		/*if(aSet.size()==0){
        	return null;
        }
        if(aSet.size()==a.size()){
        	return aSet;
        }
        
        for(int i=0;i<a.size();i++){
        	if(!aSet.contains((String)a.get(i).get("word"))){
        		a.remove(i);
        		i--;
        	}
        }
        
        return a;*/
		
	}
	
	private Set<String> getWordSet(List<Map<String, Object>> source){
		Set<String> target = new HashSet<String>();
		
		for(Map<String,Object> word:source){
			target.add((String)word.get("word"));
		}
        return target;		
	}
}
