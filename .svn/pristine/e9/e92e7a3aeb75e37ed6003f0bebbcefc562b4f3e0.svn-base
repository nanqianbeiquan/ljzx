package com.srd.ljzd.serviceImpl.company;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.company.CompanyBasicInfoDao;
import com.srd.ljzd.entity.company.CompanyBasicInfo;
import com.srd.ljzd.service.company.CompanyBasicInfoService;
import com.srd.ljzd.util.StringUtils;
@Service("companyBasicInfoService")
public class CompanyBasicInfoServiceImpl implements CompanyBasicInfoService {
	@Autowired
	CompanyBasicInfoDao companyBasicInfoDao;
	
	protected static Logger logger = LogManager.getLogger(CompanyBasicInfoServiceImpl.class.getName());
	@Override
	public CompanyBasicInfo add(String companyName, String areaName,
			Integer eventMapping) {
		if(StringUtils.isNotEmpty(companyName)&&StringUtils.isNotEmpty(areaName)){
			CompanyBasicInfo company = new CompanyBasicInfo();
			company.setCompanyName(companyName);
			company.setAreaName(areaName);
			if("工商总局".equals(areaName)){
				company.setProvince("北京市");
			}else{
				company.setProvince(areaName);
			}
			company.setCreateTime(Calendar.getInstance().getTime());
			company.setEventMapping(eventMapping==null?0:eventMapping);
			
			boolean flag = companyBasicInfoDao.save(company);
			if(flag){
				return company;
			}
		}
		return null;
	}

	@Override
	public CompanyBasicInfo queryByName(String companyName) {
		
		return companyBasicInfoDao.queryByName(companyName);
	}

}
