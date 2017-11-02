package com.srd.ljzd.serviceImpl.monitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.monitor.MonitorCompanyEventDao;
import com.srd.ljzd.dto.monitor.MonitorComEventDTO;
import com.srd.ljzd.entity.monitor.AdPunishment;
import com.srd.ljzd.entity.monitor.BrokenPromiseInfo;
import com.srd.ljzd.entity.monitor.BusinessAbnormal;
import com.srd.ljzd.entity.monitor.ChangedAnnouncement;
import com.srd.ljzd.entity.monitor.ChattelMortgage;
import com.srd.ljzd.entity.monitor.CourtAnnouncement;
import com.srd.ljzd.entity.monitor.EquityPledge;
import com.srd.ljzd.entity.monitor.ExecutedPerson;
import com.srd.ljzd.entity.monitor.FoodDrug;
import com.srd.ljzd.entity.monitor.HuanBao;
import com.srd.ljzd.entity.monitor.InvestorInfo;
import com.srd.ljzd.entity.monitor.MonitorCompanyEvent;
import com.srd.ljzd.entity.monitor.News;
import com.srd.ljzd.entity.monitor.Penalty;
import com.srd.ljzd.entity.monitor.SocialSecurietyPay;
import com.srd.ljzd.entity.monitor.TaxplayerAbnormal;
import com.srd.ljzd.entity.monitor.TaxplayerInfo;
import com.srd.ljzd.entity.monitor.TradeMark;
import com.srd.ljzd.entity.monitor.ZhiJian;
import com.srd.ljzd.service.monitor.MonitorCompanyEventService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventMappingService;
import com.srd.ljzd.serviceImpl.company.CompanyInfoServiceImpl;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.ReadTxtUtils;
import com.srd.ljzd.util.StringUtils;
import com.srd.ljzd.util.StringUtils4Dev;

@Service
public class MonitorCompanyEventServiceImpl implements MonitorCompanyEventService {
	@Autowired
	MonitorCompanyEventDao monitorComEventDao;
	@Autowired
	MonitorCompanyEventMappingService monitorCompanyEventMappingService;
	protected static Logger logger = LogManager
			.getLogger(MonitorCompanyEventServiceImpl.class.getName());
	private String dataService = Global.getConfig("dataService");
	private String searchEventsDetails = Global
			.getConfig("searchEventsDetails");
	private String lawInfo = Global.getConfig("lawInfo");
	private String getMoneyPlaintiffDefendant = Global.getConfig("getMoneyPlaintiffDefendant");
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Override
	public Page<MonitorCompanyEvent> getMonitorEveList(
			MonitorComEventDTO monitorComEventDTO, int currentPageNo,
			int pageSize) {
		String tableName = monitorCompanyEventMappingService.getTableNameOfMonitorCompanyEvent(monitorComEventDTO.getCompanyName());
		if(tableName==null){
			return null;
		}
		Page<MonitorCompanyEvent> page = monitorComEventDao.getMonitorComEvent(
				monitorComEventDTO,tableName, currentPageNo, pageSize);

		return page;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List getMonitorComEveDetailsList(
			Map<String, MonitorCompanyEvent> paramEventsMap,
			String eventSubType, Boolean flag) {

		StringBuffer events = new StringBuffer();
		for (String eventId : paramEventsMap.keySet()) {
			events.append(eventId).append(",");
		}

		String url = dataService + searchEventsDetails;

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("keys", events.toString());
		paramMap.put("type", eventSubType);
		String key = null;
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);

		if (null != jsonResult && jsonResult.containsKey("respCode")) {
			if (jsonResult.getString("respCode").equals("200")
					&& jsonResult.containsKey("data")) {
				if (jsonResult.get("data") != null
						&& !jsonResult.get("data").equals("")
						&& !jsonResult.get("data").equals("[]")) {
					JSONArray array = jsonResult.getJSONArray("data");

					if (array.size() >= 1) {
						if ("01".equals(eventSubType)
								|| "02".equals(eventSubType)
								|| "03".equals(eventSubType)
								|| "05".equals(eventSubType)) {
							List<ChangedAnnouncement> changedList = new ArrayList<ChangedAnnouncement>();
							for (int i = 0; i < array.size(); i++) {
								ChangedAnnouncement change = new ChangedAnnouncement();

								change.setBefore(StringUtils4Dev
										.stringEmptyDeal(array
												.getJSONObject(i)
												.getString(
														"changedannouncement_before")));
								change.setAfter(StringUtils4Dev
										.stringEmptyDeal(array
												.getJSONObject(i)
												.getString(
														"changedannouncement_after")));
								change.setDate(StringUtils4Dev
										.stringEmptyDeal(array
												.getJSONObject(i)
												.getString(
														"changedannouncement_date")));
								change.setEvents(StringUtils4Dev
										.stringEmptyDeal(array
												.getJSONObject(i)
												.getString(
														"changedannouncement_events")));
								key = array.getJSONObject(i).getString("key");
								change.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								change.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								change.setHappenDate(paramEventsMap.get(key).getHappenDate());
								changedList.add(change);
							}
							return changedList;

						} else if ("04".equals(eventSubType)) {
							List<BusinessAbnormal> abnormalList = new ArrayList<BusinessAbnormal>();
							for (int i = 0; i < array.size(); i++) {
								BusinessAbnormal businessAbnormal = new BusinessAbnormal();
								// 做出决定机关
								businessAbnormal
										.setDecisioninstitution(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"abnormal_decisioninstitution")));
								// 序号，做出决定机关，列入经营异常原因 ，列入日期 ，移出经营异常原因 ，移出日期，事件等级
								businessAbnormal
										.setAbnormalReason(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"abnormal_events")));
								businessAbnormal
										.setInputDate(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"abnormal_datesin")));
								businessAbnormal
										.setOutputDate(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"abnormal_datesout")));
								businessAbnormal
										.setMoveoutReason(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"abnormal_moveoutreason")));
								key = array.getJSONObject(i).getString("key");
								businessAbnormal.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								businessAbnormal.setLevel(paramEventsMap.get(
										key).getEventLevel());
								businessAbnormal.setHappenDate(paramEventsMap.get(
										key).getHappenDate());
								abnormalList.add(businessAbnormal);
							}
							return abnormalList;

						} else if ("06".equals(eventSubType)) {
							// 经营状态变化

						} else if ("07".equals(eventSubType)) {
							// 对外投资
							List<InvestorInfo> investorInfoList = new ArrayList<InvestorInfo>();
							for (int i = 0; i < array.size(); i++) {

								InvestorInfo investorInfo = new InvestorInfo();
								// 投资企业名称 工商注册号 企业类型 企业状态 注册资本(万元) 出资比例 事件等级
								investorInfo.setEntname(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("entname")));
								investorInfo.setEntstatus(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("entstatus")));
								investorInfo.setEnttype(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("enttype")));
								investorInfo.setFundedratio(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("fundedratio")));
								investorInfo.setRegcap(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("regcap")));
								investorInfo.setRegno(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("regno")));
								key = array.getJSONObject(i).getString("key");
								investorInfo.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								investorInfo.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								investorInfo.setHappenDate(paramEventsMap.get(key)
										.getHappenDate());
								investorInfoList.add(investorInfo);

							}
							return investorInfoList;

						} else if ("08".equals(eventSubType)) {
							// 动产质押
							List<ChattelMortgage> chattelMortgageList = new ArrayList<ChattelMortgage>();
							for (int i = 0; i < array.size(); i++) {
								// 投资企业名称 工商注册号 企业类型 企业状态 注册资本(万元) 出资比例 事件等级
								ChattelMortgage chattelMortgage = new ChattelMortgage();
								chattelMortgage
										.setGuaranteeAmount(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"chattelmortgage_guaranteedamount")));
								chattelMortgage
										.setRegistrationDate(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"chattelmortgage_registrationdate")));
								chattelMortgage
										.setRegistrationNo(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"chattelmortgage_registrationno")));
								chattelMortgage
										.setReOrg(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"chattelmortgage_registrationinstitution")));
								chattelMortgage
										.setStatus(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"chattelmortgage_status")));
								key = array.getJSONObject(i).getString("key");
								chattelMortgage.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								chattelMortgage.setHappenDate(paramEventsMap
										.get(key).getHappenDate());
								chattelMortgage.setLevel(paramEventsMap
										.get(key).getEventLevel());
								chattelMortgageList.add(chattelMortgage);
							}
							return chattelMortgageList;

						} else if ("09".equals(eventSubType)) {
							// 股权出质
							List<EquityPledge> equityPledgeList = new ArrayList<EquityPledge>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 登记编号 出质人 证照/证件号码 出质股权数额 质权人 登记日期 公示日期 变化情况
								// 状态 事件等级
								EquityPledge equityPledge = new EquityPledge();
								// 出质股权数额
								equityPledge
										.setAmount(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_amount")));
								// 公示日期
								equityPledge
										.setAnnouncedate(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_announcedate")));
								// 变化情况
								if (!StringUtils.isNotEmpty(array
										.getJSONObject(i).getString(
												"equitypledge_change"))) {
									equityPledge.setChange("无");
								} else {
									equityPledge
											.setChange(StringUtils4Dev
													.stringEmptyDeal(array
															.getJSONObject(i)
															.getString(
																	"equitypledge_change")));
								}

								// 质权人
								equityPledge
										.setPawnee(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_pawnee")));
								// 出质人
								equityPledge
										.setPledgor(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_pledgor")));
								// 证照/证件号码
								equityPledge
										.setPledgorid(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_pledgorid")));
								// 登记日期
								equityPledge
										.setRegistrationDate(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_registrationdate")));
								// 登记编号
								equityPledge
										.setRegistrationNo(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_registrationno")));
								// 状态
								equityPledge
										.setStatus(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"equitypledge_status")));

								key = array.getJSONObject(i).getString("key");
								equityPledge.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								equityPledge.setHappenDate(paramEventsMap.get(key).getHappenDate());
								equityPledge.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								equityPledgeList.add(equityPledge);

							}
							return equityPledgeList;

						} else if ("10".equals(eventSubType)) {
							// 股权冻结 TODO
						} else if ("11".equals(eventSubType)) {
							// 工商行政处罚
							List<AdPunishment> adPunishmentList = new ArrayList<AdPunishment>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 登记编号 出质人 证照/证件号码 出质股权数额 质权人 登记日期 公示日期 变化情况
								// 状态 事件等级
								AdPunishment adPunishment = new AdPunishment();
								key = array.getJSONObject(i).getString("key");
								adPunishment.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								adPunishment.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								adPunishment.setHappenDate(paramEventsMap.get(key).getHappenDate());
								adPunishment.setPenalty_code(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("penalty_code")));
								adPunishment
										.setPenalty_decisioncontent(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"penalty_decisioncontent")));
								adPunishment
										.setPenalty_decisiondate(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"penalty_decisiondate")));
								adPunishment
										.setPenalty_decisioninsititution(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"penalty_decisioninsititution")));
								adPunishment
										.setPenalty_illegaltype(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"penalty_illegaltype")));
								adPunishmentList.add(adPunishment);
							}
							return adPunishmentList;

						} else if ("12".equals(eventSubType)) {
							// 食药监监查
							List<FoodDrug> foodDrugList = new ArrayList<FoodDrug>();
							for (int i = 0; i < array.size(); i++) {
								// 企业名称 时间，详情 事件等级
								FoodDrug foodDrug = new FoodDrug();
								key = array.getJSONObject(i).getString("key");
								foodDrug.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								foodDrug.setWebsite(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("website")));
								foodDrug.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								foodDrug.setHappenDate(paramEventsMap.get(key).getHappenDate());
								foodDrug.setDescription(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("description")));
								foodDrug.setEnterprisename(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("enterprisename")));
								foodDrug.setPublishtime(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("publishtime")));
								foodDrugList.add(foodDrug);
							}
							return foodDrugList;

						} else if ("13".equals(eventSubType)) {
							// 质量监督
							List<ZhiJian> zhiJianList = new ArrayList<ZhiJian>();
							for (int i = 0; i < array.size(); i++) {
								// 受检产品 商标 规格型号 批号/生产日期 生产企业（标称）受检企业 事件等级
								ZhiJian zhiJian = new ZhiJian();
								// 出质股权数额
								zhiJian.setCheckEnterprise(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("check_enterprise")));
								zhiJian.setGgxh(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("ggxh")));
								zhiJian.setProduct(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("product")));
								zhiJian.setProduct_time(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("product_time")));
								zhiJian.setProductEnterprise(StringUtils4Dev
										.stringEmptyDeal(array
												.getJSONObject(i)
												.getString("product_enterprise")));
								zhiJian.setTrademark(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("trademark")));
								key = array.getJSONObject(i).getString("key");
								zhiJian.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								zhiJian.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								zhiJian.setHappenDate(paramEventsMap.get(key).getHappenDate());
								zhiJianList.add(zhiJian);
							}
							return zhiJianList;
						} else if ("14".equals(eventSubType)) {
							// 社保缴纳
							List<SocialSecurietyPay> payList = new ArrayList<SocialSecurietyPay>();
							for (int i = 0; i < array.size(); i++) {
								// 企业名称 社保缴纳情况 发布时间 事件等级
								SocialSecurietyPay socialSecurietyPay = new SocialSecurietyPay();
								socialSecurietyPay
										.setEnterprisename(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"enterprisename")));
								socialSecurietyPay.setItems(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("items")));
								key = array.getJSONObject(i).getString("key");
								socialSecurietyPay.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								socialSecurietyPay.setLevel(paramEventsMap.get(
										key).getEventLevel());
								socialSecurietyPay.setHappenDate(paramEventsMap.get(
										key).getHappenDate());
								socialSecurietyPay.setTime(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("time")));
								payList.add(socialSecurietyPay);
							}
							return payList;
						}

						else if ("15".equals(eventSubType)) {
							// 环境核查
							List<HuanBao> huanBaoList = new ArrayList<HuanBao>();
							for (int i = 0; i < array.size(); i++) {
								// 被处罚人名称，违法行为类别，处罚内容，处罚决定时间，处罚单位 事件等级
								HuanBao huanBao = new HuanBao();
								huanBao.setEnterprisename(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("enterprisename")));
								huanBao.setDecide_time(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("decide_time")));
								huanBao.setPenalties(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("penalties")));
								huanBao.setPenalty_unit(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("penalty_unit")));
								huanBao.setType(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("type")));
								key = array.getJSONObject(i).getString("key");
								huanBao.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								huanBao.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								huanBao.setHappenDate(paramEventsMap.get(key).getHappenDate());
								huanBaoList.add(huanBao);
							}
							return huanBaoList;
						} else if ("16".equals(eventSubType)) {
							// 欠税信息
							List<TaxplayerInfo> taxplayerInfoList = new ArrayList<TaxplayerInfo>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 企业名称、税务类型、欠税于额、限缴日期 事件等级
								TaxplayerInfo taxplayerInfo = new TaxplayerInfo();
								taxplayerInfo.setQsje(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("qsje")));
								taxplayerInfo.setQssz(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("qssz")));
								taxplayerInfo.setQymc(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("qymc")));
								taxplayerInfo.setXjrq(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("ggrq")));
								key = array.getJSONObject(i).getString("key");
								taxplayerInfo.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								taxplayerInfo.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								taxplayerInfo.setHappenDate(paramEventsMap.get(key).getHappenDate());
								taxplayerInfoList.add(taxplayerInfo);
							}
							return taxplayerInfoList;
						} else if ("17".equals(eventSubType)) {
							// 纳税非正常户
							List<TaxplayerAbnormal> taxplayerAbnormalList = new ArrayList<TaxplayerAbnormal>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 纳税人名称、认定日期 事件等级
								// 序号 征管分局，纳税人识别码，企业名称，法定代表人(负责人)，非正常户认定日期 事件等级
								TaxplayerAbnormal taxplayerAbnormal = new TaxplayerAbnormal();
								taxplayerAbnormal.setQymc(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("qymc")));
								taxplayerAbnormal.setRdrq(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("rdrq")));
								taxplayerAbnormal.setFddbr(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("fddbr")));
								taxplayerAbnormal.setNsrsbm(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("nsrsbm")));
								taxplayerAbnormal.setZgfj(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("zgfj")));
								key = array.getJSONObject(i).getString("key");
								taxplayerAbnormal.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								taxplayerAbnormal.setLevel(paramEventsMap.get(
										key).getEventLevel());
								taxplayerAbnormal.setHappenDate(paramEventsMap.get(
										key).getHappenDate());
								taxplayerAbnormalList.add(taxplayerAbnormal);
							}
							return taxplayerAbnormalList;
						} else if ("18".equals(eventSubType)
								|| "19".equals(eventSubType)
								|| "20".equals(eventSubType)) {
							// 裁判文书（金融合同） 裁判文书 裁判文书（劳务纠纷）
							List<Penalty> penaltyList = new ArrayList<Penalty>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 判决时间 身份 详情 事件等级
								Penalty penalty = new Penalty();
								penalty.setDecisionDate(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("judgmenttime")));
								penalty.setDetails(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("content")));
								penalty.setName(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("companyname")));
								penalty.setShuxing(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("shuxing")));
								penalty.setJudgmentId(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("judgmentid")));
								penalty.setJudgmentTime(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("judgmenttime")));
								penalty.setTitle(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("title")));
								penalty.setInstrumenttype(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("instrumenttype")));
								key = array.getJSONObject(i).getString("key");
								penalty.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								penalty.setDocket(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("docket")));
								penalty.setCourtname(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("courtname")));
								penalty.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								penalty.setHappenDate(paramEventsMap.get(key).getHappenDate());
								penaltyList.add(penalty);

							}
							return penaltyList;
						} else if ("21".equals(eventSubType)) {
							// 被执行人
							List<ExecutedPerson> executedPersonList = new ArrayList<ExecutedPerson>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 案号 姓名/名称 执行标的 执行法院 案件状态 关注次数 事件等级
								ExecutedPerson executedPerson = new ExecutedPerson();
								executedPerson.setCaseCode(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("ah")));
								executedPerson.setCaseStatus(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("ajzt")));
								executedPerson.setCourtName(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("zxfy")));
								executedPerson.setFocusNum(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("gzcs")));
								executedPerson.setName(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("mc")));
								executedPerson.setRegisterTime(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("sj")));
								executedPerson.setZxbd(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("zxbd")));
								key = array.getJSONObject(i).getString("key");
								executedPerson.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								executedPerson.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								executedPerson.setHappenDate(paramEventsMap.get(key).getHappenDate());
								executedPersonList.add(executedPerson);

							}
							return executedPersonList;
						} else if ("22".equals(eventSubType)) {
							// 失信被执行信息
							List<BrokenPromiseInfo> brokenPromiseInfoList = new ArrayList<BrokenPromiseInfo>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 发布时间 案号 省份 执行法院 被执行人履行情况 详情 事件等级

								BrokenPromiseInfo brokenPromiseInfo = new BrokenPromiseInfo();

								brokenPromiseInfo.setAh(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("ah")));
								brokenPromiseInfo.setDm(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("dm")));

								brokenPromiseInfo.setFbsj(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("fbsj")));
								brokenPromiseInfo.setFddbr(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("fddbr")));

								brokenPromiseInfo.setLxqk(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("lxqk")));
								brokenPromiseInfo.setMc(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("mc")));

								brokenPromiseInfo.setSf(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("sf")));
								brokenPromiseInfo.setSj(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("sj")));
								brokenPromiseInfo.setSxjtqk(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("sxjtqk")));
								brokenPromiseInfo.setWsqdyw(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("wsqdyw")));
								brokenPromiseInfo.setZxfy(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("zxfy")));
								brokenPromiseInfo.setZxyjdw(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("zxyjdw")));
								brokenPromiseInfo.setZxyjwh(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("zxyjwh")));

								key = array.getJSONObject(i).getString("key");
								brokenPromiseInfo.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								brokenPromiseInfo.setLevel(paramEventsMap.get(
										key).getEventLevel());
								brokenPromiseInfo.setHappenDate(paramEventsMap.get(
										key).getHappenDate());
								brokenPromiseInfoList.add(brokenPromiseInfo);
							}
							return brokenPromiseInfoList;

						} else if ("23".equals(eventSubType)) {
							// 开庭公告
							List<CourtAnnouncement> courtAnnouncementList = new ArrayList<CourtAnnouncement>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 开庭日期 案由 当事人 执行法院 审理法庭 审理法官 事件等级
								CourtAnnouncement courtAnnouncement = new CourtAnnouncement();
								courtAnnouncement
										.setExecuteCourt(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"fa_yuan_ming_cheng")));
								courtAnnouncement.setAnHao(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("an_hao")));
								courtAnnouncement.setOrigin(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("an_you")));
								courtAnnouncement.setPrivy(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("dang_shi_ren")));
								courtAnnouncement.setTrialCourt(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("shen_li_fa_ting")));
								courtAnnouncement.setTrialDate(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("kai_ting_ri_qi")));
								courtAnnouncement
										.setTrialJudge(StringUtils4Dev
												.stringEmptyDeal(array
														.getJSONObject(i)
														.getString(
																"zhu_shen_fa_guan")));
								key = array.getJSONObject(i).getString("key");
								courtAnnouncement.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								courtAnnouncement.setLevel(paramEventsMap.get(
										key).getEventLevel());
								courtAnnouncement.setHappenDate(paramEventsMap.get(
										key).getHappenDate());
								courtAnnouncementList.add(courtAnnouncement);
							}
							return courtAnnouncementList;
						}else if ("24".equals(eventSubType) || "25".equals(eventSubType)) {
							// 新闻资讯 负面舆情
							List<News> newsList = new ArrayList<News>();
							
							Pattern pattern = null;
							Matcher matcher = null;
							
							News news = null;
							
							StringBuffer keyWordPattern = new StringBuffer("");
							
							String tempContent = "";
							
							String title = "";
							
							for(int i = 0; i < array.size(); i++){
								// 标题，时间，来源，详情 事件等级
								news = new News();
								
								title = StringUtils4Dev.stringEmptyDeal(array.getJSONObject(i).getString("title"));
								
								if (flag == true) {
									String SENSITIVEWORDS = Global.getConfig("constant_sensitive_words");
									String COMPETITORNAME = Global.getConfig("constant_competitor_name");
									
									Map<String, String[]> keyWordSources = (Map<String, String[]>) redisTemplate.opsForValue().get("keyWordSources");

									if (null == keyWordSources) {
										ReadTxtUtils readTxt = new ReadTxtUtils();
										keyWordSources = readTxt.readTxt();

										redisTemplate.opsForValue().set("keyWordSources",keyWordSources);
									}
									
									String content = StringUtils4Dev.stringEmptyDeal(array.getJSONObject(i).getString("content"));
									
									String companyName = array.getJSONObject(i).getString("companyid");

									StringBuffer newsContent = new StringBuffer();
									
									newsContent.append(content);
									
									if (!"--".equals(newsContent) && !"".equals(newsContent) && "" != companyName) {
										//隐藏舆情中竟对的名称
										keyWordPattern = new StringBuffer("(" + COMPETITORNAME + ")");
										pattern = Pattern.compile(keyWordPattern.toString());
										matcher = pattern.matcher(newsContent);
										tempContent = matcher.replaceAll("XXXXX");
										newsContent = new StringBuffer(tempContent);
										
										//隐藏标题中竟对的名称
										matcher = pattern.matcher(title);
										title = matcher.replaceAll("XXXXX");
										
										//高亮显示关键字
										keyWordPattern = new StringBuffer("(" + SENSITIVEWORDS + ")");
										pattern = Pattern.compile(keyWordPattern.toString());
										matcher = pattern.matcher(newsContent);
										tempContent = matcher.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
										newsContent = new StringBuffer(tempContent);
										
										//高亮企业名称
										keyWordPattern = new StringBuffer("("+companyName+")");
										pattern = Pattern.compile(keyWordPattern.toString());
										matcher = pattern.matcher(newsContent);
										tempContent = matcher.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
										newsContent = new StringBuffer(tempContent);
										
										//高亮显示股票代码和股票简称
										if(null != keyWordSources.get(companyName)){
											keyWordPattern = new StringBuffer("(");
											
											for (int j = 0; j < keyWordSources.get(companyName).length; j++) {
												if (!companyName.equals(keyWordSources.get(companyName)[j]) && null != keyWordSources.get(companyName)[j] && !"".equals(keyWordSources.get(companyName)[j].trim())) {
													keyWordPattern.append(keyWordSources.get(companyName)[j] + "|");
												}
											}
											
											//去掉最后的竖线
											keyWordPattern = new StringBuffer(keyWordPattern.toString().substring(0, keyWordPattern.toString().length()-1));
											
											keyWordPattern.append(")");
											
											pattern = Pattern.compile(keyWordPattern.toString());
											matcher = pattern.matcher(newsContent);
											tempContent = matcher.replaceAll("<span style=\"background:#d1edfe;\">$1</span>");
											newsContent = new StringBuffer(tempContent);
										}
									}
									
									news.setContent(newsContent);
								}

								key = array.getJSONObject(i).getString("key");
								news.setEventId(StringUtils4Dev.stringEmptyDeal(key));
								news.setLevel(paramEventsMap.get(key).getEventLevel());
								news.setHappenDate(paramEventsMap.get(key).getHappenDate());
								news.setPubtime(StringUtils4Dev.stringEmptyDeal(array.getJSONObject(i).getString("pubtime")));
								news.setSource(StringUtils4Dev.stringEmptyDeal(array.getJSONObject(i).getString("source")));
								news.setTitle(title);
								newsList.add(news);
							}
							
							return newsList;

						} else if ("27".equals(eventSubType)) {
							// 专利

						} else if ("28".equals(eventSubType)) {
							// 投招标

						} else if ("29".equals(eventSubType)) {
							// 商标
							List<TradeMark> tradeMarkList = new ArrayList<TradeMark>();
							for (int i = 0; i < array.size(); i++) {
								// 序号 开庭日期 案由 当事人 执行法院 审理法庭 审理法官 事件等级
								TradeMark tradeMark = new TradeMark();
								tradeMark.setApply_time(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("apply_time")));
								tradeMark.setBrand_name(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("brand_name")));
								tradeMark.setBrand_status(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("brand_status")));
								tradeMark.setCat_no(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("cat_no")));
								tradeMark.setImg_str(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("img_str")));
								tradeMark.setReg_no(StringUtils4Dev
										.stringEmptyDeal(array.getJSONObject(i)
												.getString("reg_no")));
								key = array.getJSONObject(i).getString("key");
								tradeMark.setEventId(StringUtils4Dev
										.stringEmptyDeal(key));
								tradeMark.setLevel(paramEventsMap.get(key)
										.getEventLevel());
								tradeMark.setHappenDate(paramEventsMap.get(key).getHappenDate());
								tradeMarkList.add(tradeMark);
							}
							return tradeMarkList;
						}

					}
				}
			} else {
				logger.error("搜索公司事件详情，大数据接口返回数据有误，respCode:"
						+ jsonResult.getString("respCode") + ", respMsg:"
						+ jsonResult.getString("respMsg"));
				logger.error("jsonResult" + jsonResult.toString());
			}
		} else {
			logger.error("搜索公司事件详情，大数据接口返回异常！");

			if (null != jsonResult) {
				logger.error("jsonResult" + jsonResult.toString());
			}
		}

		return null;
	}

	/**
	 * 
	 * @Title: getEveLevel
	 * @Description: TODO
	 * @param @param eveList
	 * @return void
	 * @author jiang.zhou
	 * @throws
	 * @date 下午3:35:03
	 */
	private String getEveLevel(List<MonitorCompanyEvent> eveList, String eventId) {

		String eveltLevel = null;
		for (int i = 0; i < eveList.size(); i++) {
			if (eventId.equals(eveList.get(i).getEventID())) {
				eveltLevel = eveList.get(i).getEventLevel();
				break;
			}
			continue;
		}
		return eveltLevel;
	}

	@Override
	public Penalty getMonitorComSfEveDetails(String judgmentId,
			String companyName) {
		String url = dataService + lawInfo;

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", judgmentId + "&judgidentifier");
		paramMap.put("companyname", companyName);
		paramMap.put(
				"columns",
				"judgidentifier:content,judgidentifier:title,judgidentifier:judgmenttime,judgidentifier:docket,judgidentifier:courtname,judgidentifier:instrumenttype");

		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);

		Penalty penalty = new Penalty();
		if (jsonResult != null && jsonResult.containsKey("respCode")) {
			if (jsonResult.getString("respCode").equals("200")
					&& jsonResult.containsKey("data")) {
				if (jsonResult.get("data") != null
						&& !jsonResult.get("data").equals("")
						&& !jsonResult.get("data").equals("[]")) {

					JSONArray array = jsonResult.getJSONArray("data");

					if (array.size() >= 1) {
						Pattern p = null;
						Pattern p1 = null;
						Matcher m = null;
						Matcher m1 = null;
						int lastStartIndex = -1;

						Map<String, String[]> keyWordSources = (Map<String, String[]>) redisTemplate
								.opsForValue().get("keyWordSources");

						if (null == keyWordSources) {
							ReadTxtUtils readTxt = new ReadTxtUtils();
							keyWordSources = readTxt.readTxt();

							redisTemplate.opsForValue().set("keyWordSources",
									keyWordSources);
						}

						String content = StringUtils4Dev.stringEmptyDeal(array
								.getJSONObject(0).getString(
										"judgidentifier:content"));

						StringBuffer newsContent = new StringBuffer();
						newsContent.append(content);
						// 在新闻舆情详情页面关键字高亮显示，供测试用
						if (!"--".equals(newsContent)) {
							String[] keyWords = { "裁定如下","判决如下" };

							for (int j = 0; j < keyWords.length; j++) {
								p = Pattern.compile(keyWords[j]);
								m = p.matcher(newsContent);
								while (m.find()) {
									newsContent = newsContent.replace(
											m.start(), m.end(),
											"<span style=\"background:#d1edfe;\">"
													+ keyWords[j] + "</span>");
								}
							}
						}
						penalty.setDetails(newsContent.toString());
						penalty.setTitle(array.getJSONObject(0).getString(
								"judgidentifier:title"));
						penalty.setJudgmentTime(array.getJSONObject(0)
								.getString("judgidentifier:judgmenttime"));
						// penalty.setDetails(array.getJSONObject(0).getString("judgidentifier:content"));
						penalty.setDocket(array.getJSONObject(0).getString(
								"judgidentifier:docket"));
						penalty.setInstrumenttype(array.getJSONObject(0)
								.getString("judgidentifier:instrumenttype"));
						penalty.setCourtname(array.getJSONObject(0).getString(
								"judgidentifier:courtname"));

					}
				}
			} else {
				logger.error("检索司法信息详情，大数据接口返回数据有误，respCode:"
						+ jsonResult.getString("respCode") + ", respMsg:"
						+ jsonResult.getString("respMsg"));
			}
		}

		return penalty;
	}

	@Override
	public Map<String,Object> queryUnReadEventCategoryNumList(String accountId, String companyId, String companyName, Date infoShowDate) {
		String tableName = monitorCompanyEventMappingService.getTableNameOfMonitorCompanyEvent(companyName);
		
		List<Map<String,Object>> list= monitorComEventDao.queryUnReadEventCategoryNumList(accountId, companyId, companyName, infoShowDate, tableName);
		
		Map<String,Object> cateGoryMap = new HashMap<String, Object>();
		
		for(Map<String,Object> record : list){
			cateGoryMap.put((String)record.get("eventSubType"), record.get("eventNum"));
		}
		
		return cateGoryMap;
	}

	@Override
	public List queryAllEventCategory(String companyName, Date infoShowDate) {
		String tableName = monitorCompanyEventMappingService.getTableNameOfMonitorCompanyEvent(companyName);
		
		return monitorComEventDao.queryEventCategory(companyName, infoShowDate, tableName);
	}
	
	@Override
	public List<String> getUnReadEventIdList(String accountId, String monitorId,String companyName,String infoShowDate,String eventSubType){
		String tableName = monitorCompanyEventMappingService.getTableNameOfMonitorCompanyEvent(companyName);
		
		return monitorComEventDao.getUnReadEventIdList(accountId, monitorId, companyName, infoShowDate, eventSubType, tableName);
	}
	
	@Override
	public Map<String, Object> getMoneyPlaintiffDefendant(String judgmentId,String companyName) {
		String url = dataService + getMoneyPlaintiffDefendant;

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("jId", judgmentId);
		//paramMap.put("companyname", companyName);

		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		if (jsonResult != null && jsonResult.containsKey("respCode")) {
			if (jsonResult.getString("respCode").equals("200")
					&& jsonResult.containsKey("data")) {
				if (jsonResult.get("data") != null
						&& !jsonResult.get("data").equals("")
						&& !jsonResult.get("data").equals("{}")) {
					
					JSONObject data = jsonResult.getJSONObject("data") ;
					
					String amount = data.getString("ws:money");
					String loser = data.getString("ws:loser");
					HashMap<String,Object> target = new HashMap<String, Object>();
					target.put("amount", formatAmount(amount));
					if(StringUtils.isEmpty(loser)){
						target.put("result", null);
					}else{
						if(loser.contains(companyName)){
							target.put("result", "败诉");
						}else{
							target.put("result", "胜诉");
						}
					}
					return target;
 				}
			}
		}
		
		return null;
	}
	private String formatAmount(String amountStr){
		if(StringUtils.isEmpty(amountStr)||"--".equals(amountStr)||"null".equals(amountStr)||"\\N".equals(amountStr)){
			return null;
		}
		try{
			Double lineDouble = 1.0d;
			Double amountDouble = Double.parseDouble(amountStr);
			if(Math.abs(amountDouble)>=lineDouble){
				return  new   BigDecimal(amountDouble).setScale(1,   BigDecimal.ROUND_HALF_UP).toString()+"万元";
				
			}else{
				return  new   BigDecimal(amountDouble*100000).setScale(1,   BigDecimal.ROUND_HALF_UP).toString()+"元";
			}
			
		}catch(Exception e){
			return null;
		}
	}
}
