/**  
 * 文件名: LawService.java
 * 包    名: com.srd.ljzd.service
 * 描    述: 司法信息的service
 * 作    者：曾长贵
 * 日    期： 2016年01月03日
 * 版    本： V2.0  
 */

package com.srd.ljzd.service.law;

import java.util.List;
import java.util.Map;
import com.srd.ljzd.dto.law.LawLegalInstrumentDTO;
import com.srd.ljzd.entity.law.LawAdaptor;
import com.srd.ljzd.entity.law.LawCourtAnnouncement;
import com.srd.ljzd.entity.law.LawDishonestInfo;
import com.srd.ljzd.entity.law.LawLegalInstrument;
import com.srd.ljzd.util.Page;

public interface LawService {

	/**
	 * @Description: 查询法院公告详情信息
	 * @param @param companyName
	 * @param @param courtID
	 * @author zengCG
	 * @date 2016年01月03日
	 */
	public LawCourtAnnouncement queryCourtAnnouncementListDetails(String companyName, String courtID);

	/**
	   * 构造分页对象，
	   * @param pageSize
	   * @param array
	   * @return  返回一个HashMap,
	   * 包含：pageSize,
	   */
	 public Map<String,Object> initPage(int curPage,int pageSize,List<? extends Object> array);

	/**
	 * @Description: 查询企业失信详细信息
	 * @param @param companyName
	 * @param @param shixinid
	 * @author zengCG
	 * @date 2016年01月03日
	 */
	public LawDishonestInfo queryDishonestInfoDetails(String shixinid,String companyName);

	/**
	 * @Title: queryLegalInstrument
	 * @Description: 根据公司名称和文书类型查询法律文书
	 * @param @param legalInstrumentDTO
	 * @param @return 设定文件
	 * @return List<LawLegalInstrument> 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年01月03日 
	 */
	public List<LawLegalInstrument> queryLegalInstrument(LawLegalInstrumentDTO legalInstrumentDTO);

	/**
	 * @Title: viewLegalInstrumentDetail
	 * @Description: 查看裁判文书详情
	 * @param @param companyName
	 * @param @param judgmentID
	 * @param @return 设定文件
	 * @return LawLegalInstrument 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年01月03日 
	 */
	public LawLegalInstrument viewLegalInstrumentDetail(String companyName,String judgmentID);

	/**
	 * @Title: findLegalInstrumentPage
	 * @Description: 获取司法文书分页数据
	 * @param @param legalInstrumentDTO
	 * @param @param currentPageNo
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return Page<LawLegalInstrument> 返回类型
	 * @throws
	 * @author shiyong
	 * @date 2016年5月21日 下午3:01:12
	 */
	public Page<LawLegalInstrument> findLegalInstrumentPage(LawLegalInstrumentDTO legalInstrumentDTO, int currentPageNo,int pageSize);

	/**
	 * @Title: queryLawInfo
	 * @Description: 查询公司司法信息
	 * @param @param companyName
	 * @param @return 设定文件
	 * @return CompanyAdaptor 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年01月03日 
	 */
	public LawAdaptor queryLawInfo(String companyName);
	
	
	public LawAdaptor getCachedLawMsg(String companyName);
	/**
	 * @Title: queryJudgmentInstrumentList
	 * @Description: 查询法院判决列表
	 * @param @param companyName
	 * @param @param currentPageNo
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return List<LawLegalInstrument> 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年01月03日 
	 */
	public List<LawLegalInstrument> queryJudgmentInstrumentList(String companyName, int currentPageNo, int pageSize);

	/**
	 * @Title: getLegalInstrumentNum
	 * @Description: 查询某个公司某种司法文书的数量
	 * @param @param companyName
	 * @param @param instrumentType
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 * @author zengCG
	 * @date 2016年01月03日 
	 */
	public int getLegalInstrumentNum(String companyName, String instrumentType);
	
	/** 
	* @Title: getKaiTingGongGaoNum 
	* @Description: 获取某家公司开庭公告的数量
	* @param @param companyName
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author zengCG
	* @date 2016年01月03日
	*/
	public int getKaiTingGongGaoNum(String companyName);
}
