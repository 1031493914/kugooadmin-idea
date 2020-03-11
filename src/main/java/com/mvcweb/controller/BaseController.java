package com.mvcweb.controller;

import com.mvcweb.model.PageInfoVo;
import com.mvcweb.util.StringUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseController {
	/**
	 * 文件上传保存地址
	 */
	public static final String ASSET_UPLOAD_PATH = "upload/1111111";

	/**
	 * 项目根路径
	 */
	public static final String PROJECT_ROOT = "E:/luna-workspace/plgolfadmin/WebContent/";

	/**
	 * 验证是否登陆
	 * @param req
	 * @return
	 */
	protected boolean auth(HttpServletRequest req) {
		Integer stationId = (Integer) req.getSession().getAttribute("sessionUserId");
		return null != stationId;
	}

	/**
	 * 获取当前用户的id
	 * @param req
	 * @return
	 */
	protected Integer getCurrentUserId(HttpServletRequest req) {
		return (Integer) req.getSession().getAttribute("sessionUserId");
	}

	protected String getCurrentUserName(HttpServletRequest req) {
		return req.getSession()
				.getAttribute("sessionUserName").toString();
	}

	/**
	 * 返回去除空格后的字符串 <br/>
	 * 如果是GET方式传递字符串，值非空时会被进行一次 ISO-8859-1 到 UTF-8的转码
	 * @param req
	 * @param key
	 * @return null 或 其他
	 */
	protected String getValueTrimFromRequest(HttpServletRequest req, String key) {
		String value = req.getParameter(key);
		if (value == null)
			return null;
		if ("GET".equalsIgnoreCase(req.getMethod())) {
			value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
		}
		return value.trim();
	}

	/**
	 * 返回去除空格后的字符串, 如果为空则返回空字符串,不会返回null <br/>
	 * 如果是GET方式传递字符串，值非空时会被进行一次 ISO-8859-1 到 UTF-8的转码
	 * 
	 * @param req
	 * @param key
	 * @return 空字符串 或 其他
	 */
	protected String getSafeValueTrimFromRequest(HttpServletRequest req,
			String key) {
		String value = getValueTrimFromRequest(req, key);
		if (value == null)
			return "";
		return value;
	}

	/**
	 * 解析日期类型的参数
	 * 
	 * @param req
	 * @param key
	 * @return null 或者解析成功的日期。 不会抛出异常
	 */
	protected Date getDateValueTrimFromRequest(HttpServletRequest req,
			String key) {
		String value = getValueTrimFromRequest(req, key);
		if (value == null)
			return null;
		try {
			return DateUtils.parseDate(value,
					new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 返回当前请求的页面
	 * 
	 * @param req
	 * @return 默认为1
	 */
	protected PageInfoVo getCurrentPageFromRequest(HttpServletRequest req) {
		PageInfoVo page = new PageInfoVo();
		page.setCurrentPage(NumberUtils.toInt(
				getValueTrimFromRequest(req, "page"), 1));
		page.setPagesize(15);
		return page;
	}

	protected void bindData(HttpServletRequest req, String key, Object value) {
		req.setAttribute(key, value);
	}

	protected void responseExcel(String filename, List<String> title,
			List<String> mapKey, List<Map<String, Object>> reportList,
			HttpServletResponse resp) throws Exception {
		resp.setHeader(
				"Content-disposition",
				"attachment; filename="
						+ StringUtils.newStringIso8859_1(StringUtils
								.getBytesUtf8((filename + "_"
										+ System.currentTimeMillis() + ".xls"))));// 设定输出文件头
		resp.setContentType("application/msexcel");// 定义输出类型
		resp.getOutputStream().flush(); // 立刻弹出下载提示框
		WritableWorkbook book = Workbook.createWorkbook(resp.getOutputStream());
		WritableSheet wsheet = book.createSheet("sheet1", 0);
		// 设置单元格的文字格式
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setAlignment(Alignment.CENTRE);
		Label label = null;
		for (int i = 0; i < title.size(); i++) {
			label = new Label(i, 0, title.get(i), wcf);
			wsheet.addCell(label);
		}
		WritableCellFormat cf1 = new WritableCellFormat(new DateFormat(
				"yyyy-MM-dd HH:mm:ss"));
		WritableCellFormat cf2 = new WritableCellFormat(NumberFormats.INTEGER);
		WritableCellFormat cf3 = new WritableCellFormat(NumberFormats.FLOAT);
		Object value = null;
		for (int i = 0; i < reportList.size(); i++) {
			for (int j = 0; j < mapKey.size(); j++) {
				value = reportList.get(i).get(mapKey.get(j));
				if (value == null) {
					wsheet.addCell(new Label(j, i + 1, ""));
					continue;
				}
				if (value instanceof Integer) {
					wsheet.addCell(new jxl.write.Number(j, i + 1,
							(Integer) value, cf2));
				} else if (value instanceof Float) {
					wsheet.addCell(new jxl.write.Number(j, i + 1,
							(Float) value, cf3));
				} else if (value instanceof Date) {
					wsheet.addCell(new DateTime(j, i + 1, (Date) value, cf1));
				} else {
					wsheet.addCell(new Label(j, i + 1, value + ""));
				}
			}
		}
		book.write();
		book.close();
	}

	/**
	 * 保存上传文件
	 * 
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	protected String saveUploadFile(HttpServletRequest request,
			MultipartFile multipartFile) {
		try {
			if (multipartFile == null || multipartFile.isEmpty()) {
				return null;
			}
			byte[] fileBytes = multipartFile.getBytes();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sign = fmt.format(new Date());
			String fileName = multipartFile.getOriginalFilename();
			System.out.println(sign + "  " + fileName);
			String fileLocalPath = PROJECT_ROOT + ASSET_UPLOAD_PATH;
			File parentDir = new File(fileLocalPath);
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			File file = new File(fileLocalPath, fileName);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			if (file.createNewFile()) {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(fileBytes, 0, fileBytes.length);
				fileOutputStream.close();
			}
			return ASSET_UPLOAD_PATH + fileName;
		} catch (Exception e) {
			return null;
		}
	}

	public static MultipartFile getMultipartFile(HttpServletRequest req,
			String name) {
		MultipartHttpServletRequest multipartReq = getMultipartHttpServletRequest(req);
		if (multipartReq == null)
			return null;
		return multipartReq.getMultiFileMap().getFirst(name);
	}

	public static MultipartHttpServletRequest getMultipartHttpServletRequest(
			HttpServletRequest req) {
		if (req instanceof MultipartHttpServletRequest) {
			return (MultipartHttpServletRequest) req;
		} else if (ClassUtils.hasMethod(HttpServletRequest.class, "getParts")) {
			return new StandardMultipartHttpServletRequest(req);
		}
		return null;
	}

	/**
	 * 返回ajax通过iframe提交的处理结果
	 * 
	 * @param resp
	 * @param callback
	 *            回调函数名称
	 * @param result
	 *            Map结构的返回内容
	 */
	protected void responseJavaScript(HttpServletResponse resp,
			String callback, Map<String, ?> result) {
		if (StringUtil.isNull(callback)) {
			try {
				resp.setContentType("application/json;charset=UTF-8");
				resp.getWriter().write(
						new ObjectMapper().writeValueAsString(result));
				resp.getWriter().flush();
			} catch (Exception e) {
			}
		} else {
			resp.setContentType("text/html;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			try {
				StringBuilder text = new StringBuilder();
				text.append("<script type=\"text/javascript\">");
				text.append("parent.");
				text.append(callback + "("
						+ new ObjectMapper().writeValueAsString(result) + ")");
				text.append("</script>");
				resp.getWriter().write(text.toString());
				resp.getWriter().flush();
			} catch (IOException e) {
			}
		}
	}
}
