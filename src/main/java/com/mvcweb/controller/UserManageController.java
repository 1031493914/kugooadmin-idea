package com.mvcweb.controller;

import com.mvcweb.base.Constants;
import com.mvcweb.model.*;
import com.mvcweb.service.UserManageService;
import com.mvcweb.util.StringUtil;
import com.mvcweb.util.WebRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserManageController {


    private UserManageService userManageService;

    @Autowired
    public void setUserManageService(UserManageService userManageService) {
        this.userManageService = userManageService;
    }



    @RequestMapping({"/searchOutNumList"})
    public ModelAndView searchOutNumList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mav = new ModelAndView("outnumlist");
        String companycode=req.getParameter("companycode");
        String devicetype=req.getParameter("devicetype");

        List<DeviceCompany> devicecompanyList = userManageService.getDeviceCompanyListByCondition();
        List<DeviceType> devicetypeList = userManageService.getListDeviceType();

        List<DeviceCompany> outnumdevicecompanyList=new ArrayList<DeviceCompany>();
        int outnum = userManageService.getOutNumByCompanyCode(companycode);
        DeviceCompany outnumdc=userManageService.getDeviceCompanyByCode(companycode);
        outnumdc.setOutnum(outnum);
        outnumdevicecompanyList.add(outnumdc);
        mav.addObject("outnumdevicecompanyList", outnumdevicecompanyList);
        mav.addObject("devicecompanyList", devicecompanyList);
        mav.addObject("devicetypeList", devicetypeList);
        return mav;
    }


    @RequestMapping({"/outnum"})
    public ModelAndView outnum(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mav = new ModelAndView("outnumlist");
        List<DeviceCompany> devicecompanyList = userManageService.getDeviceCompanyListByCondition();
        List<DeviceType> devicetypeList = userManageService.getListDeviceType();
        List<DeviceCompany> outnumdevicecompanyList=new ArrayList<DeviceCompany>();
        for (DeviceCompany dc : devicecompanyList) {
            int outnum = userManageService.getOutNumByCompanyCode(dc.getCode());
            System.out.println("dc.getCode()----"+dc.getCode()+"outnum------"+outnum);
            dc.setOutnum(outnum);
            outnumdevicecompanyList.add(dc);
        }
        mav.addObject("outnumdevicecompanyList", outnumdevicecompanyList);
        mav.addObject("devicecompanyList", devicecompanyList);
        mav.addObject("devicetypeList", devicetypeList);
        return mav;
    }


    @RequestMapping(value = {"/createDeviceCompany"})
    public ModelAndView createDeviceCompany(HttpServletRequest req) {
        String id = req.getParameter("companyid");
        DeviceCompany deviceCompany = new DeviceCompany();
        if (!StringUtil.isNull(id)) {
            deviceCompany = userManageService.getDeviceCompanyById(id);
        }
        ModelAndView mav = new ModelAndView("addDeviceCompany");
        mav.addObject("deviceCompany", deviceCompany);
        mav.addObject("errormessage", "");
        return mav;
    }

    /**
     * 设备厂商提交
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/addDeviceCompanySubmit"})
    public ModelAndView addDeviceCompanySubmit(HttpServletRequest req) {
        String companyname = req.getParameter("companyname");
        String companycode = req.getParameter("companycode");
        String loginname = req.getParameter("loginname");
        String loginpwd = req.getParameter("loginpwd");
        String companyid = req.getParameter("companyid");
        String adminuserid = req.getParameter("adminuserid");

        String hascompanyid = userManageService.getCompanyIdBycompanycode(companycode);

        String hasloginnameid = userManageService.getCompanyIdByLoginName(loginname);

        if (!StringUtil.isNull(hascompanyid)) {
            ModelAndView mav = new ModelAndView("addDeviceCompany");
            mav.addObject("errormessage", "厂商代码已经存在");
            return mav;
        }

        if (!StringUtil.isNull(hasloginnameid)) {
            ModelAndView mav = new ModelAndView("addDeviceCompany");
            mav.addObject("errormessage", "登录名已经存在");
            return mav;
        }

        if (StringUtil.isNull(companyid) || companyid.equals("0")) {
            adminuserid = userManageService.insertAdminUser(companyname, loginname, loginpwd, "2") + "";
            //添加厂商
            userManageService.insertNewDeviceCompany(companyname, companycode, adminuserid);
        } else {
            //adminuserid
            userManageService.updateAdminUserById(companyname, loginname, loginpwd, adminuserid);
            userManageService.updateDeviceCompany(companyname, companycode, companyid);
        }


        ModelAndView mav = new ModelAndView("devicecompanylist");
        List<DeviceCompany> devicecompanyList = userManageService.getDeviceCompanyListByCondition();
        mav.addObject("devicecompanyList", devicecompanyList);
        return mav;
    }


    /**
     * 设备厂商管理
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/deviceCompanyList"})
    public ModelAndView deviceCompanyList(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicecompanylist");
        List<DeviceCompany> devicecompanyList = userManageService.getDeviceCompanyListByCondition();
        for (DeviceCompany deviceCompany : devicecompanyList) {
            if (!StringUtil.isNull(deviceCompany.getAdminuserid())) {
                AdminUser adminUser = userManageService.getAdminUserById(deviceCompany.getAdminuserid());
                if (adminUser != null) {
                    deviceCompany.setLoginname(adminUser.getLoginname());
                    deviceCompany.setLoginpwd(adminUser.getLoginpwd());
                } else {
                    deviceCompany.setLoginname("");
                    deviceCompany.setLoginpwd("");
                }
            } else {
                deviceCompany.setLoginname("");
                deviceCompany.setLoginpwd("");
            }
        }

        mav.addObject("devicecompanyList", devicecompanyList);
        return mav;
    }


    @RequestMapping(value = {"/devicemanagelist", "/searchDeviceList"})
    public ModelAndView devicemanagelist(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicelist");
        String createtime1 = StringUtil.isNull(req.getParameter("createtime1")) ? "" : req.getParameter("createtime1");
        String createtime2 = StringUtil.isNull(req.getParameter("createtime2")) ? "" : req.getParameter("createtime2");
        Page page = WebRequestUtils.getCurrentPageFromRequest(req);
        page.setPageSize(Constants.PAGE_DEFAULT_SIZE);
        List<DeviceInfo> deviceList = userManageService.getUserListByCondition(page, createtime1, createtime2);
        mav.addObject("deviceList", deviceList);
        mav.addObject("page", page);
        return mav;
    }


    @RequestMapping(value = {"/adminrolelist"})
    public ModelAndView adminrolelist(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("adminrolelist");
        List<AdminUser> listAdminUser = userManageService.getListAdminUser();
        mav.addObject("listAdminUser", listAdminUser);
        return mav;
    }

    @RequestMapping(value = {"/createaddAdminUser"})
    public ModelAndView createaddAdminUser(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("addAdminRole");
        return mav;
    }


    @RequestMapping(value = {"/addAdminUserSubmit"})
    public ModelAndView addAdminUserSubmit(HttpServletRequest req) {
        String content = req.getParameter("content");
        String loginname = req.getParameter("loginname");
        String loginpwd = req.getParameter("loginpwd");
        String role = req.getParameter("role");
        userManageService.insertAdminUser(content, loginname, loginpwd, role);
        ModelAndView mav = new ModelAndView("adminrolelist");
        List<AdminUser> listAdminUser = userManageService.getListAdminUser();
        mav.addObject("listAdminUser", listAdminUser);
        return mav;
    }


    @RequestMapping(value = {"/deviceseller"})
    public ModelAndView deviceseller(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("deviceseller");
        List<DeviceSeller> listDeviceSeller = userManageService.getListDeviceSeller();
        mav.addObject("listDeviceSeller", listDeviceSeller);
        return mav;
    }


    @RequestMapping(value = {"/addDeviceSeller"})
    public ModelAndView addDeviceSeller(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("addDeviceSeller");
        return mav;
    }

    @RequestMapping(value = {"/addDeviceSellerSubmit"})
    public ModelAndView addDeviceSellerSubmit(HttpServletRequest req) {
        String sellername = req.getParameter("sellername");
        String country = req.getParameter("country");
        userManageService.insertDeviceSeller(sellername, country);
        ModelAndView mav = new ModelAndView("deviceseller");
        List<DeviceSeller> listDeviceSeller = userManageService.getListDeviceSeller();
        mav.addObject("listDeviceSeller", listDeviceSeller);
        return mav;
    }


    @RequestMapping(value = {"/mapinfo"})
    public ModelAndView mapinfo(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("mapinfo");
        return mav;
    }


    @RequestMapping(value = {"/addDeviceType"})
    public ModelAndView addDeviceType(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("adddevicetype");
        return mav;
    }


    @RequestMapping(value = {"/addDeviceTypeSubmit"})
    public ModelAndView addDeviceTypeSubmit(HttpServletRequest req) {
        String typecode = req.getParameter("typecode");
        String companycode = req.getParameter("companycode");
        String pici = req.getParameter("pici");
        userManageService.insertDeviceType(typecode, companycode, pici);
        ModelAndView mav = new ModelAndView("devicetype");
        List<DeviceType> listDeviceType = userManageService.getListDeviceType();
        mav.addObject("listDeviceType", listDeviceType);
        return mav;
    }


    @RequestMapping(value = {"/devicetype"})
    public ModelAndView devicetype(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicetype");
        List<DeviceType> listDeviceType = userManageService.getListDeviceType();
        mav.addObject("listDeviceType", listDeviceType);
        return mav;
    }


    @RequestMapping(value = {"/devicearea"})
    public ModelAndView devicearea(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicearea");
        List<DeviceSellerAddressList> listDeviceSellerAddressList = userManageService.getListDeviceSellerAddressCountry();
        mav.addObject("listDeviceSellerAddressList", listDeviceSellerAddressList);
        return mav;
    }


    @RequestMapping(value = {"/devicecount"})
    public ModelAndView devicecount(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicecount");
        List<DeviceCountList> deviceList = userManageService.getUserCountListByCondition();
        for (DeviceCountList dc : deviceList) {
            String strDateFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            dc.setStrdatetime(sdf.format(dc.getDatetime()));
        }
        mav.addObject("deviceList", deviceList);
        return mav;
    }


    @RequestMapping(value = {"/devicesetting"})
    public ModelAndView devicesetting(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicesetting");
        //0静止频率  1运动频率  2故障频率
        //0
        String settingon = userManageService.getAdminSettingByType(0);
        String settingoff = userManageService.getAdminSettingByType(1);
        String settingbreakdown = userManageService.getAdminSettingByType(2);
        mav.addObject("settingon", settingon);
        mav.addObject("settingoff", settingoff);
        mav.addObject("settingbreakdown", settingbreakdown);
        return mav;
    }


    @RequestMapping(value = {"/SystemSettingSubmit"})
    public ModelAndView SystemSettingSubmit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicesetting");
        String settingon = req.getParameter("settingon");
        String settingoff = req.getParameter("settingoff");
        String settingbreakdown = req.getParameter("settingbreakdown");
        userManageService.updateAdminSettingByType(0, settingon);
        userManageService.updateAdminSettingByType(1, settingoff);
        userManageService.updateAdminSettingByType(2, settingbreakdown);
        mav.addObject("settingon", settingon);
        mav.addObject("settingoff", settingoff);
        mav.addObject("settingbreakdown", settingbreakdown);
        return mav;
    }


    @RequestMapping(value = {"/devicebreakdown"})
    public ModelAndView devicebreakdown(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("devicebreakdown");
        List<DeviceBreakDownList> listDeviceBreakDown = userManageService.getListDeviceBreakDown();
        mav.addObject("listDeviceBreakDown", listDeviceBreakDown);
        return mav;
    }


    /**
     * AJAX操作
     * @param req
     * @return
     * @throws Exception
     */
//	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
//	@ResponseBody
//	public Boolean deleteUser(HttpServletRequest req) throws Exception {
//		String userid = req.getParameter("id");
//		userManageService.updateUserStatusByUid(userid);
//		return true;
//	}


}
