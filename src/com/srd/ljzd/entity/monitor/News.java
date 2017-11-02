package com.srd.ljzd.entity.monitor;

import java.io.Serializable;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：新闻资讯 负面舆情
 * 类名称：com.srd.ljzd.entity.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年12月5日下午5:00:04
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public class News extends BaseDimension implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 标题
	 */
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubtime() {
		return pubtime;
	}

	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 时间
	 */
	private String pubtime;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 详情
	 */
	private StringBuffer content;
	
	/**
	 * 事件等级
	 */
	private String level;
	
}
