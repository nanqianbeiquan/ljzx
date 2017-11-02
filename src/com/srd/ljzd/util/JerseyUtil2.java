/**   
* @Title: JerseyUtil.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年5月6日 上午9:20:05 
* @version V1.0   
*/
package com.srd.ljzd.util;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



/** 
 * @ClassName: JerseyUtil
 * @Description: Jersey工具类,升级版，请求的媒体类型作为参数
 * @author shiyong
 * @date 2016年5月6日 上午9:20:05
 *  
 */
public class JerseyUtil2 {
	
	protected static Logger logger = LogManager.getLogger(JerseyUtil2.class.getName());
	
	/** 
	* @Title: sendHttpFormRequest 
	* @Description: 发送httpForm请求
	* @param @param url
	* @param @param params
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年5月6日 上午9:29:39
	*/
	public static JSONObject sendHttpFormRequest(String url, Map<String, String> params,MediaType mediaType){
		Form form = new Form();
		
		for(String key:params.keySet()){
			form.param(key, params.get(key));
		}
		
		JSONObject jsonObject = null;
		String result = null;
		try{
		
			long t1 = System.currentTimeMillis();
			
			Client client = ClientBuilder.newClient();
			
			WebTarget target = client.target(url);
			
			result = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form,mediaType), String.class);
			
			long t2 = System.currentTimeMillis();
			
			LoggerUtils.info("接口地址：" + url +"， 接口访问所需时间：" + (t2-t1) + "ms");
			
			if(result!=null&&!"null".equals(result.trim())){
				jsonObject = JSONObject.parseObject(result);
			}
		
		}catch(Exception e){
	        LoggerUtils.error("访问大数据接口异常：" + e.getMessage() + "url:" + url + "参数：" +params + "返回值：" +result, e);
		}
		
		return jsonObject;
	}
	
	public static JSONArray sendHttpFormRequestReturnArray(String url, Map<String, String> params,MediaType mediaType){
		Form form = new Form();
		
		for(String key:params.keySet()){
			form.param(key, params.get(key));
		}
		
		JSONArray jsonArray = null;
		
		try{
		
			long t1 = System.currentTimeMillis();
			
			Client client = ClientBuilder.newClient();
			
			WebTarget target = client.target(url);
			
			String result = target.request(MediaType.APPLICATION_JSON_TYPE).
					header("Content-Type", "UTF-8").
					post(Entity.entity(form,mediaType), String.class);
			
			long t2 = System.currentTimeMillis();
			
			LoggerUtils.info("接口地址：" + url +"， 接口访问所需时间：" + (t2-t1) + "ms");
			
			jsonArray = JSONObject.parseArray(result);
			
		}catch(Exception e){
	        LoggerUtils.error("访问大数据接口异常：" + e.getMessage() + "url:" + url + "参数：" +params, e);
		}
		
		return jsonArray;
	}
	
	/** 
	* @Title: sendHttpFormRequest1 
	* @Description: 发送httpForm请求
	* @param @param url
	* @param @param params
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月3日 下午1:01:45
	*/
	@SuppressWarnings("rawtypes")
	public static JSONObject sendHttpFormRequest1(String url, Map<String, Object> params,MediaType mediaType){
		Form form = new Form();
		Object value = null;
		
		for(String key:params.keySet()){
			value = params.get(key);
			
			if(value instanceof String){
				form.param(key, (String)params.get(key));
			}else if(value instanceof List){//value 是集合
				for(Object innervalue : (List)value){
					form.param(key, (String)innervalue);
				}
			}
		}
		
		JSONObject jsonObject = null;
		
		try{
			Client client = ClientBuilder.newClient();
			
			WebTarget target = client.target(url);
			
			String result = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form,mediaType), String.class);
			
			jsonObject = JSONObject.parseObject(result);
		}catch(Exception e){
			StringBuffer errorMsg = new StringBuffer("");
			
			for(String key:params.keySet()){
				value = params.get(key);
				
				errorMsg.append("key:" + key + ", value:" + value);
			}
			
			LoggerUtils.error(errorMsg.toString(), e);
		}
		
		return jsonObject;
	}
	
	public static JSONObject sendHttpFormRequest(String url,String params,MediaType mediaType){
		
		JSONObject jsonObject = null;
		
		try{
		
			Client client = ClientBuilder.newClient();
			
			WebTarget target = client.target(url);
			
			String result = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(params,mediaType), String.class);
			
			jsonObject = JSONObject.parseObject(result);
		
		}catch(Exception e){
			LoggerUtils.error("访问接口异常：" + e.getMessage() + "参数：" +params, e);
		}
		
		return jsonObject;
	}
	
	public static String sendHttpFormRequestString(String url, Map<String, String> params, MediaType mediaType){
		Form form = new Form();
		
		for(String key:params.keySet()){
			form.param(key, params.get(key));
		}
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(url);
		
		String result = target.request(MediaType.APPLICATION_JSON_TYPE)
				      .post(Entity.entity(form,mediaType), String.class);
		
		return result;
	}
	
}
