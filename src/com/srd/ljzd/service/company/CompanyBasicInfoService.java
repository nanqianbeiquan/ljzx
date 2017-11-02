package com.srd.ljzd.service.company;

import com.srd.ljzd.entity.company.CompanyBasicInfo;

public interface CompanyBasicInfoService {
	
  public CompanyBasicInfo add(String companyName,String areaName,Integer eventMapping);
  
  public CompanyBasicInfo queryByName(String companyName);
}
