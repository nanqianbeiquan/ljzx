package com.srd.ljzd.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class DateStrComparator implements Comparator<String>,Serializable{

	private static final long serialVersionUID = -5032780977137295417L;

	//按时间倒序排序
	public int compare(String key1, String key2) {
        //如果有空值，直接返回0
        if (key1 == null || key2 == null)
            return 0; 
        Date date1 = DateUtils.getLocalDate(key1);
		Date date2 = DateUtils.getLocalDate(key2);
		if(date1==null||date2==null){
			return 0;
		}
        if(date1.before(date2)){//返回true，说明date1 比date2早，
			return 1;
		}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
			if(key1.equals(key2)){
				return 0;
			}else{
				return -1;
			}
		}
    }

}
