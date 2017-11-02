package com.srd.ljzd.dao.info;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.info.InfoFeedback;

/** 
* @ClassName: InfoFeedbackDao
* @Description: 意见反馈Dao
* @author shiyong
* @date 2016年11月22日 上午11:15:28
*  
*/
public interface InfoFeedbackDao extends BaseDao<InfoFeedback,String> {

	
	/** 
	* @Title: queryFeedbackList 
	* @Description: 根据账号ID获取反馈列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<InfoFeedback> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月22日 上午11:15:45
	*/
	public List<InfoFeedback> queryFeedbackList(String accountId);

}


