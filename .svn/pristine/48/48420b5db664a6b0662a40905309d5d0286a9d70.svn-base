package com.srd.ljzd.entityparser;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entityparser.base.BizParser;
@Component("bizJsonBrandParser")
public class BizJsonBrandParser extends BizParser{
	protected static Logger log = LogManager.getLogger(BizJsonBrandParser.class.getName());
	@Override
	public BizMsg parse(Object data) {
		if(data==null){
			return null;
		}
		JSONArray sourceJsonArray = (JSONArray)data;
		BizMsg company = new BizMsg();
		
		if(sourceJsonArray!=null&&sourceJsonArray.size()>0){
			ArrayList<HashMap<String,String>> targetArray = new ArrayList<HashMap<String,String>>();
			HashMap<String,String> targetItem = null;
			
			JSONObject sourceItem = null;
			String value = null;
			for(Object obj : sourceJsonArray){
				sourceItem = (JSONObject)obj;
				targetItem = new HashMap<String,String>();
				
				for(Object key : sourceItem.keySet()){
					
					if(key!=null){
						value = (sourceItem.get(key)==null?"":sourceItem.get(key)).toString();
						if("null".equals(value)||"\\N".equals(value)){
							value = "";
						}
						targetItem.put((String)key, value);
					}
				}
				targetArray.add(targetItem);
			}
			
			company.setBrandArray(targetArray);
			return company;
		}
		
		return null;
	}

}
