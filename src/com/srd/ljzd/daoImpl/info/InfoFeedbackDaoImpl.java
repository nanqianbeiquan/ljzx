package com.srd.ljzd.daoImpl.info;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.info.InfoFeedbackDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.info.InfoFeedback;

/** 
* @ClassName: InfoFeedbackDaoImpl
* @Description: 意见反馈dao实现类
* @author shiyong
* @date 2016年11月22日 上午11:19:13
*  
*/
@Repository("infoFeedbackDao")
public class InfoFeedbackDaoImpl extends BaseDaoImpl<InfoFeedback, String> implements InfoFeedbackDao {

	@Override
	public List<InfoFeedback> queryFeedbackList(String accountId) {
		
		String hql = "from InfoFeedback where accountID = '" + accountId + "' order by createTime desc";
		
		List<InfoFeedback> feedbackList = getListByHql(hql);
		
		return feedbackList;
	}
	
	
}
