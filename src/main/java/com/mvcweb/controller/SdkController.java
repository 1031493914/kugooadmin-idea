package com.mvcweb.controller;

import com.mvcweb.model.AdminUser;
import com.mvcweb.model.SdkApp;
import com.mvcweb.service.UserManageService;
import com.mvcweb.setting.ProjectSetting;
import com.mvcweb.util.StringUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class SdkController extends BaseController {

	private static Logger logger = Logger.getLogger(SdkController.class);

	private UserManageService userManageService;
	@Autowired
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}



	@RequestMapping({"/searchAppList"})
	public ModelAndView searchAppList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView("adminapplist");
		String appname=req.getParameter("appname");
		List<SdkApp> listSdkApp=userManageService.getListAdminSdkByCondition(appname);
		mav.addObject("listSdkApp",listSdkApp);
		return mav;
	}

	@RequestMapping(value = { "updateSdkAPPSubmit"})
	public ModelAndView updateSdkAPPSubmit(HttpServletRequest req) {
		String appname=req.getParameter("appname");
		String packagename=req.getParameter("packagename");
		AdminUser adminUser= (AdminUser) req.getSession().getAttribute("sessionAdminUser");
		int userid=adminUser.getId();
		String appid=req.getParameter("appid");
		userManageService.updateUserAPP(appname,packagename,appid);
		ModelAndView mav = new ModelAndView("sdkapplist");
		List<SdkApp> listSdkApp=userManageService.getListUserSdk(userid);
		mav.addObject("listSdkApp",listSdkApp);
		return mav;
	}




	@RequestMapping(value = { "/addSdkApp"})
	public ModelAndView addSdkApp(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("addSdkApp");
		String appid=req.getParameter("appid");
		SdkApp sdkAPP=new SdkApp();
		if(StringUtil.isNull(appid)){
			mav.addObject("sdkAPP",sdkAPP);
			mav.addObject("appid",'0');
		}else{

			sdkAPP=userManageService.getSdkAppById(appid);
			mav.addObject("sdkAPP",sdkAPP);
			mav.addObject("appid",appid);
		}


		return mav;

	}

//	SdkApp sdkAPP=new SdkApp();
//		if(!appid.equals("0")){
//		sdkAPP=userManageService.getSdkAppById(appid);
//		appid=sdkAPP.getId()+"";
//	}
//
//		mav.addObject("sdkAPP",sdkAPP);

	@RequestMapping(value = { "/addSdkAPPSubmit"})
	public ModelAndView addSdkAPPSubmit(HttpServletRequest req) {
		String appname=req.getParameter("appname");
		String packagename=req.getParameter("packagename");
		String appkey=System.currentTimeMillis()+ RandomStringUtils.random(4, "1234567890");
		AdminUser adminUser= (AdminUser) req.getSession().getAttribute("sessionAdminUser");
		int userid=adminUser.getId();
		userManageService.insertUserAPP(appname,packagename,appkey,userid);
		ModelAndView mav = new ModelAndView("sdkapplist");
		List<SdkApp> listSdkApp=userManageService.getListUserSdk(userid);
		mav.addObject("listSdkApp",listSdkApp);
		return mav;
	}

	/**
	 * 操作APP
	 */
	@RequestMapping(value = "/updateAppStatus", method = RequestMethod.POST)
	@ResponseBody
	public Boolean deleteBusinesses(HttpServletRequest req) throws Exception {
		String appid = req.getParameter("id");
		String status = req.getParameter("status");

		userManageService.updateAppStatus(appid,status);



		return true;
	}
	/**
	 * 使用方应用管理
	 */
	@RequestMapping({"/sdkapplist"})
	public ModelAndView sdkapplist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView("sdkapplist");
		AdminUser adminUser= (AdminUser) req.getSession().getAttribute("sessionAdminUser");
		List<SdkApp> listSdkApp=userManageService.getListUserSdk(adminUser.getId());
		mav.addObject("listSdkApp",listSdkApp);
		return mav;
	}

	/**
	 * 应用管理
	 */
	@RequestMapping({"/adminapplist"})
	public ModelAndView adminapplist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView("adminapplist");
		List<SdkApp> listSdkApp=userManageService.getListAdminSdk();
		mav.addObject("listSdkApp",listSdkApp);
		return mav;
	}


	@RequestMapping(value = "/operatorUser", method = RequestMethod.POST)
	@ResponseBody
	public Boolean operatorUser(HttpServletRequest req) throws Exception {
		String userid = req.getParameter("id");
		String status = req.getParameter("status");

		userManageService.updateUserStatus(userid,status);



		return true;
	}

	/**
	 * 管理员首页
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"/sdkuserlist"})
	public ModelAndView sdkuserlist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView("sdkUserList");
		List<AdminUser> listAdminUser=userManageService.getListAdminUserBySdk();
		mav.addObject("listAdminUser",listAdminUser);
		return mav;
	}

	/**
	 * 管理管理员
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/createaddSdkUser"})
	public ModelAndView createaddSdkUser(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("addSdkUser");
		return mav;
	}


	@RequestMapping(value = { "/addSdkUserSubmit"})
	public ModelAndView addSdkUserSubmit(HttpServletRequest req) {
		String content=req.getParameter("content");
		String loginname=req.getParameter("loginname");
		String loginpwd=req.getParameter("loginpwd");
		String role=req.getParameter("role");
		userManageService.insertAdminUser(content,loginname,loginpwd,role);
		ModelAndView mav = new ModelAndView("sdkUserList");
		List<AdminUser>  listAdminUser=userManageService.getListAdminUserBySdk();
		mav.addObject("listAdminUser",listAdminUser);
		return mav;
	}



	/**
	 * 设置Admin登陆状态
	 * 
	 * @param req
	 * @param resp
	 */
	private void setAdminToSession(HttpServletRequest req,
			HttpServletResponse resp, AdminUser adminUser) {
		req.getSession().setAttribute("sessionAdminUser", adminUser);
		req.getSession().setAttribute("ImgLocation", ProjectSetting.IMG_LOCATION);
		req.getSession().setAttribute("defaultImg", ProjectSetting.DEFAULT_IMG);
	}



}