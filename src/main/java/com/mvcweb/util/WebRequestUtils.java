package com.mvcweb.util;

import com.mvcweb.base.Constants;
import com.mvcweb.model.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


public class WebRequestUtils extends ServletRequestUtils {
	
	static Log log = LogFactory.getLog(WebRequestUtils.class);
	/**
	 * 返回当前请求的页面
	 * @param req
	 * @return 默认为1
	 */
	public static Page getCurrentPageFromRequest(HttpServletRequest req){
		Page page = new Page();
		page.setCurrentPage(NumberUtils.toInt(getStringParameterTrim(req, Constants.PAGE_NAME_CURRENT_PAGE), 1));
		Integer ps = getIntegerParameter(req, Constants.PAGE_NAME_SIZE);
		if(ps != null && ps > 0){
			page.setPageSize(ps);
		}
		return page;
	}

	/**
	 * 返回去除空格后的字符串  <br/>
	 * @param req
	 * @param key    如果是GET访问，则会经过一次URLDecoder.decode
	 * @return  null 或 其他
	 */
	
	public static String getStringParameterTrim(HttpServletRequest req,String key){
		String value = req.getParameter(key);
		if(value == null) return null;
		if("GET".equalsIgnoreCase(req.getMethod())){
			try {
				value = URLDecoder.decode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value.trim();
	}
	
	/**
	 * 返回去除空格后的字符串, 如果为空则返回空字符串,不会返回null  <br/>
	 * @param req
	 * @param key
	 * @return  空字符串 或 其他
	 */
	public static String getStringParameterSafeTrim(HttpServletRequest req,String key){
		String value = getStringParameterTrim(req,key);
		if(value == null) return "";
		return value;
	}
	
	/**
	 * 返回去除空格后的字符串, 如果为空则返回空字符串,不会返回null<br/>
	 * 处理逻辑： 中文逗号转成英文逗号 》 清除所有空格 》 替换多个连续的逗号为一个 》 去除开头和结尾的逗号
	 * @param req
	 * @param key
	 * @return  空字符串 或 其他
	 */
	public static String getMultipleStringParameterSafeTrim(HttpServletRequest req,String key){
		String value = getStringParameterTrim(req,key);
		if(value == null) return "";
		// 将中文逗号统一转成英文逗号，并去除包含的空格
		value = value.replaceAll("，", ",").replaceAll("\\s+", "").replaceAll("\\,{2,}", ",");
		// 去除首部和尾部的逗号
		value = value.replaceFirst("^\\,", "").replaceFirst("\\,$", "");
		return value;
	}
	
	/**
	 * 解析日期类型的参数,格式为 yyyy-MM-dd 和 yyyy-MM-dd HH:mm:ss
	 * @param req
	 * @param key
	 * @return null 或者解析成功的日期。  不会抛出异常
	 */
	public static Date getDateValueTrimFromRequest(HttpServletRequest req,String key){
		String value = getStringParameterTrim(req,key);
		if(StringUtils.isBlank(value)) return null;
		try {
			return DateUtils.parseDate(value, new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","yyyy-MM"});
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 解析日期类型的参数
	 * @param req
	 * @param key
	 * @param patterns 日期的格式，多个日期格式的数组
	 * @return null 或者解析成功的日期。  不会抛出异常
	 */
	public static Date getDateValueTrimFromRequest(HttpServletRequest req,String key,String[] patterns){
		String value = getStringParameterTrim(req,key);
		if(StringUtils.isBlank(value)) return null;
		try {
			return DateUtils.parseDate(value, patterns);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 解析整数类型的参数
	 * @param req
	 * @param key
	 * @return 如果解析失败则返回null
	 */
	public static Integer getIntegerParameter(HttpServletRequest req,String key){
		String value = getStringParameterTrim(req,key);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	/**
	 * 解析布尔类型的参数
	 * @param req
	 * @param key
	 * @return 如果解析失败则返回null
	 */
	public static Boolean getBooleanParameter(HttpServletRequest req,String key){
		String value = getStringParameterTrim(req,key);
		if("true".equalsIgnoreCase(value)){
			return true;
		}
		if("false".equalsIgnoreCase(value)){
			return false;
		}
		return null;
	}
	
	/**
	 * 解析整数类型的参数
	 * @param req
	 * @param key
	 * @param defaultValue
	 * @return 如果解析失败则返回null
	 */
	public static Integer getIntegerParameter(HttpServletRequest req,String key,Integer defaultValue){
		String value = getStringParameterTrim(req,key);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	

	

	/**
	 * 将HttpServletRequest中的参数转换到Map中
	 * @param req
	 * @return 包含所有req中的key值，  value值可能是 null或者 String[]
	 */
	public static Map<String,Object> getParameterMap(HttpServletRequest req){
		Map<String,Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Enumeration<String> keyEnum =  req.getParameterNames();
		Object v;
		String k;
		while(keyEnum.hasMoreElements()){
			k = keyEnum.nextElement();
			v = getStringParameterTrim(req, k);
			if(v == null) v = req.getParameterValues(k);
			map.put(k, v);
		}
		return map;
	}
	
	/**
	 * 是否是ajax请求  (通过X-Requested-With判断,jquery默认ajax请求时会添加这个header参数)
	 * @param request
	 * @return true 是一个ajax请求， false 不是
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		if(request.getHeader("X-Requested-With") != null) return true;
		return false;
	}
	
	/**
	 * 获取文件上传的请求对象
	 * 
	 * @param request 普通请求对象
	 * @return 如果可以获取则返回，否则返回null
	 */
	public static MultipartHttpServletRequest getMultipartHttpServletRequest(HttpServletRequest req){
		if (req instanceof MultipartHttpServletRequest) {
			return (MultipartHttpServletRequest) req;
		}
		else if (ClassUtils.hasMethod(HttpServletRequest.class, "getParts")) {
			// Servlet 3.0 available ..
			return new StandardMultipartHttpServletRequest(req);
		}
		return null;
	}
	
	/**
	 * 返回请求中所有上传的文件对象集合
	 * 
	 * @param req 请求对象
	 * @param nameList 参数名列表
	 * @return 文件对象集合，不是上次请求则返回空集合
	 */
	public static MultiValueMap<String,MultipartFile> getMultipartFileMap(HttpServletRequest req,List<String> nameList){
		MultipartHttpServletRequest multipartReq = getMultipartHttpServletRequest(req);
		if(multipartReq == null) return new LinkedMultiValueMap<String, MultipartFile>(0);
		return multipartReq.getMultiFileMap();
	}
	
	/**
	 * 返回请求中指定参数的上传文件对象
	 * 
	 * @param req 请求对象
	 * @param name 参数名
	 * @return 能获取到则返回文件对象，其他情况返回null
	 */
	public static MultipartFile getMultipartFile(HttpServletRequest req,String name){
		MultipartHttpServletRequest multipartReq = getMultipartHttpServletRequest(req);
		if(multipartReq == null) return null;
		return multipartReq.getMultiFileMap().getFirst(name);
	}
	
	/**
	 * 检查是否是文件类型
	 * 
	 * @param contentType
	 * @return
	 */
	public static boolean isImageContentType(String contentType){
		if(contentType == null) return false;
		if(!contentType.toLowerCase().startsWith("image")) return false;
		return true;
	}
	
	/**
	 * 保存上传的文件，返回保存的文件名
	 * 
	 * @param mf 文件上传对象
	 * @param dir 文件的保存目录
	 * @return 返回此次操作保存的文件名，不包含路径
	 */
	public static File savaUploadFile(MultipartFile mf,String dir){
		String suffix = ""; // 默认文件无后缀
		// 原始文件名
		String originalName = mf.getOriginalFilename();
		log.debug("[upload] 原始文件名："+originalName);
		// 截取后缀名
		if(originalName.lastIndexOf(".") != -1 && !originalName.endsWith(".")){
			suffix = originalName.substring(originalName.lastIndexOf("."));
		}
		// 拼接文件名 格式为： 当前毫米数+原始文件后缀
		String fileName = System.currentTimeMillis()+suffix;
		log.debug("[upload] 保存文件名："+fileName);
		// 确保文件目录存在
		File saveDirFile = new File(dir);
		if(!saveDirFile.exists()){
			saveDirFile.mkdirs();
		}
		log.debug("[upload] 保存目录路径："+saveDirFile.getAbsolutePath());
		// 保存文件的对象
		File saveFile = new File(saveDirFile, fileName);
		log.debug("[upload] 保存文件路径："+saveFile.getAbsolutePath());
		try {
			// 写入文件
			mf.transferTo(saveFile);
		} catch (Exception e) {
			
		}
		// 返回保存的文件名
		return saveFile;
	}
	
	/**
	 * 返回客户端的IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

}
