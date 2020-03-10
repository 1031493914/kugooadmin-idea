package com.mvcweb.controller;

import com.mvcweb.util.ImageUtil;
import com.mvcweb.util.WebRequestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;




@RequestMapping("/fileupload/")
@Controller
public class FileUploadController extends BaseController {
	/**
	 * 上传图片
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/image")
	public void uploadImage(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("error", 1);
		String callback = WebRequestUtils.getStringParameterSafeTrim(req,
				"callback");
		String filename = WebRequestUtils.getStringParameterTrim(req,
				"filename");
		int width = WebRequestUtils.getIntParameter(req, "width", 0);
		int height = WebRequestUtils.getIntParameter(req, "height", 0);
		MultipartFile file = WebRequestUtils.getMultipartFile(req, "file");
		if (file == null || file.isEmpty()) {
			resMap.put("msg", "上传内容为空");
			responseJavaScript(resp, callback, resMap);
			return;
		}
		String originalFilename = file.getOriginalFilename();
		if (StringUtils.isNotBlank(filename))
			originalFilename = filename;
		
		System.out.println("originalFilename----"+originalFilename);
		String dir = "/mnt/foryouimg/";
		File saveFile = null;
		try {
			saveFile = WebRequestUtils.savaUploadFile(file, dir);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).warn(e);
			resMap.put("msg", "图片上传失败");
			responseJavaScript(resp, callback, resMap);
			return;
		}
		
		int[] wh = {0, 0};
		System.out.println("xls----"+originalFilename.endsWith("xls"));
		if(!originalFilename.endsWith("xls")){
			System.out.println("xls----"+originalFilename.endsWith("xls"));
			wh = ImageUtil.getImageWdithHeight(saveFile);
		}
		
		// 允许误差在3个像素以内
		if (width > 0 && Math.abs(width - wh[0]) > 3) {
			resMap.put("error", 1);
			resMap.put("msg", "图片尺寸错误，要求：" + width + "x" + height);
			saveFile.delete();
			responseJavaScript(resp, callback, resMap);
			return;
		}

		// 允许误差在3个像素以内
		if (height > 0 && Math.abs(height - wh[1]) > 3) {
			resMap.put("error", 1);
			resMap.put("msg", "图片尺寸错误，要求：" + width + "x" + height);
			saveFile.delete();
			responseJavaScript(resp, callback, resMap);
			return;
		}

		String contentType = file.getContentType();
		System.out.println("contentType-----"+contentType);
		if (contentType == null
				|| !contentType.toLowerCase().startsWith("image")|| !contentType.toLowerCase().startsWith("xls")) {
			contentType = ImageUtil.getImageContentType(saveFile);
		}
		resMap.put("error", 0);
		resMap.put("filename", originalFilename);
		resMap.put("size", saveFile.length());
		resMap.put("path", saveFile.getName());
		resMap.put("url", saveFile.getName());
		resMap.put("width", wh[0]);
		resMap.put("height",wh[1]);
		responseJavaScript(resp, callback, resMap);
	}

}
