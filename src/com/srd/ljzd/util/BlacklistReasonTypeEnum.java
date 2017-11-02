package com.srd.ljzd.util;

public enum BlacklistReasonTypeEnum {
   ADD("1"),REMOVE("2");
   
   private BlacklistReasonTypeEnum(String value){
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
