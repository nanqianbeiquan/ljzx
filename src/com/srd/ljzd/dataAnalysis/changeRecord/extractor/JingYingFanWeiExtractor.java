package com.srd.ljzd.dataAnalysis.changeRecord.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.srd.ljzd.dataAnalysis.changeRecord.extractor.base.ExtractorBase;

public class JingYingFanWeiExtractor extends ExtractorBase{

	private static JingYingFanWeiExtractor instance;
	private JingYingFanWeiExtractor(){}
	
	public static synchronized JingYingFanWeiExtractor getInstance(){
		if (instance == null) {  
			  instance = new JingYingFanWeiExtractor();  
		}  
		return instance; 
	}
	
	@Override
	public List<Map<String, Object>> extract(String text) {
		
		if(!isValid(text)){
			return null;
		}
		List<Character> exp = new ArrayList(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
		return findBySimpleSplit(text,exp);
	}

}
