/**  
* 文件名: Page.java
* 包    名: com.srd.ljzd.util
* 描    述: 页封装
* 作    者： 谭显伦 
* 日    期： 2016年4月6日
* 版    本： V1.0  
*/

package com.srd.ljzd.util;

/**
 * 类名: Page
 * 描述: 页封装
 * 作者： 谭显伦
 * 日期： 2016年4月6日
 */

import java.util.List;


public class Page<T> {
	public Page(){
		super();
	}
	public Long getRecordSum() {
		return recordSum;
	}

	public void setRecordSum(Long recordSum) {
		this.recordSum = recordSum;
	}

	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	// 上一页数
	private int prePageNo;

	// 下一页数
	private int nextPageNo;

	// 当前页数
	private int currentPageNo;

	// 每页记录数
	private int pageSize;

	// 总记录条数
	private Long recordSum;

	// 总页数
	private int pageSum;
	
	//开始
	private int beginIndex;

	// 是否有上一页
	private boolean hasPrePage = true;

	// 是否有下一页
	private boolean hasNextPage = true;

	// 当前页记录列表
	private List<T> results;

	public Page(int everyPage, Long recordSum, int pageSum, int currentPageNo,
			int beginIndex, boolean hasPrePage, boolean hasNextPage) {
		this.pageSize = everyPage;
		this.recordSum = recordSum;
		this.pageSum = pageSum;
		this.currentPageNo = currentPageNo;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
		if(this.hasNextPage){
			this.nextPageNo = this.currentPageNo+1;
		}
		if(this.hasPrePage){
			this.prePageNo = this.currentPageNo-1;
		}
	}
	
	public int getPrePageNo() {
		return prePageNo;
	}

	public void setPrePageNo(int prePageNo) {
		this.prePageNo = prePageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public void resetPageNo() {
		this.nextPageNo = currentPageNo + 1;
		pageSum = recordSum.intValue() % pageSize == 0 ? recordSum.intValue() / pageSize
				: recordSum.intValue() / pageSize + 1;
	}
}
