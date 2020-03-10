package com.mvcweb.filter;

import com.mvcweb.setting.ProjectSetting;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class ServerStartFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String reqContentType = req.getContentType();
		if (reqContentType == null) {
			req.setCharacterEncoding(ProjectSetting.CHARSET);
		} else {
			String[] contentType = reqContentType.split(";");
			if (contentType.length != 2) {
				req.setCharacterEncoding(ProjectSetting.CHARSET);
			} else {
				String reqCharset = contentType[1].split("=")[1];
				if (reqCharset.toLowerCase().equals("gbk") // for fileupload
						|| reqCharset.toLowerCase().equals("gb2312")
						|| reqCharset.toLowerCase().equals("utf-8")
						|| reqCharset.toLowerCase().equals("utf8")
						|| reqCharset.toLowerCase().equals("gb18030")) {
					req.setCharacterEncoding(reqCharset);
				} else {
					req.setCharacterEncoding(ProjectSetting.CHARSET);
				}
			}
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		setBasePath(httpServletRequest);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		setCharacterEncoding(config);
		String siteName = config.getServletContext().getInitParameter(
				"siteName");
		if (StringUtils.isNotBlank(siteName)) {
			ProjectSetting.COMPANY_NAME = siteName;
		}
	}

	private void setCharacterEncoding(FilterConfig config) {
		String initCharset = config.getInitParameter("charSet");
		if (initCharset == null || initCharset.trim().length() == 0)
			return;
		ProjectSetting.CHARSET = initCharset;
	}

	private void setBasePath(HttpServletRequest request) {
		if (StringUtils.isNotBlank(ProjectSetting.BASE_PATH))
			return;
		String basePath = request.getSession().getServletContext()
				.getInitParameter("basePath");
		if (basePath == null) {
			String path = request.getContextPath();
			basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path + "/";
		}
		ProjectSetting.BASE_PATH = basePath;
	}

}
