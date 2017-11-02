package com.srd.ljzd.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class BizComparator implements Comparator<HashMap<String,String>>{

	/**
	 * 
	 */
	@Override
	public int compare(HashMap<String,String> o1, HashMap<String,String> o2) {
		Date date1 = null;
		Date date2 = null;
		//变更记录
		if(o1.containsKey(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr())
				&&o1.get(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr())
				&&o2.get(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.BIAN_GENG_JI_LU.getSortAttr()));
		}
		//股东信息
		else if(o1.containsKey(ModuleEnum.GU_DONG.getSortAttr())
				&&o1.get(ModuleEnum.GU_DONG.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.GU_DONG.getSortAttr())
				&&o2.get(ModuleEnum.GU_DONG.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.GU_DONG.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.GU_DONG.getSortAttr()));
		}
		//动产抵押
		else if(o1.containsKey(ModuleEnum.DONG_CHAN_DI_YA.getSortAttr())
				&&o1.get(ModuleEnum.DONG_CHAN_DI_YA.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.DONG_CHAN_DI_YA.getSortAttr())
				&&o2.get(ModuleEnum.DONG_CHAN_DI_YA.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.DONG_CHAN_DI_YA.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.DONG_CHAN_DI_YA.getSortAttr()));
		}
		//经营异常
		else if(o1.containsKey(ModuleEnum.JING_YING_YI_CHANG.getSortAttr())
				&&o1.get(ModuleEnum.JING_YING_YI_CHANG.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.JING_YING_YI_CHANG.getSortAttr())
				&&o2.get(ModuleEnum.JING_YING_YI_CHANG.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.JING_YING_YI_CHANG.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.JING_YING_YI_CHANG.getSortAttr()));
		}//行政处罚
		else if(o1.containsKey(ModuleEnum.ADMINISTRATIVE_PENALTY.getSortAttr())
				&&o1.get(ModuleEnum.ADMINISTRATIVE_PENALTY.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.ADMINISTRATIVE_PENALTY.getSortAttr())
				&&o2.get(ModuleEnum.ADMINISTRATIVE_PENALTY.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.ADMINISTRATIVE_PENALTY.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.ADMINISTRATIVE_PENALTY.getSortAttr()));
		}//商标
		else if(o1.containsKey(ModuleEnum.BRAND.getSortAttr())
				&&o1.get(ModuleEnum.BRAND.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.BRAND.getSortAttr())
				&&o2.get(ModuleEnum.BRAND.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.BRAND.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.BRAND.getSortAttr()));
		}
		
		//个人失信信息
		else if(o1.containsKey(ModuleEnum.PERSONAL_DISHONEST.getSortAttr())
				&&o1.get(ModuleEnum.PERSONAL_DISHONEST.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.PERSONAL_DISHONEST.getSortAttr())
				&&o2.get(ModuleEnum.PERSONAL_DISHONEST.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.PERSONAL_DISHONEST.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.PERSONAL_DISHONEST.getSortAttr()));
		}
		//个人被执行人信息
		else if(o1.containsKey(ModuleEnum.PERSONAL_LEGALPERSON.getSortAttr())
				&&o1.get(ModuleEnum.PERSONAL_LEGALPERSON.getSortAttr())!=null
				&&o2.containsKey(ModuleEnum.PERSONAL_LEGALPERSON.getSortAttr())
				&&o2.get(ModuleEnum.PERSONAL_LEGALPERSON.getSortAttr())!=null){
			date1 = DateUtils.getLocalDate(o1.get(ModuleEnum.PERSONAL_LEGALPERSON.getSortAttr()));
			date2 = DateUtils.getLocalDate(o2.get(ModuleEnum.PERSONAL_LEGALPERSON.getSortAttr()));
		}
		
		
		if(date1==null&&date2!=null){
			return 1;
		}
		if(date2==null&&date1!=null){
			return -1;
		}
		if(date1!=null&&date2!=null){
			
			if(date1.before(date2)){//返回true，说明date1 比date2早，
				return 1;
			}else{//返回false,包含两种情况，一是date1==date2,二是date1比date2晚
				if(DateUtils.getLocalStr("yyyy-MM-dd", date1).equals(DateUtils.getLocalStr("yyyy-MM-dd", date2))){
					return 0;
				}else{
					return -1;
				}
			}
		}
		return 0;
	}

	
}
