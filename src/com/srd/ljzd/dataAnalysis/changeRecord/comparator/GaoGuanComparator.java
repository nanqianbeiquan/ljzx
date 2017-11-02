package com.srd.ljzd.dataAnalysis.changeRecord.comparator;

import com.srd.ljzd.dataAnalysis.changeRecord.comparator.base.ComparatorBase;

public class GaoGuanComparator extends ComparatorBase{
	private static GaoGuanComparator instance;
	private GaoGuanComparator(){}
	
	public static synchronized GaoGuanComparator getInstance(){
		if (instance == null) {  
			  instance = new GaoGuanComparator();  
		}  
		return instance; 
	}
}
