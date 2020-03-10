package com.mvcweb.base;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * 分页自定义页码标签 
 * @author jx
 */
public class PageTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	// 当前页
	private int currentPage;
	// 总页码
	private int totalPage;
	// 总数量
	private int totalSize;
	// 跳转地址
	private String url;
	// 当前页变量名
	private String paramName;

	/**
	 * @param currentPage 设置当前页
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * @param totalPage 设置总页码
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * @param totalSize 设置总数量
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	/**
	 * @param url 跳转地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param paramName 当前页变量名
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	
	public String getPageByJava(){
		if(totalSize < 0) totalSize = 0;
		if(totalPage < 1) totalPage = 1;
		if(currentPage < 1) currentPage = 1;
		if(currentPage > totalPage) currentPage = totalPage;
		
		boolean hasParam = url.indexOf("?") != -1;
		String a = "";
		if(CommonUtils.isEmpty(paramName))
			a = Constants.PAGE_NAME_CURRENT_PAGE;
		else
			a = paramName;
		if(hasParam){
			url += "&" + a + "=";
		}else{
			url += "?" + a + "=";
		}
		
		// 构造字符串拼接对象
		StringBuilder page = new StringBuilder();
		// 添加上一页
		if(currentPage - 1 >= 1){			
			page.append("<a class=\"\" href=\"").append(url).append(currentPage - 1).append("\">上一页</a>");
		}else{
			page.append("<a class=\"\" href=\"dfdsfdsfds:;\">上一页</a>");
		}
			
			
		
		int i = 0;
		// 所有页码在一页内显示完 1页8个页码
		if(totalPage <= (4 + Constants.PAGE_SELECT_CONTROLL*2)){
			// 当前页在开始的左边范围内，循环输入左边页码
			for(i=1;i<=totalPage;i++){
				if(i == currentPage){
					page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
				}else{
					page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
				}
			}
		}else{
			// 当前页位置靠近起始页
			if((currentPage - Constants.PAGE_SELECT_CONTROLL) <= 4){
				// 输出起始的几位页码
				for(i=1;i<=(4+Constants.PAGE_SELECT_CONTROLL);i++){
					if(i == currentPage){
						page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
					}else{
						page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
					}
				}
				// 间隔省略号
				page.append("<a href=\"").append(url).append(i).append("\">...</a>");
				// 最后一页
				page.append("<a href=\"").append(url).append(totalPage).append("\">").append(totalPage).append("</a>");
				
			}else if((currentPage+Constants.PAGE_SELECT_CONTROLL+2) >= totalPage){ // 当前页靠近结束页
				// 第一页
				page.append("<a href=\"").append(url).append(1).append("\">").append(1).append("</a>");
				// 间隔省略号
				page.append("<a href=\"").append(url).append(totalPage-Constants.PAGE_SELECT_CONTROLL*2-Constants.PAGE_SELECT_CONTROLL).append("\">...</a>");
				// 输出末尾的几位页码
				for(i=(totalPage-Constants.PAGE_SELECT_CONTROLL-3);i<=totalPage;i++){
					if(i == currentPage){
						page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
					}else{
						page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
					}
				}
			}else{// 当前页处在中间位置
				// 第一页
				page.append("<a href=\"").append(url).append(1).append("\">").append(1).append("</a>");
				page.append("<a href=\"").append(url).append(2).append("\">").append(2).append("</a>");
				page.append("<a href=\"").append(url).append(currentPage-Constants.PAGE_SELECT_CONTROLL).append("\">").append("...").append("</a>");
				
				// 输出末尾的几位页码
				for(i=(currentPage-Constants.PAGE_SELECT_CONTROLL+1);i<=(currentPage+Constants.PAGE_SELECT_CONTROLL-1);i++){
					if(i == currentPage){
						page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
					}else{
						page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
					}
				}
				
				page.append("<a href=\"").append(url).append(i).append("\">...</a>");
				// 最后一页
				page.append("<a href=\"").append(url).append(totalPage).append("\">").append(totalPage).append("</a>");
			}
			
		}
		
		// 添加下一页
		if(currentPage + 1 <= totalPage)
			page.append("<a  href=").append(url).append(currentPage + 1).append(">下一页</a>");
		else
			page.append("<a  href=\"javascript:;\">下一页</a>");
		
		return page.toString();
	}
	
	
	
	
	public int doStartTag() throws JspException {
		if(totalSize < 0) totalSize = 0;
		if(totalPage < 1) totalPage = 1;
		if(currentPage < 1) currentPage = 1;
		if(currentPage > totalPage) currentPage = totalPage;
			
		boolean hasParam = url.indexOf("?") != -1;
		String a = "";
		if(CommonUtils.isEmpty(paramName))
			a = Constants.PAGE_NAME_CURRENT_PAGE;
		else
			a = paramName;
		if(hasParam){
			url += "&" + a + "="; 
		}else{
			url += "?" + a + "=";
		}
		
		// 构造字符串拼接对象
		StringBuilder page = new StringBuilder();
		// 添加上一页
		if(currentPage - 1 >= 1)
			page.append("<a  href=\"").append(url).append(currentPage - 1).append("\">上一页</a>");
		else
			page.append("<a  href=\"javascript:;\">上一页</a>");
		
		int i = 0;
		// 所有页码在一页内显示完 1页8个页码
		if(totalPage <= (4 + Constants.PAGE_SELECT_CONTROLL*2)){
			// 当前页在开始的左边范围内，循环输入左边页码
			for(i=1;i<=totalPage;i++){
				if(i == currentPage){
					page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
				}else{
					page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
				}
			}
		}else{
			// 当前页位置靠近起始页
			if((currentPage - Constants.PAGE_SELECT_CONTROLL) <= 4){
				// 输出起始的几位页码
				for(i=1;i<=(4+Constants.PAGE_SELECT_CONTROLL);i++){
					if(i == currentPage){
						page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
					}else{
						page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
					}
				}
				// 间隔省略号
				page.append("<a href=\"").append(url).append(i).append("\">...</a>");
				// 最后一页
				page.append("<a href=\"").append(url).append(totalPage).append("\">").append(totalPage).append("</a>");
				
			}else if((currentPage+Constants.PAGE_SELECT_CONTROLL+2) >= totalPage){ // 当前页靠近结束页
				// 第一页
				page.append("<a href=\"").append(url).append(1).append("\">").append(1).append("</a>");
				// 间隔省略号
				page.append("<a href=\"").append(url).append(totalPage-Constants.PAGE_SELECT_CONTROLL*2-Constants.PAGE_SELECT_CONTROLL).append("\">...</a>");
				// 输出末尾的几位页码
				for(i=(totalPage-Constants.PAGE_SELECT_CONTROLL-3);i<=totalPage;i++){
					if(i == currentPage){
						page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
					}else{
						page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
					}
				}
			}else{// 当前页处在中间位置
				// 第一页
				page.append("<a href=\"").append(url).append(1).append("\">").append(1).append("</a>");
				page.append("<a href=\"").append(url).append(2).append("\">").append(2).append("</a>");
				page.append("<a href=\"").append(url).append(currentPage-Constants.PAGE_SELECT_CONTROLL).append("\">").append("...").append("</a>");
				
				// 输出末尾的几位页码
				for(i=(currentPage-Constants.PAGE_SELECT_CONTROLL+1);i<=(currentPage+Constants.PAGE_SELECT_CONTROLL-1);i++){
					if(i == currentPage){
						page.append("<a href=\"javascript:;\" class=\"current\">").append(i).append("</a>");
					}else{
						page.append("<a href=\"").append(url).append(i).append("\">").append(i).append("</a>");
					}
				}
				
				page.append("<a href=\"").append(url).append(i).append("\">...</a>");
				// 最后一页
				page.append("<a href=\"").append(url).append(totalPage).append("\">").append(totalPage).append("</a>");
			}
			
		}
		
		// 添加下一页
		if(currentPage + 1 <= totalPage)
			page.append("<a  href=").append(url).append(currentPage + 1).append(">下一页</a>");
		else
			page.append("<a  href=\"javascript:;\">下一页</a>");
		
		try {
			pageContext.getOut().write(page.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
