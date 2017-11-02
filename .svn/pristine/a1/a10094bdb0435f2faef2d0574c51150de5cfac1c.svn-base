/**  
 * 文件名: PageUtil.java
 * 包    名: com.srd.ljzd.util
 * 描    述: TODO(用一句话描述该文件做什么)
 * 作    者： 谭显伦 
 * 日    期： 2016年4月5日
 * 版    本： V1.0  
 */
package com.srd.ljzd.util;

public class PageUtil<T>{
	
	public  Page createPage(int pageSize,Long totalCount,int currentPageNo) {
		int size = getPageSize(pageSize);
		
		int totalPageNo = getTotalPageNo(pageSize, totalCount);
		
		int current = getCurrentPageNo(currentPageNo,totalPageNo);
		
		int beginIndex = getBeginIndex(pageSize, currentPageNo);
		boolean hasPrePage = isHasPrePage(currentPageNo);
		boolean hasNextPage = isHasNextPage(totalPageNo, currentPageNo);
		return new Page<T>(size, totalCount, totalPageNo, current,
				beginIndex, hasPrePage, hasNextPage);

	}
	
	/**
	* 方法名称: getPageSize
	* 描       述: 根据输入的记录条数，判断每页显示的条数是否合法；如不合法，返回10条
	* 参       数： @param pageSize:输入记录条数
	* 返回类型：输入值小于0，返回10条；否则按输入值返回
	*/
	public  int getPageSize(int pageSize) {
		return pageSize<= 0 ? 10 : pageSize;
	}
	
	/**
	* 方法名称: getCurrentPageNo
	* 描       述: 根据每页显示的记录条数和总记录条数，获取总页数
	* 参       数： @param currentPageNo:当前页数
	* 返回类型：当前页数
	*/
	public  int getCurrentPageNo(int currentPageNo,int totalPageNo) {
		if(currentPageNo>totalPageNo){
			return totalPageNo;
		}
		return currentPageNo <= 0 ? 1 : currentPageNo;
	}
	
	/**
	* 方法名称: getTotalPageNo
	* 描       述: 根据每页显示的记录条数和总记录条数，获取总页数
	* 参       数： @param pageSize:每页记录数；@param totalCount：记录总条数
	* 返回类型：总页数
	*/
	public int getTotalPageNo(int pageSize,Long totalCount) {
		int totalPageNo = 0;
		if(totalCount % pageSize == 0) {
			totalPageNo = totalCount.intValue() /pageSize;
		} else {
			totalPageNo = totalCount.intValue() / pageSize + 1;
		}
		return totalPageNo;
	}
	
	/**
	* 方法名称: getBeginIndex
	* 描       述: 根据每页显示的记录条数和当前页数，获取开始记录数
	* 参       数： @param pageSize:每页记录数；@param currentPageNo：当前页数
	* 返回类型：当前页开始记录数
	*/
	public  int getBeginIndex(int pageSize,int currentPageNo) {
		return (currentPageNo - 1) * pageSize;
	}
	
	/**
	* 方法名称: isHasPrePage
	* 描       述: 判断是否有前一页
	* 参       数： @param currentPageNo：当前页数
	* 返回类型：true：有前一页；false：没有前一页
	*/
	public  boolean isHasPrePage(int currentPageNo) {
		return currentPageNo == 1 ? false : true;
	}
	
	/**
	* 方法名称: isHasNextPage
	* 描       述: 判断是否有下一页
	* 参       数： @param totalPageNo:总页数；@param currentPageNo：当前页数
	* 返回类型：true：有下一页；false：没有下一页
	*/
	public  boolean isHasNextPage(int totalPageNo, int currentPageNo) {
		return currentPageNo >= totalPageNo || totalPageNo == 0 ? false : true;
	}	
}