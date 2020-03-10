package com.mvcweb.controller;

import com.mvcweb.model.AdminUser;
import com.mvcweb.service.WelcomeService;
import com.mvcweb.setting.ProjectSetting;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class WelcomeController extends BaseController {

	private static Logger logger = Logger.getLogger(WelcomeController.class);

	@Autowired
	private WelcomeService welcomeService;

	/**
	 * 管理员首页
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"/", "index", "/home"})
	public ModelAndView home(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView mv = new ModelAndView("home");	
		return mv;
	}

	
	/**
	 * 管理员登录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @author CZF188
	 */
	@RequestMapping(value="/loginSubmit", method = RequestMethod.POST)
	public ModelAndView loginSumbit(HttpServletRequest req,HttpServletResponse resp, AdminUser adminUser) {
		ModelAndView mv = new ModelAndView("login");
		String logincode=req.getParameter("logincode");
		String code=(String) req.getSession().getAttribute("randCheckCode");
		if(logincode.toLowerCase().equals(code.toLowerCase())){
			AdminUser admin=welcomeService.getAdminUserByLoginNameAndLoginPwd(adminUser.getLoginname(), adminUser.getLoginpwd());
			if(admin!=null){
				logger.info("Login is succeed.");
				setAdminToSession(req, resp, admin);		
				mv.addObject("admin",admin);
				try {
					resp.sendRedirect("home");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			mv.addObject("errorMsg", "用户名或密码错误");
			logger.info("Login is failed.");
			return mv;
		}else{
			mv.addObject("errorMsg", "验证码错误");
			logger.info("Login is failed.");
			return mv;
		}
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


	/**
	 * Admin退出登录
	 * 
	 * @param req
	 * @return
	 * @author CZF188
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest req) {
		req.getSession().removeAttribute("sessionAdminUser");
		req.getSession().removeAttribute("ImgLocation");
		req.getSession().invalidate();
		return "login";
	}


}