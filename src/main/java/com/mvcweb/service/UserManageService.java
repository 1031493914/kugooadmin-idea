package com.mvcweb.service;

import com.mvcweb.model.*;

import java.util.List;



public interface UserManageService {

	List<DeviceInfo> getUserListByCondition(Page page, String createtime1, String createtime2);

	String getAdminSettingByType(int i);

	void updateAdminSettingByType(int i, String settingoff);

    List<DeviceSellerAddressList> getListDeviceSellerAddressCountry();

    List<DeviceCountList> getUserCountListByCondition();

    List<DeviceBreakDownList> getListDeviceBreakDown();

    List<DeviceType> getListDeviceType();


    void insertDeviceType(String typecode, String companycode, String pici);

    List<DeviceSeller> getListDeviceSeller();

    void insertDeviceSeller(String sellername, String country);

    List<AdminUser> getListAdminUser();

    long insertAdminUser(String content, String loginname, String loginpwd, String role);

    List<AdminUser> getListAdminUserBySdk();

    List<SdkApp> getListAdminSdk();

    List<SdkApp> getListUserSdk(int userid);

    void updateAppStatus(String appid, String status);

    void insertUserAPP(String appname, String packagename, String appkey, int userid);

    SdkApp getSdkAppById(String appid);

    void updateUserAPP(String appname, String packagename, String appid);

    List<SdkApp> getListAdminSdkByCondition(String appname);

    void updateUserStatus(String appid, String status);

    List<DeviceCompany> getDeviceCompanyListByCondition();

    void insertNewDeviceCompany(String companyname, String companycode,String adminuserid);

    AdminUser getAdminUserById(String adminuserid);

    DeviceCompany getDeviceCompanyById(String id);

    void updateAdminUserById(String companyname, String loginname, String loginpwd, String adminuserid);

    void updateDeviceCompany(String companyname, String companycode, String companyid);

    String getCompanyIdBycompanycode(String companycode);

    String getCompanyIdByLoginName(String loginname);

    int getOutNumByCompanyCode(String code);

    DeviceCompany getDeviceCompanyByCode(String companycode);

    int getDeviceNumByType(int i);

    int getDeviceBreakDownNum();
}
