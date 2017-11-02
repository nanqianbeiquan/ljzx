package com.srd.ljzd.util;

public enum ModuleEnum {
    BIAN_GENG_JI_LU("bianGengJiLu","变更记录","changedannouncement_date"),
    GU_DONG("guDong","股东信息","condate"),
    DONG_CHAN_DI_YA( "dongChanDiYa","动态抵押","chattelmortgage_registrationdate"),
    JING_YING_YI_CHANG("jingYingYiChang","经营异常","abnormal_datesin"),
    ADMINISTRATIVE_PENALTY("administrativePenalty","行政处罚","penalty_decisiondate"),
    BRAND("brand","商标","apply_time"),
    PERSONAL_DISHONEST("idCardDishonest","个人失信信息","fbsj"),
    PERSONAL_LEGALPERSON("idCardPerson","个人被执行人信息","sj");
	private String name;
	private String desp;
	private String sortAttr;
	
	private ModuleEnum(String name,String desp,String sortAttr){
		this.name = name;
		this.desp = desp;
		this.sortAttr = sortAttr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getSortAttr() {
		return sortAttr;
	}

	public void setSortAttr(String sortAttr) {
		this.sortAttr = sortAttr;
	}
	
	
}
