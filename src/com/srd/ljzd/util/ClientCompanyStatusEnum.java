package com.srd.ljzd.util;

public enum ClientCompanyStatusEnum {

	UNOPEN("0"),
	OPENED("1"),
	OVERDUE("2"),
	DISABLE("3");
	
	
	
	private String value;
	
	private ClientCompanyStatusEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
