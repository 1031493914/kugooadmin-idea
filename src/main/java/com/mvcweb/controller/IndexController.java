package com.mvcweb.controller;

import com.mvcweb.model.AdminUser;
import com.mvcweb.service.UserManageService;
import com.mvcweb.setting.ProjectSetting;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexController extends BaseController {

    private static Logger logger = Logger.getLogger(IndexController.class);

    private UserManageService userManageService;

    @Autowired
    public void setUserManageService(UserManageService userManageService) {
        this.userManageService = userManageService;
    }


    @RequestMapping(value = {"/indexdata"})
    public ModelAndView indexdata(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("indexdata");
        int daynnum = userManageService.getDeviceNumByType(0);
        int weeknum = userManageService.getDeviceNumByType(1);
        int monthnum = userManageService.getDeviceNumByType(2);
        mav.addObject("daynnum", daynnum);
        mav.addObject("weeknum", weeknum);
        mav.addObject("monthnum", monthnum);
        //总设备数
        int allnum = userManageService.getDeviceNumByType(3);
        mav.addObject("allnum", allnum);
        //APP下载量
        int allAppnum = userManageService.getDeviceNumByType(4);
        mav.addObject("allappnum", allAppnum);
        int breakdownnum = userManageService.getDeviceBreakDownNum();
        mav.addObject("breakdownnum", breakdownnum);
        return mav;
    }


    /**
     * 设置Admin登陆状态
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