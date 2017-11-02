/**  
* @Title: AddRelationCompanyAndPersonServiceImpl.java
* @Package com.srd.ljzd.serviceImpl.relationCompanyAndPerson
* @Description: TODO(用一句话描述该文件做什么)
* @author zihui.pei  
* @date 2017年1月16日 上午10:49:54
* @version V1.3
*/
package com.srd.ljzd.serviceImpl.relationCompanyAndPerson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.entity.relationCompanyAndPerson.RelationCompanyAndPerson;
import com.srd.ljzd.service.relationCompanyAndPerson.AddRelationCompanyAndPersonService;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.StringTool;


/**   
 * 版权所有：2017
 * 项目名称：ljzx   
 *
 * 类描述：
 * 类名称：com.srd.ljzd.serviceImpl.relationCompanyAndPerson.AddRelationCompanyAndPersonServiceImpl     
 * 创建人：裴子辉
 * 创建时间：2017年1月16日 上午10:49:54   
 * 修改人：
 * 修改时间：2017年1月16日 上午10:49:54   
 * 修改备注：   
 * @version   V1.3    
 */
@Service("addRelationCompanyAndPersonService")
public class AddRelationCompanyAndPersonServiceImpl implements AddRelationCompanyAndPersonService {
	
protected static Logger logger = LogManager.getLogger(AddRelationCompanyAndPersonServiceImpl.class.getName());
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	

	@Override
	public Map<String, List<RelationCompanyAndPerson>> queryAllRelationsList(String companyName){
		Map<String, List<RelationCompanyAndPerson>> map = new HashMap<String, List<RelationCompanyAndPerson>>();
		map = getRelationCompanyAndPersonListFromCache(companyName);
		if(map==null||(map!=null&&map.isEmpty())){
			map = getRelationCompanyAndPersonThrouthInterface(companyName);
		}
		return map;
	}

	/**
	* @Title: getRelationCompanyAndPersonThrouthInterface
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param  @param companyName
	* @param  @param key
	* @param  @return  
	* @return Map<String,List<RelationCompanyAndPerson>>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月23日 上午10:03:17
	*/
	private Map<String, List<RelationCompanyAndPerson>> getRelationCompanyAndPersonThrouthInterface(
			String companyName) {
		Map<String, List<RelationCompanyAndPerson>> map = new HashMap<String, List<RelationCompanyAndPerson>>();
		String url = Global.getConfig("dataService") + Global.getConfig("getRelationCompanyAndPerson");
		Map<String, String> params = new HashMap<String, String>();
		params.put("compNameAjax", companyName);

		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, params);
		if (jsonResult != null){
			if (jsonResult.containsKey("results")) {
				JSONArray resultsJSONArray = jsonResult.getJSONArray("results");
				JSONObject jo =(JSONObject)resultsJSONArray.get(0);
				map = resolveGraph(jsonResult,companyName);
				if(map!=null&&!map.isEmpty()){
					saveRelationCompanyAndPersonListToCache(companyName,map); 
				}
			} 
		}
		return map;
	}

	/**
	* @Title: resolveGraph
	* @Description: TODO(解析每个图节点)
	* @param  @param jsonResult  
	* @return void    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月16日 下午3:43:25
	*/
	private Map resolveGraph(JSONObject jsonResult,String companyName) {
		Map<String, List<RelationCompanyAndPerson>> resultMap = new HashMap<String, List<RelationCompanyAndPerson>>();
		JSONArray resultsJSONArray = jsonResult.getJSONArray("results");
		JSONObject jsonObject =(JSONObject)resultsJSONArray.get(0);
		
		JSONObject relationshipsObject = new JSONObject();

		List<RelationCompanyAndPerson>  stockholderList = new ArrayList<RelationCompanyAndPerson>();// 股东  自然人股东
		List<RelationCompanyAndPerson>  stockholderCorporationLegalList = new ArrayList<RelationCompanyAndPerson>();// 股东  法人股东
		List<RelationCompanyAndPerson>  corporationLegalPersonList = new ArrayList<RelationCompanyAndPerson>();//企业法人； corporation legal person
		List<RelationCompanyAndPerson>  managerList = new ArrayList<RelationCompanyAndPerson>();//高管
		List<RelationCompanyAndPerson>  investmentCompanyList = new ArrayList<RelationCompanyAndPerson>();//投资公司列表；
		
		// graph
		if(jsonObject.containsKey("data")){
			JSONArray graphObjectArray  =jsonObject.getJSONArray("data");
			for (int i = 0; i < graphObjectArray.size(); i++) {
				JSONObject gobj =(JSONObject)graphObjectArray.get(i);
				JSONObject graphObject = (JSONObject)gobj.get("graph");
				
				Map relationMap = new HashMap();
				if(graphObject.containsKey("relationships")){
					JSONArray  relationshipsArray  =graphObject.getJSONArray("relationships");
					if(relationshipsArray!=null&&!relationshipsArray.isEmpty()){
						
						relationshipsObject = (JSONObject)relationshipsArray.get(0);
						 
						 if(relationshipsObject!=null){
							 if(relationshipsObject.containsKey("id")){
								 relationMap.put("id",relationshipsObject.get("id"));
							 }
							 if(relationshipsObject.containsKey("type")){
								 relationMap.put("type",relationshipsObject.get("type"));
							 }
							 if(relationshipsObject.containsKey("startNode")){
								 relationMap.put("startNode",relationshipsObject.get("startNode"));
							 }
							 if(relationshipsObject.containsKey("endNode")){
								 relationMap.put("endNode",relationshipsObject.get("endNode"));
							 }
						 }
					}
					
				}
				
				// 节点；
				if(graphObject.containsKey("nodes")){
					JSONArray nodesArray = graphObject.getJSONArray("nodes");
					for(int j = 0; j < nodesArray.size(); j++) {
						JSONObject nodesArrayObject =(JSONObject)nodesArray.get(j);
						Map nodesMap = new HashMap();
						RelationCompanyAndPerson rcp = new RelationCompanyAndPerson();
						String idInNodes="";
						String labels="";
						String tsInProperties="";
						String companyNameInProperties="";
						String personNameInProperties="";
						String keyInProperties="";
						
						if(nodesArrayObject!=null){
							if(nodesArrayObject.containsKey("id")){
								idInNodes = nodesArrayObject.getString("id");
							}
							
							if(nodesArrayObject.containsKey("labels")){
								JSONArray labelsArray = nodesArrayObject.getJSONArray("labels");
								if(labelsArray!=null){
									for (int l = 0; l < labelsArray.size();l++){
										String slabelStr = (String)labelsArray.get(l);
										labels += slabelStr+"_";
									}
								}
							}
							
							if(nodesArrayObject.containsKey("properties")){
								JSONObject propertiesObject =(JSONObject)nodesArrayObject.get("properties");
								if(propertiesObject!=null){
									if(propertiesObject.containsKey("key")){
										keyInProperties = propertiesObject.getString("key");
									}
									if(propertiesObject.containsKey("ts")){
										tsInProperties = propertiesObject.getString("ts");
									}
									if(propertiesObject.containsKey("公司名称")){
										companyNameInProperties = propertiesObject.getString("公司名称");
									}
									if(propertiesObject.containsKey("姓名")){
										personNameInProperties = propertiesObject.getString("姓名");
									}
								}
							}
						}
						
						rcp.setId(idInNodes);
						rcp.setLabels(labels);
						rcp.setTsInProperties(tsInProperties);
						rcp.setCompanyNameInProperties(companyNameInProperties);
						rcp.setPersonNameInProperties(personNameInProperties);
						
						//根据relationShip里面的type来判断节点的如下三种类型
						// 法定代表人 // 投资 // 任职
						// 选择每个graph中node的startNode等于id的选项；
						
						if(relationshipsObject!=null&&rcp!=null&&relationshipsObject.containsKey("startNode")){
							if(relationshipsObject.get("startNode").toString().equals(rcp.getId())){
								if("法定代表人".equals(relationshipsObject.get("type").toString())){  // 法人，
									if(StringTool.isNotNullAndBlack(rcp.getPersonNameInProperties())){
										corporationLegalPersonList.add(rcp);
									}
								}else if("投资".equals(relationshipsObject.get("type").toString())){ // 股东 
									// 筛选自然人股东列表
									if(rcp.getLabels().startsWith("Person")){
										if(StringTool.isNotNullAndBlack(rcp.getPersonNameInProperties())){
											stockholderList.add(rcp);
										}
									}else if(rcp.getLabels().startsWith("Company")){
										// 筛选投资企业和企业股东    企业股东,剔除本公司；
										if(StringTool.isNotNullAndBlack(rcp.getCompanyNameInProperties())&&!rcp.getCompanyNameInProperties().equals(companyName)){
											stockholderCorporationLegalList.add(rcp);
										}
									}
								}else if("任职".equals(relationshipsObject.get("type").toString())){ // 高管	
									if(StringTool.isNotNullAndBlack(rcp.getPersonNameInProperties())){	
										managerList.add(rcp);
									}
								}
							}
							
							if(relationshipsObject.get("endNode").toString().equals(rcp.getId())){
							if("投资".equals(relationshipsObject.get("type").toString())){ // 股东 
									// 筛选企业，
									if(rcp.getLabels().startsWith("Company")){
										// 投资企业
										if(StringTool.isNotNullAndBlack(rcp.getCompanyNameInProperties())&&!rcp.getCompanyNameInProperties().equals(companyName)){
											investmentCompanyList.add(rcp);
										}
									}
								}
							}
							
						}
					}
				}
			}
		}
		
		resultMap.put("stockholderList", removeDouble(stockholderList));
		resultMap.put("stockholderCorporationLegalList",removeDouble(stockholderCorporationLegalList));
		resultMap.put("corporationLegalPersonList", removeDouble(corporationLegalPersonList));
		resultMap.put("managerList", removeDouble(managerList));
		resultMap.put("investmentCompanyList",removeDouble(investmentCompanyList));
		
		return resultMap;
	}
	
	/**
	* @Title: removeDouble
	* @Description: TODO(去重)
	* @param  @param list
	* @param  @return  
	* @return List<RelationCompanyAndPerson>    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2017年1月20日 下午3:29:59
	*/
	public List<RelationCompanyAndPerson>  removeDouble( List<RelationCompanyAndPerson>  list){
		//去掉重复的企业；
		RelationCompanyAndPerson orcp=new RelationCompanyAndPerson();
		RelationCompanyAndPerson ircp=new RelationCompanyAndPerson();
		
		List<RelationCompanyAndPerson>  innerList = list;
		
		for(int i=0;i<list.size();i++){
			orcp = list.get(i);
			for (int j=list.size()-1;j>i;j--) {
				ircp=innerList.get(j);
				// 企业去重；
				if(orcp.getLabels().startsWith("Company")){
					if(StringTool.isNotNullAndBlack(orcp.getCompanyNameInProperties())&&StringTool.isNotNullAndBlack(ircp.getCompanyNameInProperties())&&ircp.getCompanyNameInProperties().equals(orcp.getCompanyNameInProperties())){
						list.remove(orcp);
					}
				}
				// 个人去重；
				if(orcp.getLabels().startsWith("Person")){
					if(StringTool.isNotNullAndBlack(orcp.getPersonNameInProperties())&&StringTool.isNotNullAndBlack(ircp.getPersonNameInProperties())&&ircp.getPersonNameInProperties().equals(orcp.getPersonNameInProperties())){
						list.remove(orcp);
					}
				}
			}
			
		}
		return list;
	}
	

	@Override
	public void saveRelationCompanyAndPersonListToCache(String companyName,Map<String, List<RelationCompanyAndPerson>> map) {
		String key = "RelationCompanyAndPersonList"+"_"+ companyName;
		redisTemplate.delete(key);
		redisTemplate.opsForValue().set(key, map, 30, TimeUnit.MINUTES);
		
	}

	@Override
	public Map<String, List<RelationCompanyAndPerson>> getRelationCompanyAndPersonListFromCache(String companyName){
		 String key = "RelationCompanyAndPersonList"+"_"+ companyName;
		 Map<String, List<RelationCompanyAndPerson>>  map = (Map<String, List<RelationCompanyAndPerson>>) redisTemplate.opsForValue().get(key);
		 return map;
	}

}
