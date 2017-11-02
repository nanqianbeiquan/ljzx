package com.srd.ljzd.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String errorCode;
	private String description;
	private Exception parent;
	private Class<? extends Object> source;
	
	
	public Class<? extends Object> getSource() {
		return source;
	}
	public void setSource(Class<? extends Object> source) {
		this.source = source;
	}
	public BusinessException(String errorCode, String description,
			Exception parent, Class<? extends Object> source) {
		super();
		this.errorCode = errorCode;
		this.description = description;
		this.parent = parent;
		this.source = source;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Exception getParent() {
		return parent;
	}
	public void setParent(Exception parent) {
		this.parent = parent;
	}
	
	

}
