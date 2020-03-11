package com.mvcweb.model;


import com.mvcweb.base.Constants;

public class Page {

	private int totalSize; //总记录数
	private int totalPage;  //总页码
	private int pageSize;  //每页大小
	private int currentPage;  //当前页
	
	public Page(){
		totalSize = 0;
		totalPage = 1;
		pageSize = Constants.PAGE_DEFAULT_SIZE;
		currentPage = 1;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
		setTotalPage();
	}
	
	public void setTotalPage() {
		if (this.totalSize % this.pageSize != 0) {
			this.totalPage = this.totalSize / this.pageSize + 1;
		}else {
			this.totalPage = this.totalSize / this.pageSize;
		}
		if(totalPage == 0) totalPage = 1;
	}
	
	@Override
	public String toString() {
		StringBuilder v = new StringBuilder();
		v.append("{");
		v.append("totalSize: ").append(totalSize);
		v.append(", totalPage: ").append(totalPage);
		v.append(", pageSize: ").append(pageSize);
		v.append(", currentPage: ").append(currentPage);
		v.append("}");
		return v.toString();
	}
	
}
