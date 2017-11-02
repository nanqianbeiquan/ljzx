package com.srd.ljzd.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;


public class JsonUtil {

	public static ArrayList<HashMap<String,String>> toArrayList(JSONArray array){
		if(array!=null&&array.size()>0){
			ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
			JSONObject jsonItem = null;
			HashMap<String,String> item = null;
			for(int i=0;i<array.size();i++){
				jsonItem = (JSONObject)array.get(i);
				
				item = JSONObject.parseObject(jsonItem.toJSONString(), new TypeReference<HashMap<String,String>>() {});
				
				list.add(item);
			}
			return list;
		}
		
		return null;
	}
}
