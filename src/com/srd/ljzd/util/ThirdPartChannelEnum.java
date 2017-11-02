package com.srd.ljzd.util;

public enum ThirdPartChannelEnum {
   ZHONG_SHU("ZHONG_SHU"),QI_CHA_CHA("QI_CHA_CHA");
   
   private ThirdPartChannelEnum(String value){
	   this.value = value;
   }
   
   private String value;
   public String getValue() {
		return value;
   }
   public void setValue(String value) {
		this.value = value;
   }
	@Override
	public String toString() {
		return this.value;
	}
   
}
