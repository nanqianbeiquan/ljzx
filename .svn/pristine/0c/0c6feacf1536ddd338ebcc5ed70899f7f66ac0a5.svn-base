package com.srd.ljzd.entity.biz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.srd.ljzd.util.DateStrComparator;
import com.srd.ljzd.util.DateUtils;

public class BizChangeRecordHolder  implements Serializable{
	private static final long serialVersionUID = -6280565885777409537L;
    //变更事项及其数量的列表
	private Map<String,Integer> category;
	//变更时间及相应变更的数量列表，按时间倒序排列
	private Map<String,Integer> sortedDate;
	
    /**
     * //时间轴数据
     * 数据格式：
     * {
     *   dateKey:{
     *      categoryNameKey:[{item},{item},{item}...],
     *      categoryNameKey:[{item},{item},{item}...]
     *      ,...
     *   },
     *   dateKey:{
     *      categoryNameKey:[{item},{item},{item}...],
     *      categoryNameKey:[{item},{item},{item}...]
     *      ,...
     *   }
     *   ,...
     * }
     */
	private Map<String,TreeMap<String,List<HashMap<String,String>>>> timeAxis;
	
	public BizChangeRecordHolder() {
		this.category = new HashMap<String, Integer>();
		
		this.sortedDate = new TreeMap<String, Integer>(new DateStrComparator());
		
		this.timeAxis = new HashMap<String, TreeMap<String,List<HashMap<String,String>>>>();
		
	}

	public BizChangeRecordHolder(Map<String, Integer> category,
			Map<String, Integer> sortedDate,
			Map<String, TreeMap<String, List<HashMap<String, String>>>> timeAxis) {
		super();
		this.category = category;
		this.sortedDate = sortedDate;
		this.timeAxis = timeAxis;
	}

	public Map<String, Integer> getCategory() {
		return category;
	}

	public void setCategory(Map<String, Integer> category) {
		this.category = category;
	}


	public Map<String, Integer> getSortedDate() {
		return sortedDate;
	}

	public void setSortedDate(Map<String, Integer> sortedDate) {
		this.sortedDate = sortedDate;
	}

	public Map<String, TreeMap<String, List<HashMap<String, String>>>> getTimeAxis() {
		return timeAxis;
	}

	public void setTimeAxis(
			Map<String, TreeMap<String, List<HashMap<String, String>>>> timeAxis) {
		this.timeAxis = timeAxis;
	}
	
	
	
}
