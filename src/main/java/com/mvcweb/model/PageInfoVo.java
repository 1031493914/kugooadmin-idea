package com.mvcweb.model;

public class PageInfoVo {

	private int totalSize; //总记录数
	private int totalPage;  //总页码
	private int pagesize = 10;  //每页大小,默认10
	private int currentPage;  //当前页
	private String orderBy; //排序方式 为空则表示不进行排序
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
		setTotalpage();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		setTotalpage();
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public void setTotalpage() {
		if (this.totalSize % this.pagesize != 0) {
			this.totalPage = this.totalSize / this.pagesize + 1;
		}

		else {
			this.totalPage = this.totalSize / this.pagesize;
		}
		if(totalPage == 0) totalPage = 1;
	}
	
}
