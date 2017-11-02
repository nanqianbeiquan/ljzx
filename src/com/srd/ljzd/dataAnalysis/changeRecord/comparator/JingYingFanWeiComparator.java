package com.srd.ljzd.dataAnalysis.changeRecord.comparator;

import com.srd.ljzd.dataAnalysis.changeRecord.comparator.base.ComparatorBase;

public class JingYingFanWeiComparator extends ComparatorBase{
	private static JingYingFanWeiComparator instance;
	private JingYingFanWeiComparator(){}
	
	public static synchronized JingYingFanWeiComparator getInstance(){
		if (instance == null) {  
			  instance = new JingYingFanWeiComparator();  
		}  
		return instance; 
	}
}
