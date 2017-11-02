package com.srd.ljzd.entity.news;

import java.io.Serializable;


/**   
 * 版权所有：2017
 * 项目名称：ljzx   
 *
 * 类描述： 新闻舆情； 
 * 类名称：com.srd.ljzd.entity.news.NewsOpinion     
 * 创建人：裴子辉
 * 创建时间：2017年1月3日 上午9:48:08   
 * 修改人：
 * 修改时间：2017年1月3日 上午9:48:08   
 * 修改备注：   
 * @version   2.0    
 */
  
public class NewsOpinion implements Serializable{
	private static final long serialVersionUID = 1701966387737760049L;
	private String title;//舆情标题
	private String pubtime;//舆情时间
	private String content;//新闻舆情内容
	private String newsId;//新闻舆情ID
	private String source;//新闻舆情来源
	private String opinionType; // 0 媒体咨询；1 负面舆情；
	private String opinionKey;// 查看详情Key;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOpinionType() {
		return opinionType;
	}
	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}
	public String getOpinionKey() {
		return opinionKey;
	}
	public void setOpinionKey(String opinionKey) {
		this.opinionKey = opinionKey;
	}
	
	
	
	
}
