/**  
 * 文件名: LawController.java
 * 包    名: com.srd.ljzd.controller
 * 描    述: TODO(用一句话描述该文件做什么)
 * 作    者： zengCG
 * 日    期： 2016年12月29日
 * 版    本： V2.0  
 */

package com.srd.ljzd.controller.law;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.srd.ljzd.dto.law.LawLegalInstrumentDTO;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.law.LawAdaptor;
import com.srd.ljzd.entity.law.LawCourtAnnouncement;
import com.srd.ljzd.entity.law.LawDishonestInfo;
import com.srd.ljzd.entity.law.LawLegalInstrument;
import com.srd.ljzd.service.law.LawService;
import com.srd.ljzd.util.Page;

@Controller
@RequestMapping("/law")
public class LawController {
	
	@Autowired
	private LawService lawService;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	protected static Logger logger = LogManager.getLogger(LawController.class.getName());

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @Title: toLegalInstrumentList
	 * @Description: 跳转到公司司法文书查询页面
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月29日 
	 */
	@RequestMapping("/toLegalInstrumentList")
	public String toLegalInstrumentList(HttpServletRequest request,HttpServletResponse response, Model model) {
		String companyName = request.getParameter("companyName");
		model.addAttribute("companyName", companyName);
		
		String currentDeep = request.getParameter("currentDeep");
		model.addAttribute("currentDeep", currentDeep);
		
		return "modules/company/detail/law/lawDetails/legalInstrumentList";
	}
	
	/**
	 * @Title: toLawList
	 * @Description: 跳转到司法信息页面
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月29日 
	 */
	@RequestMapping("/toLawList")
	public String toLawList(HttpServletRequest request,HttpServletResponse response, Model model) {
		String companyName = request.getParameter("companyName");
		
		LawAdaptor LawInfo = lawService.queryLawInfo(companyName);
		request.setAttribute("LawInfo", LawInfo);
		
		Map<String,Object>	LegalInstrumentPage = lawService.initPage(1,6, LawInfo.getJudgmentInstrumentList());
		request.setAttribute("LegalInstrumentPage", LegalInstrumentPage);
		
		Map<String, Object> gongGaoPage = lawService.initPage(1,6, LawInfo.getCourtAnnouncementList());
		request.setAttribute("gongGaoPage", gongGaoPage);
		
		Map<String, Object> shiXinPage  = lawService.initPage(1,6, LawInfo.getDishonestInfoList());
		request.setAttribute("shiXinPage", shiXinPage);
		
		
		Map<String, Object> personPage = lawService.initPage(1,6, LawInfo.getExecutedPersonList());
		request.setAttribute("personPage", personPage);
		
		Map<String, Object> kaiTingGongGaoPage = lawService.initPage(1,6, LawInfo.getKaiTingGongGaoList());
		request.setAttribute("kaiTingGongGaoPage", kaiTingGongGaoPage);
		
		return "modules/company/detail/law/lawDetails";
	}
	
	/**
	 * @Title: queryLegalInstrumentList
	 * @Description: 查询公司司法文书
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return ResultBean 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月29日 
	 */
	@RequestMapping("/queryLegalInstrumentList")
	@ResponseBody
	public ResultBean queryLegalInstrumentList(HttpServletRequest request, HttpServletResponse response) {
		ResultBean result = new ResultBean();
		String currentDeep=request.getParameter("currentDeep");
		if(null!=currentDeep){
			request.setAttribute("currentDeep", currentDeep);
		}
		String companyName = request.getParameter("companyName");
		String instrumentTypes = request.getParameter("instrumentTypes");
		String trialClasses = request.getParameter("trialClasses");
		String litigationTypes = request.getParameter("litigationTypes");
		String relatedPositions = request.getParameter("relatedPositions");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String minAmountCount = request.getParameter("minAmountCount");
		String maxAmountCount = request.getParameter("maxAmountCount");

		LawLegalInstrumentDTO legalInstrumentDTO = new LawLegalInstrumentDTO();

		if (StringUtils.isNotEmpty(companyName)) {
			legalInstrumentDTO.setCompanyName(companyName);
		}

		if (StringUtils.isNotEmpty(instrumentTypes)) {
			legalInstrumentDTO.setInstrumentTypes(instrumentTypes);
		}

		if (StringUtils.isNotEmpty(trialClasses)) {
			legalInstrumentDTO.setTrialClasses(trialClasses);
		}

		if (StringUtils.isNotEmpty(litigationTypes)) {
			legalInstrumentDTO.setLitigationTypes(litigationTypes);
		}

		if (StringUtils.isNotEmpty(relatedPositions)) {
			String[] relatedPositionArray = relatedPositions.split(",");
			
			StringBuffer newRelatedPositions = new StringBuffer("");
			
			for(int i=0;i<relatedPositionArray.length;i++){
				if("原告".equals(relatedPositionArray[i])){
					newRelatedPositions.append("一审原告,原告,原告人,原审原告,上诉人,");
				}else if("被告".equals(relatedPositionArray[i])){
					newRelatedPositions.append("被告,被告人,一审被告,原审被告,原审本诉被告,原审反诉被告,被上诉人,");
				}else if("第三人".equals(relatedPositionArray[i])){
					newRelatedPositions.append("第三人,原审第三人,");
				}else{
					newRelatedPositions.append("案外人,被罚款人,被申请人,被执行人,辩护人,代表人,代理人,担保人,法定代表人,法定代理人,负责人,公诉机关,");
					newRelatedPositions.append("监护人,经营者,抗诉机关,利害关系人,赔偿请求人,赔偿申请人,赔偿义务机关,起诉人,申报人,申请复议人,申请人,申请再审人,");
					newRelatedPositions.append("申请执行人,申诉人,诉讼代表人,投资人,委托代理,委托代理人,协助执行人,异议人,原公诉机关,原审被申请人,原审申请人,");
					newRelatedPositions.append("原审自诉人,再审申请人,债权人,罪犯,被起诉人,");
				}
			}
			
			legalInstrumentDTO.setRelatedPositions(newRelatedPositions.subSequence(0, newRelatedPositions.length()-1).toString());
		}
		
		if (StringUtils.isNotEmpty(beginDate)) {
			legalInstrumentDTO.setBeginDate(beginDate);
		}
		
		if (StringUtils.isNotEmpty(endDate)) {
			legalInstrumentDTO.setEndDate(endDate);
		}
		
		if (StringUtils.isNotEmpty(minAmountCount)) {
			legalInstrumentDTO.setMinAmountCount(minAmountCount);
		}
		
		if (StringUtils.isNotEmpty(maxAmountCount)) {
			legalInstrumentDTO.setMaxAmountCount(maxAmountCount);
		}

		int currentPageNo = 1;
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		
		int pageSize = 6;
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<LawLegalInstrument> page = lawService.findLegalInstrumentPage(legalInstrumentDTO, currentPageNo, pageSize);
		
		result.setResultData(page);
		request.setAttribute("companyName", companyName);
		return result;
	}
	
	/**
	 * @Title: LegalInstrumentPage
	 * @Description: 裁判文书的分页
	 * @param @param request
	 * @param @param model 
	 * @param @param curPage 当前页 companyName 公司名称
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月30日 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/LegalInstrumentPage")
	public String LegalInstrumentPage(Integer curPage,HttpServletRequest request, Model model) {
		String companyName = request.getParameter("companyName");
		Map<String, Object> LegalInstrumentPage = (Map<String, Object>) request.getSession().getAttribute("LegalInstrumentPage");
		
		LawAdaptor lawInfo=lawService.getCachedLawMsg(companyName);
		
		if (LegalInstrumentPage == null) {
			LegalInstrumentPage = lawService.initPage(curPage,6, lawInfo.getJudgmentInstrumentList());
		} else {
			LegalInstrumentPage.put("curPage", curPage);
		}
		request.getSession().setAttribute("LegalInstrumentPage", LegalInstrumentPage);
		request.setAttribute("LawInfo", lawInfo);
		return "/modules/company/detail/law/lawDetails/judgmentInstrument";
	}
	
	/**
	 * @Title: personNextPage
	 * @Description: 被执行人的分页
	 * @param @param request
	 * @param @param model 
	 * @param @param curPage 当前页 companyName 公司名称
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月30日 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/personNextPage")
	public String personNextPage(Integer curPage,HttpServletRequest request, Model model) {
		String companyName = request.getParameter("companyName");
		Map<String, Object> personPage = (Map<String, Object>) request.getSession().getAttribute("personPage");
		
		LawAdaptor lawInfo=lawService.getCachedLawMsg(companyName);
		
		if (personPage == null) {
			personPage = lawService.initPage(curPage,6, lawInfo.getExecutedPersonList());
		} else {
			personPage.put("curPage", curPage);
		}
		request.getSession().setAttribute("personPage", personPage);
		request.setAttribute("LawInfo", lawInfo);
		return "/modules/company/detail/law/lawDetails/personSubjectToEnforcement";
	}
	
	/**
	 * @Title: shiXinPage
	 * @Description: 失信信息的分页
	 * @param @param request
	 * @param @param model 
	 * @param @param curPage 当前页 companyName 公司名称
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月30日 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/shiXinNextPage")
	public String shiXinPage(Integer curPage,HttpServletRequest request, Model model) {
		String companyName = request.getParameter("companyName");
		Map<String, Object> shiXinPage = (Map<String, Object>) request.getSession().getAttribute("shiXinPage");
		
		LawAdaptor lawInfo=lawService.getCachedLawMsg(companyName);
	
		if (shiXinPage == null) {
			shiXinPage = lawService.initPage(curPage,6,lawInfo.getDishonestInfoList());
		} else {
			shiXinPage.put("curPage", curPage);
		}
		request.getSession().setAttribute("shiXinPage", shiXinPage);
		request.setAttribute("LawInfo", lawInfo);
		return "/modules/company/detail/law/lawDetails/dishonestInfo";
	}
	
	/**
	 * @Title: kaiTingGongGaoPage
	 * @Description: 开庭公告的分页
	 * @param @param request
	 * @param @param model 
	 * @param @param curPage 当前页 companyName 公司名称
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月30日 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/kaiTingGongGaoNextPage")
	public String kaiTingGongGaoPage(Integer curPage,HttpServletRequest request, Model model) {
		String companyName = request.getParameter("companyName");
		Map<String, Object> kaiTingGongGaoPage = (Map<String, Object>) request.getSession().getAttribute("kaiTingGongGaoPage");
		
		LawAdaptor lawInfo=lawService.getCachedLawMsg(companyName);
		
		if (kaiTingGongGaoPage == null) {
			kaiTingGongGaoPage = lawService.initPage(curPage,6, lawInfo.getKaiTingGongGaoList());
		} else {
			kaiTingGongGaoPage.put("curPage", curPage);
		}
		request.getSession().setAttribute("kaiTingGongGaoPage", kaiTingGongGaoPage);
		request.setAttribute("LawInfo", lawInfo);
		return "/modules/company/detail/law/lawDetails/noticeOfTrail";
	}
	
	/**
	 * @Title: gongGaoPage
	 * @Description: 法院公告的分页
	 * @param @param request
	 * @param @param model 
	 * @param @param curPage 当前页 companyName 公司名称
	 * @return String 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月30日 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/gongGaoNextPage")
	public String gongGaoPage(Integer curPage,HttpServletRequest request, Model model) {
		String companyName = request.getParameter("companyName");
		
		LawAdaptor lawInfo=lawService.getCachedLawMsg(companyName);
		
		Map<String, Object> gongGaoPage = (Map<String, Object>) request.getSession().getAttribute("gongGaoPage");
		
		if (gongGaoPage == null) {
			
			gongGaoPage = lawService.initPage(curPage,6, lawInfo.getCourtAnnouncementList());
			
		} else {
			gongGaoPage.put("curPage", curPage);
		}
		request.getSession().setAttribute("gongGaoPage", gongGaoPage);
		request.setAttribute("LawInfo", lawInfo);
		return "/modules/company/detail/law/lawDetails/courtAnnouncement";
	}
	
	/**
	 * @Title: viewLegalInstrumentDetail
	 * @Description: 查看裁判文书详情
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月29日 
	 */
	@ResponseBody
	@RequestMapping("/viewLegalInstrumentDetail")
	public Object viewLegalInstrumentDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
		String companyName = request.getParameter("companyName");
		String judgmentId = request.getParameter("judgmentId");

		LawLegalInstrument legalInstrument = lawService.viewLegalInstrumentDetail(companyName, judgmentId);

		return legalInstrument;
	}
	
	/**
	 * @Title: getDishonestInfoDetails
	 * @Description: 失信信息的详细信息
	 * @param @param request
	 * @param @param response 设定文件
	 * @param @param caseNo 案号 companyName 公司名称
	 * @return Object 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年1月3日 
	 */
	@ResponseBody
	@RequestMapping("/getDishonestInfoDetails")
	public Object getDishonestInfoDetails(HttpServletRequest request,HttpServletResponse response, Model model) {
		String companyName = request.getParameter("companyName");
		String caseNo = request.getParameter("caseNo");
		
		LawDishonestInfo dishonestInfo = lawService.queryDishonestInfoDetails(caseNo, companyName);
		
		return dishonestInfo;
	}
	
	/**
	 * @Title: getCourtDetails
	 * @Description: 法院公告的详细信息
	 * @param @param request
	 * @param @param response 设定文件
	 * @param @param courtID 法院ID companyName 公司名称
	 * @return Object 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年01月09日 
	 */
	@ResponseBody
	@RequestMapping("/getCourtDetails")
	public Object getCourtDetails(HttpServletRequest request,HttpServletResponse response, Model model) {
		String companyName = request.getParameter("companyName");
		String courtId = request.getParameter("courtId");
		
		LawCourtAnnouncement courtAnnouncement = lawService.queryCourtAnnouncementListDetails(companyName, courtId);
		
		return courtAnnouncement;
	}
	
	/**
	 * @Title: exportLegalInstrumentList
	 * @Description: 导出司法文书列表
	 * @param @param request
	 * @param @param response 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年12月29日 
	 */
	@RequestMapping("/exportLegalInstrumentList")
	public void exportLegalInstrumentList(HttpServletRequest request,HttpServletResponse response) {
		String companyName = request.getParameter("companyName");

		LawLegalInstrumentDTO legalInstrumentDTO = new LawLegalInstrumentDTO();

		if (StringUtils.isNotEmpty(companyName)) {
			legalInstrumentDTO.setCompanyName(companyName);
		}

		List<LawLegalInstrument> legalInstrumentList = lawService.queryLegalInstrument(legalInstrumentDTO);

		XSSFWorkbook workbook = new XSSFWorkbook();// 创建工作薄

		XSSFSheet spreadsheet = workbook.createSheet("司法文书");// 创建电子表格

		XSSFRow row = spreadsheet.createRow(0);// 创建行
		row.createCell(0).setCellValue("判决书时间");
		row.createCell(1).setCellValue("案号");
		row.createCell(2).setCellValue("当事人地位");
		row.createCell(3).setCellValue("案由");
		row.createCell(4).setCellValue("文书类型");
		row.createCell(5).setCellValue("诉讼类型");
		row.createCell(6).setCellValue("审级");
		row.createCell(7).setCellValue("判决金额");

		for (int i = 0; i < legalInstrumentList.size(); i++) {
			row = spreadsheet.createRow(i + 1);// 创建行
			row.createCell(0).setCellValue(legalInstrumentList.get(i).getJudgmentTime());
			row.createCell(1).setCellValue(legalInstrumentList.get(i).getCaseNo());
			row.createCell(2).setCellValue(legalInstrumentList.get(i).getRelatedPosition());
			row.createCell(3).setCellValue(legalInstrumentList.get(i).getCauseAction());
			row.createCell(4).setCellValue(legalInstrumentList.get(i).getInstrumentType());
			row.createCell(5).setCellValue(legalInstrumentList.get(i).getLitigationType());
			row.createCell(6).setCellValue(legalInstrumentList.get(i).getTrialClass());
			row.createCell(7).setCellValue(legalInstrumentList.get(i).getAmountCount());
		}

		try {
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(companyName + "法律文书.xlsx", "UTF-8"));

			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
