/**   
* @Title: CompanyInfoServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月16日 下午5:52:03 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.company.CompanyBasicInfo;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.company.RegisterInfo;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.service.company.CompanyBasicInfoService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;
import com.srd.ljzd.util.StringUtils4Dev;
import com.srd.ljzd.util.ThirdPartChannelEnum;

/** 
 * @ClassName: CompanyInfoServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月16日 下午5:52:03
 *  
 */
@Service("companyService")
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	protected static Logger logger = LogManager.getLogger(CompanyInfoServiceImpl.class.getName());

	private String dataService = Global.getConfig("dataService");
	private String searchCompany = Global.getConfig("searchCompany");
	private String searchCompanyUnion = Global.getConfig("searchCompanyUnion");
	private String bizInfo = Global.getConfig("bizInfo");
	private String bizInfoUnion = Global.getConfig("bizInfoUnion");
	private String usedCompanyNameURL = Global.getConfig("usedCompanyNameURL");
	
	@Autowired
	BizService bizService;
	@Autowired
	CompanyBasicInfoService companyBasicInfoService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public Map<String, Object> queryCompanyInfoList(String keyword, int resultNum) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String url = dataService + searchCompany;
		
		List<CompanyInfo> companyInfoList = new ArrayList<CompanyInfo>();
		
		CompanyInfo companyInfo = null;
		
		RegisterInfo registerInfo = null;
		
		int total = 0;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("keyword", keyword);
		paramMap.put("rows", String.valueOf(resultNum));
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONArray array = jsonResult.getJSONArray("data");
					
					if(array.size() >= 1){
						total = array.getJSONObject(0).getIntValue("NumFound");
						
						for(int i=1;i<array.size();i++){
							companyInfo = new CompanyInfo();
							
							registerInfo = new RegisterInfo();
							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("enterprisename"))){
								registerInfo.setEnterpriseName(array.getJSONObject(i).getString("enterprisename"));
							}else{
								registerInfo.setEnterpriseName("");
							}
							
							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("legalrepresentative"))){
								registerInfo.setLegalRepresentative(array.getJSONObject(i).getString("legalrepresentative"));
							}else{
								registerInfo.setLegalRepresentative("");
							}

							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("registrationstatus"))){
								registerInfo.setOperateStatus(array.getJSONObject(i).getString("registrationstatus"));
							}else{
								registerInfo.setOperateStatus("");
							}
							
							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("registeredcapital"))){
								registerInfo.setRegisteredCapital(array.getJSONObject(i).getString("registeredcapital"));
							}else{
								registerInfo.setRegisteredCapital("");
							}

							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("establishmentdate"))){
								registerInfo.setEstablishmentDate(array.getJSONObject(i).getString("establishmentdate"));
							}else{
								registerInfo.setEstablishmentDate("");
							}
							
							companyInfo.setRegisterInfo(registerInfo);
							
							companyInfoList.add(companyInfo);
						}
					}
				}
			}else{
				logger.error("搜索公司信息，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		resultMap.put("total", total);
		resultMap.put("companyInfoList", companyInfoList);
		
		//将查询结果存入缓存
		saveCompanyInfoListToCache(keyword, resultMap);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> queryCompanyInfoListUnion(String keyword, int resultNum) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String url = dataService + searchCompanyUnion;
		
		List<CompanyInfo> companyInfoList = new ArrayList<CompanyInfo>();
		
		CompanyInfo companyInfo = null;
		
		RegisterInfo registerInfo = null;
		
		int total = 0;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("keyword", keyword);
		paramMap.put("searchEngineRows", String.valueOf(resultNum-20));
		paramMap.put("zhongShuRows", "20");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONArray array = jsonResult.getJSONArray("data");
					
					if(array.size() >= 1){
						total = array.getJSONObject(0).getIntValue("NumFound");
						
						for(int i=1;i<array.size();i++){
							companyInfo = new CompanyInfo();
							
							registerInfo = new RegisterInfo();
							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("enterprisename"))){
								registerInfo.setEnterpriseName(array.getJSONObject(i).getString("enterprisename"));
							}else{
								registerInfo.setEnterpriseName("--");
							}
							
							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("legalrepresentative"))){
								registerInfo.setLegalRepresentative(array.getJSONObject(i).getString("legalrepresentative"));
							}else{
								registerInfo.setLegalRepresentative("--");
							}

							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("registrationstatus"))){
								registerInfo.setOperateStatus(array.getJSONObject(i).getString("registrationstatus"));
							}else{
								registerInfo.setOperateStatus("--");
							}
							
							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("registeredcapital"))){
								registerInfo.setRegisteredCapital(array.getJSONObject(i).getString("registeredcapital"));
							}else{
								registerInfo.setRegisteredCapital("--");
							}

							if(StringUtils.isNotEmpty(array.getJSONObject(i).getString("establishmentdate"))){
								registerInfo.setEstablishmentDate(array.getJSONObject(i).getString("establishmentdate"));
							}else{
								registerInfo.setEstablishmentDate("--");
							}
							
							companyInfo.setRegisterInfo(registerInfo);
							
							companyInfoList.add(companyInfo);
						}
					}
				}
			}else{
				logger.error("搜索公司信息，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		if(total>60){
			total = total + 20;
		}else{
			total = companyInfoList.size();
		}
		
		resultMap.put("total", total);
		resultMap.put("companyInfoList", companyInfoList);
		
		//将查询结果存入缓存
		saveCompanyInfoListToCache(keyword, resultMap);
		
		return resultMap;
	}

	@Override
	public void saveCompanyInfoListToCache(String keyword, Map<String, Object> resultMap) {
		ClientAccount account = (ClientAccount)session.getAttribute("account");
		
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		String key = "companyList_" + account.getAccountId() + "_" + keyword;
		
		redisTemplate.delete(key);
		
		redisTemplate.opsForValue().set(key, resultMap, 30, TimeUnit.MINUTES);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyInfo> getCompanyInfoListFromCache(String keyword) {
		ClientAccount account = (ClientAccount)session.getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String key = "companyList_" + account.getAccountId() + "_" + keyword;
		
		List<CompanyInfo> companyInfoList = null;
		
		Map<String, Object> resultMap = (Map<String, Object>) redisTemplate.opsForValue().get(key);
		
		if(resultMap==null||!resultMap.containsKey("companyInfoList")){
			resultMap = queryCompanyInfoList(keyword, 60);
		}
		if(resultMap != null){
			companyInfoList = (List<CompanyInfo>) resultMap.get("companyInfoList");
		}
		
		return companyInfoList;
	}

	@Override
	public String getProvinceOfCompany(String companyName) {
		
		String province = "";
		
		String url = dataService + bizInfo;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("companyName", companyName);
		paramMap.put("columns", "province");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONObject data = jsonResult.getJSONObject("data");
					
					if(data.containsKey("Registered_Info")){
						JSONArray array = data.getJSONArray("Registered_Info");
						
						if(array.size()>0){
							JSONObject registeredInfo = array.getJSONObject(0);
							
							if(registeredInfo.containsKey("Registered_Info:province")){
								province = registeredInfo.getString("Registered_Info:province");
							}
						}
					}
				}
			}else{
				logger.error("搜索公司信息，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		return province;
	}
	
	@Override
	public String getProvinceOfCompanyFromUnion(String companyName) {
		String province = "";
		
		String url = dataService + bizInfoUnion;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("companyName", companyName);
		paramMap.put("columns", "province");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONObject data = jsonResult.getJSONObject("data");
					
					if(data.containsKey("json")){
						JSONObject json = data.getJSONObject("json");
						
						if(json.containsKey("Registered_Info")){
							JSONArray array = json.getJSONArray("Registered_Info");
							
							if(array.size()>0){
								JSONObject registeredInfo = array.getJSONObject(0);
								
								if(registeredInfo.containsKey("Registered_Info:province")){
									province = registeredInfo.getString("Registered_Info:province");
								}
							}
						}
					}else if(data.containsKey("xml")){
						CompanyInfo companyInfo = convertCompanyInfoFromXml(data.getString("xml"));
						
						province = companyInfo.getRegisterInfo().getProvince();
					}
				}
			}else{
				logger.error("搜索公司信息，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		//如果从中数接口获取不到数据，就尝试从自己的数据库中获取
		if(StringUtils.isEmpty(province)){
			province = getProvinceOfCompany(companyName);
		}
		
		return province;
	}

	@Override
	public CompanyInfo getCompanyRegisterInfo(String companyName) {
		String url = dataService + bizInfo;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("companyName", companyName);
		paramMap.put("columns", "enterprisename,enterprisetype,registrationno,zch,orgregisteredcapital,registeredcapital,province,registrationstatus,entstatus,tyshxy_code,organizationcode,businessplace,residenceaddress,validityfrom,validityto,legalrepresentative,establishmentdate,businessscope,zsopscope");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		CompanyInfo companyInfo = new CompanyInfo();
		RegisterInfo registerInfo = null;
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONObject data = jsonResult.getJSONObject("data");
					
					if(data.containsKey("Registered_Info")){
						JSONArray array = data.getJSONArray("Registered_Info");
						
						if(array.size()>0){
							registerInfo = new RegisterInfo();
							
							JSONObject registeredInfo = array.getJSONObject(0);
							
							if(registeredInfo.containsKey("Registered_Info:enterprisename")){
								registerInfo.setEnterpriseName(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:enterprisename")));
							}else{
								registerInfo.setEnterpriseName("--");
							}
							if(registeredInfo.containsKey("Registered_Info:enterprisetype")){
								registerInfo.setEnterpriseType(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:enterprisetype")));
							}else{
								registerInfo.setEnterpriseType("--");
							}
							String registeredCapital = registeredInfo.getString("Registered_Info:orgregisteredcapital");
							if("--".equals(registeredCapital)){
								registeredCapital = registeredInfo.getString("Registered_Info:registeredcapital")+"万元";
							}
							registerInfo.setRegisteredCapital(registeredCapital);
							
							String operateStatus = StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:registrationstatus"));
							if("--".equals(operateStatus)){
								operateStatus = StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:entstatus"));
							}
							registerInfo.setOperateStatus(operateStatus);
							//统一社会信用代码/注册号
							String tyshxy_code = StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:tyshxy_code"));
							if("--".equals(tyshxy_code)){
								tyshxy_code = StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:registrationno"));
								if("--".equals(tyshxy_code)){
									tyshxy_code = StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:zch"));
								}
							}
							registerInfo.setUnifiedSocialCreditCode(tyshxy_code);
							
							if(registeredInfo.containsKey("Registered_Info:organizationcode")){
								registerInfo.setOrganizationCode(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:organizationcode")));
							}else{
								registerInfo.setOrganizationCode("--");
							}

							if(registeredInfo.containsKey("Registered_Info:residenceaddress") && StringUtils.isNotEmpty(registeredInfo.getString("Registered_Info:residenceaddress"))){
								registerInfo.setBusinessPlace(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:residenceaddress")));
							}else if(registeredInfo.containsKey("Registered_Info:businessplace")){
								registerInfo.setBusinessPlace(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:businessplace")));
							}else{
								registerInfo.setBusinessPlace("--");
							}
							
							if(registeredInfo.containsKey("Registered_Info:validityfrom")){
								registerInfo.setValidityFrom(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:validityfrom")));
							}else{
								registerInfo.setValidityFrom("--");
							}
							
							if(registeredInfo.containsKey("Registered_Info:validityto")){
								registerInfo.setValidityTo(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:validityto")));
							}else{
								registerInfo.setValidityTo("--");
							}
							
							if(registeredInfo.containsKey("Registered_Info:legalrepresentative")){
								registerInfo.setLegalRepresentative(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:legalrepresentative")));
							}else{
								registerInfo.setLegalRepresentative("--");
							}
							
							if(registeredInfo.containsKey("Registered_Info:establishmentdate")){
								registerInfo.setEstablishmentDate(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:establishmentdate")));
							}else{
								registerInfo.setEstablishmentDate("--");
							}
							if(registeredInfo.containsKey("Registered_Info:province")){
								registerInfo.setProvince(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:province")));
							}else{
								registerInfo.setProvince("--");
							}
							if(registeredInfo.containsKey("Registered_Info:businessscope") && StringUtils.isNotEmpty(registeredInfo.getString("Registered_Info:businessscope"))){
								registerInfo.setBusinessScope(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:businessscope")));
							}else if(registeredInfo.containsKey("Registered_Info:zsopscope")){
								registerInfo.setBusinessScope(StringUtils4Dev.stringEmptyDeal(registeredInfo.getString("Registered_Info:zsopscope")));
							}else{
								registerInfo.setBusinessScope("--");
							}
						}
					}
				}
			}else{
				logger.error("搜索公司信息，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		companyInfo.setRegisterInfo(registerInfo);
		
		return companyInfo;
	}
	
	@Override
	public CompanyInfo getCompanyRegisterInfoQiChaCha(String companyName) {
		BizMsg companyAdapter = bizService.getBrefCompany(companyName, ThirdPartChannelEnum.QI_CHA_CHA);
		if(companyAdapter!=null&&companyAdapter.getBrefCompany()!=null){
			CompanyInfo companyInfo = new CompanyInfo();
			RegisterInfo registerInfo = new RegisterInfo();
			registerInfo.setEnterpriseName(companyAdapter.getBrefCompany().get("enterprisename"));
			registerInfo.setProvince(companyAdapter.getBrefCompany().get("province"));
			companyInfo.setRegisterInfo(registerInfo);
			return companyInfo;
		}
		return null;
	}

	@Override
	public CompanyInfo getCompanyRegisterInfoFromUnion(String companyName) {		
		String url = dataService + bizInfoUnion;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("companyName", companyName);
		paramMap.put("columns", "enterprisename,registeredcapital,province,registrationstatus,tyshxy_code,organizationcode,businessplace,residenceaddress,validityfrom,validityto,legalrepresentative,establishmentdate,businessscope,zsopscope");
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		CompanyInfo companyInfo = new CompanyInfo();
		RegisterInfo registerInfo = null;
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONObject data = jsonResult.getJSONObject("data");
					
					if(data.containsKey("json")){
						JSONObject json = data.getJSONObject("json");
						
						if(json.containsKey("Registered_Info")){
							JSONArray array = json.getJSONArray("Registered_Info");
							
							if(array.size()>0){
								registerInfo = new RegisterInfo();
								
								JSONObject registeredInfo = array.getJSONObject(0);
								
								if(registeredInfo.containsKey("Registered_Info:enterprisename")){
									registerInfo.setEnterpriseName(registeredInfo.getString("Registered_Info:enterprisename"));
								}else{
									registerInfo.setEnterpriseName("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:registeredcapital")){
									registerInfo.setRegisteredCapital(registeredInfo.getString("Registered_Info:registeredcapital"));
								}else{
									registerInfo.setRegisteredCapital("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:registrationstatus")){
									registerInfo.setOperateStatus(registeredInfo.getString("Registered_Info:registrationstatus"));
								}else{
									registerInfo.setOperateStatus("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:tyshxy_code")){
									registerInfo.setUnifiedSocialCreditCode(registeredInfo.getString("Registered_Info:tyshxy_code"));
								}else{
									registerInfo.setUnifiedSocialCreditCode("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:organizationcode")){
									registerInfo.setOrganizationCode(registeredInfo.getString("Registered_Info:organizationcode"));
								}else{
									registerInfo.setOrganizationCode("");
								}

								if(registeredInfo.containsKey("Registered_Info:residenceaddress") && StringUtils.isNotEmpty(registeredInfo.getString("Registered_Info:residenceaddress"))){
									registerInfo.setBusinessPlace(registeredInfo.getString("Registered_Info:residenceaddress"));
								}else if(registeredInfo.containsKey("Registered_Info:businessplace")){
									registerInfo.setBusinessPlace(registeredInfo.getString("Registered_Info:businessplace"));
								}else{
									registerInfo.setBusinessPlace("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:validityfrom")){
									registerInfo.setValidityFrom(registeredInfo.getString("Registered_Info:validityfrom"));
								}else{
									registerInfo.setValidityFrom("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:validityto")){
									registerInfo.setValidityTo(registeredInfo.getString("Registered_Info:validityto"));
								}else{
									registerInfo.setValidityTo("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:legalrepresentative")){
									registerInfo.setLegalRepresentative(registeredInfo.getString("Registered_Info:legalrepresentative"));
								}else{
									registerInfo.setLegalRepresentative("");
								}
								
								if(registeredInfo.containsKey("Registered_Info:establishmentdate")){
									registerInfo.setEstablishmentDate(registeredInfo.getString("Registered_Info:establishmentdate"));
								}else{
									registerInfo.setEstablishmentDate("");
								}
								if(registeredInfo.containsKey("Registered_Info:province")){
									registerInfo.setProvince(registeredInfo.getString("Registered_Info:province"));
								}else{
									registerInfo.setProvince("");
								}
								if(registeredInfo.containsKey("Registered_Info:businessscope") && StringUtils.isNotEmpty(registeredInfo.getString("Registered_Info:businessscope"))){
									registerInfo.setBusinessScope(registeredInfo.getString("Registered_Info:businessscope"));
								}else if(registeredInfo.containsKey("Registered_Info:zsopscope")){
									registerInfo.setBusinessScope(registeredInfo.getString("Registered_Info:zsopscope"));
								}else{
									registerInfo.setBusinessScope("");
								}
							}
						}
					}else if(data.containsKey("xml")){
						return convertCompanyInfoFromXml(data.getString("xml"));
					}
				}
			}else{
				logger.error("搜索公司信息，大数据接口返回数据有误，respCode:" + jsonResult.getString("respCode") + ", respMsg:" + jsonResult.getString("respMsg"));
			}
		}
		
		companyInfo.setRegisterInfo(registerInfo);
		
		return companyInfo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public CompanyInfo convertCompanyInfoFromXml(String xml) {
		
		CompanyInfo companyInfo = new CompanyInfo();
		RegisterInfo registerInfo = new RegisterInfo();
		
		try {
			Document document = DocumentHelper.parseText(xml);
			
			Element root = document.getRootElement();
			
			if("DATA".equalsIgnoreCase(root.getName())){
				
				Element orderList = root.element("ORDERLIST");//接口返回状态信息
				
				if(orderList!=null&&orderList.hasContent()){
					List items = orderList.elements("ITEM");
					
					Element order = (Element)items.get(0);
					
					if("1".equalsIgnoreCase(order.element("STATUS").getTextTrim())){//返回状态正常
						
						Element basic = root.element("BASIC");//基本信息
						registerInfo = convertRegisterInfoFromXml(basic);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		companyInfo.setRegisterInfo(registerInfo);
		
		return companyInfo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public RegisterInfo convertRegisterInfoFromXml(Element basic) {
		RegisterInfo registerInfo = new RegisterInfo();
		
		if(basic!=null&&basic.hasContent()){
			
			List items = basic.elements("ITEM");
			
			if(items!=null && items.size()>0){
				
				Element item = (Element)items.get(0);
				
			    if(item.hasContent()){
			    	registerInfo.setEnterpriseName(item.element("ENTNAME")!=null?item.element("ENTNAME").getTextTrim():"");
			    	registerInfo.setRegistrationNo(item.element("REGNO")!=null?item.element("REGNO").getTextTrim():"");
			    	registerInfo.setUnifiedSocialCreditCode(item.element("CREDITCODE")!=null?item.element("CREDITCODE").getTextTrim():"");
			    	registerInfo.setOrganizationCode(item.element("ORGCODES")!=null?item.element("ORGCODES").getTextTrim():"");
			    	registerInfo.setProvince(item.element("REGORGPROVINCE")!=null?item.element("REGORGPROVINCE").getTextTrim():"");
			    	registerInfo.setLegalRepresentative(item.element("FRNAME")!=null?item.element("FRNAME").getTextTrim():"");
			    	registerInfo.setEnterpriseType(item.element("ENTTYPE")!=null?item.element("ENTTYPE").getTextTrim():"");
			    	registerInfo.setEstablishmentDate(item.element("ESDATE")!=null?item.element("ESDATE").getTextTrim():"");
			    	registerInfo.setIssueDate("");
			    	registerInfo.setRegisteredCapital(item.element("REGCAP")!=null?item.element("REGCAP").getTextTrim():"");
			    	registerInfo.setValidityFrom(item.element("OPFROM")!=null?item.element("OPFROM").getTextTrim():"");
			    	registerInfo.setValidityTo(item.element("OPTO")!=null?item.element("OPTO").getTextTrim():"");
			    	registerInfo.setBusinessScope(item.element("OPSCOPE")!=null?item.element("OPSCOPE").getTextTrim():"");
			    	registerInfo.setRegistrationInstitution(item.element("REGORG")!=null?item.element("REGORG").getTextTrim():"");
			    	registerInfo.setOperateStatus(item.element("ENTSTATUS")!=null?item.element("ENTSTATUS").getTextTrim():"");
			    	
			    	if(item.element("DOM") != null){
			    		registerInfo.setBusinessPlace(item.element("DOM").getTextTrim());
			    	}else{
			    		if(item.element("OPLOC") != null){
				    		registerInfo.setBusinessPlace(item.element("OPLOC").getTextTrim());
				    	}else{
				    		registerInfo.setBusinessPlace("");
				    	}
			    	}
			    }
			}
		}
		
		return registerInfo;
	}
	
	
	@Override
	public Map<String, Object> getCompanyInfoListFromCacheByNum(String keyword,int num) {
		ClientAccount account = (ClientAccount)session.getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}	
		String key = "companyList_" + account.getAccountId() + "_" + keyword;
		
		Map<String, Object> resultMap = (Map<String, Object>) redisTemplate.opsForValue().get(key);
		
		if(resultMap == null){
			resultMap = queryCompanyInfoList(keyword, num);
		}
		
		return resultMap;
	}
	/**
	 * 构造companyInfo 对象的Page对象
	 */
	@Override
	public Page<Map<String,Object>> buildCompanyInfoPage(
			List<CompanyInfo> companyInfoList, Integer pageNo, Integer pageSize) {
		if(companyInfoList!=null||companyInfoList.size()>0){
			if(pageNo==null||pageNo<=0){
				pageNo = 1;
			}
			if(pageSize==null||pageSize<=0){
				pageNo = Constant.DETAULT_PAGE_SIZE;
			}
			int size =  companyInfoList.size();
			int pageNum = (size % pageSize == 0) ? (size / pageSize) : (size
					/ pageSize + 1);
			int startIndex = pageSize * (pageNo - 1);
			int endIndex = pageSize * pageNo - 1;
			if(endIndex>=size){
				endIndex = size - 1;
			}
			//判断下标是否在合法范围内
			if(startIndex>=0&&startIndex<companyInfoList.size()
					&&(endIndex+1)>startIndex&&(endIndex+1)<=companyInfoList.size()){
				List<CompanyInfo> tempList = companyInfoList.subList(startIndex, endIndex+1);
				if(tempList!=null){
					List<Map<String,Object>> targetList = new ArrayList<Map<String,Object>>(tempList.size());
					Map<String,Object> targetItem = null;
					for(CompanyInfo record:tempList){
						if(record!=null&&record.getRegisterInfo()!=null){
							targetItem = new HashMap<String, Object>();
							targetItem.put("companyName", record.getRegisterInfo().getEnterpriseName());//公司名称
							targetItem.put("legalRepresentative",record.getRegisterInfo().getLegalRepresentative());//法定代表人
							targetItem.put("registeredCapital",record.getRegisterInfo().getRegisteredCapital());//注册资本
							targetItem.put("establishmentDate",record.getRegisterInfo().getEstablishmentDate());//成立日期
							targetItem.put("operateStatus",record.getRegisterInfo().getOperateStatus());//经营状态
							targetList.add(targetItem);
						}
					}
					Page<Map<String,Object>> target = new Page<Map<String,Object>>();
					target.setResults(targetList);
					target.setRecordSum((long)size);
					target.setCurrentPageNo(pageNo);
					target.setPageSize(pageSize);
					target.setPageSum(pageNum);
					return target;
				}
			}
		}
		return null;
	}

	@Override
	public CompanyInfo buildCompanyInfoFromMultiChannel(String companyName) {
		CompanyBasicInfo ownedCompany = companyBasicInfoService.queryByName(companyName);
		if(ownedCompany!=null){
			CompanyInfo companyInfo = new CompanyInfo();
			RegisterInfo registerInfo = new RegisterInfo();
			registerInfo.setEnterpriseName(ownedCompany.getCompanyName());
			registerInfo.setProvince(ownedCompany.getProvince());
			registerInfo.setCompanyId(ownedCompany.getCompanyId());
			companyInfo.setRegisterInfo(registerInfo);
			return companyInfo;
		}
		CompanyInfo remoteCompany = this.getCompanyRegisterInfoQiChaCha(companyName);

		if(remoteCompany!=null&&remoteCompany.getRegisterInfo()!=null
				){
			String tmpCompanyName = remoteCompany.getRegisterInfo().getEnterpriseName();
			String tmpProvince = remoteCompany.getRegisterInfo().getProvince();
			if(StringUtils.isNotEmpty(tmpCompanyName)&&
					StringUtils.isNotEmpty(tmpProvince)){
				CompanyBasicInfo savedCompany = null;
				if(!tmpCompanyName.equals(companyName)){
					//返回的公司名称不一致，按企业更名处理，存储返回的公司名称
					LoggerUtils.warn("buildCompanyInfoFromMultiChannel,return unsame companyName,source="+companyName+",target="+tmpCompanyName);
					ownedCompany = companyBasicInfoService.queryByName(tmpCompanyName);
					if(ownedCompany==null){
						savedCompany = companyBasicInfoService.add(tmpCompanyName, tmpProvince, 0);
					}else{
						savedCompany = ownedCompany;
					}
				}else{
					savedCompany = companyBasicInfoService.add(companyName, tmpProvince, 0);
				}
				if(savedCompany!=null){
					remoteCompany.getRegisterInfo().setCompanyId(savedCompany.getCompanyId());
				}
			}
		}
		
		return remoteCompany;
	}

	@Override
	public JSONArray getUsedName(String companyName) {
        
		String url = dataService + usedCompanyNameURL;
		
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("companyName", companyName);
		//paramMap.put("rows", String.valueOf(resultNum));
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null && jsonResult.containsKey("respCode")){
			if(jsonResult.getString("respCode").equals("200") && jsonResult.containsKey("data")){
				if(jsonResult.get("data") != null && !jsonResult.get("data").equals("") && !jsonResult.get("data").equals("[]")){
					JSONObject data = jsonResult.getJSONObject("data");
					System.out.println(data);
					if(data.containsKey("currentName")){
						String currentName = data.getString("currentName");
						if(companyName.equals(currentName)&&data.containsKey("oldname")){
							return data.getJSONArray("oldname");
						}
					}
					
				}
			}
		}
		return null;
	}
	
	
}
