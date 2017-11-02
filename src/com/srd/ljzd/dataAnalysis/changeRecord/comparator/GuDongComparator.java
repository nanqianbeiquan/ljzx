package com.srd.ljzd.dataAnalysis.changeRecord.comparator;

import com.srd.ljzd.dataAnalysis.changeRecord.comparator.base.ComparatorBase;

public class GuDongComparator extends ComparatorBase{
	private static GuDongComparator instance;
	private GuDongComparator(){}
	
	public static synchronized GuDongComparator getInstance(){
		if (instance == null) {  
			  instance = new GuDongComparator();  
		}  
		return instance; 
	}
}
