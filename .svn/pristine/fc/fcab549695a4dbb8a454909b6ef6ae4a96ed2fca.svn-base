package com.srd.ljzd.daoImpl.company;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.company.CompanyBasicInfoDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.company.CompanyBasicInfo;
@Repository("companyBasicInfoDao")
public class CompanyBasicInfoDaoImpl extends BaseDaoImpl<CompanyBasicInfo, String> implements CompanyBasicInfoDao{

	@Override
	public CompanyBasicInfo queryByName(String companyName) {
		String hql = "select company from CompanyBasicInfo company where companyName=?";
		return super.getByHql(hql, companyName);
	}

}
