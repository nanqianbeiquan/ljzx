package com.srd.ljzd.dataAnalysis.changeRecord.replacer;

import com.srd.ljzd.dataAnalysis.changeRecord.replacer.base.ReplacerBase;

public class Replacer extends ReplacerBase{
	private static Replacer instance;
	private Replacer(){}
	
	public static synchronized Replacer getInstance(){
		if (instance == null) {  
			  instance = new Replacer();  
		}  
		return instance; 
	}
}
