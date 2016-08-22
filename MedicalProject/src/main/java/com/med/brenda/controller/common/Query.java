package com.med.brenda.controller.common;

import java.io.Serializable;

public class Query  implements Serializable{
	private static final long serialVersionUID = 6617342065316712632L;
    //页码
    private int pageIndex = 1;
    // 每页多少条
    private int pageSize = 20;
    private int pageNo = 1;
    public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	//总页数
    private int pageCount;
    private int total;//总条数
    //搜索关键字
    private String keywords;
    

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
    
}
