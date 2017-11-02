package com.srd.ljzd.serviceImpl.info;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.info.InfoFeedbackDao;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.info.InfoFeedback;
import com.srd.ljzd.service.info.InfoFeedbackService;

@Service("infoFeedbackService")
public class InfoFeedbackServiceImpl implements InfoFeedbackService{
	protected static Logger log = LogManager.getLogger(InfoFeedbackServiceImpl.class.getName());
	
	@Autowired
	InfoFeedbackDao infoFeedbackDao;

	@Override
	public List<InfoFeedback> queryFeedbackList(String accountId) {
		return infoFeedbackDao.queryFeedbackList(accountId);
	}

	@Override
	public ResultBean saveFeedback(InfoFeedback feedBack) {
		ResultBean result = new ResultBean();
		
		feedBack.setCreateTime(new Date());
		feedBack.setLastModifiedTime(new Date());
		
		boolean flag = infoFeedbackDao.save(feedBack);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("保存意见反馈成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("保存意见反馈失败！");
		}
		
		return result;
	}
	
	

}
