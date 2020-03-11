package com.mvcweb.service.imp;


import com.mvcweb.model.*;
import com.mvcweb.service.UserManageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class UserManageServiceImp extends BaseService implements UserManageService {

	@Override
	public List<DeviceInfo> getUserListByCondition(Page page, String createtime1, String createtime2) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		String sql =  "select count(*) from deviceinfo where status = 1 and companycode !='-1'";
		StringBuffer sbBuffer = new StringBuffer();
		if (page != null) {
			int totalSize = 0;
		

			if (StringUtils.isBlank(createtime1) || StringUtils.isBlank(createtime2)) {

				if (StringUtils.isNotBlank(createtime1)) {
					sbBuffer.append(" and  createtime>=?");
					params.add(createtime1);
				}
				if (StringUtils.isNotBlank(createtime2)) {
					sbBuffer.append(" and  createtime<=?");
					params.add(createtime2);
				}
			}
			if (StringUtils.isNotBlank(createtime1) && StringUtils.isNotBlank(createtime2)) {
				sbBuffer.append("and createtime between ? and ? ");
				params.add(createtime1);
				params.add(createtime2);
			}
			totalSize = queryInteger(sql + sbBuffer.toString(), params.toArray());
			page.setTotalSize(totalSize);
		
			if (totalSize == 0)
				return Collections.emptyList();
			if (page.getCurrentPage() > page.getTotalPage()) {
				page.setCurrentPage(page.getTotalPage());
			}
			if (page.getCurrentPage() < 1) {
				page.setCurrentPage(1);
			}
		}
		sql = "select * from deviceinfo where status = 1 and  companycode !='-1'";
		if (page != null) {
			sbBuffer.append(" limit ? offset ?");
			params.add(page.getPageSize());
			params.add((page.getCurrentPage() - 1) * page.getPageSize());
		}
		return getEntryList(sql + sbBuffer.toString() , new DeviceInfoRowMapper(), params.toArray());
		
		
		
	}

	@Override
	public String getAdminSettingByType(int i) {
		// TODO Auto-generated method stub
		String sql="select num from admin_setting where type=?";
		return queryString(sql, new Object[] {i});
	}

	@Override
	public void updateAdminSettingByType(int i, String settingoff) {
		// TODO Auto-generated method stub
		String sql="update admin_setting set num=? where type=?";
		update(sql,new Object[] {settingoff,i});
	}

	@Override
	public List<DeviceSellerAddressList> getListDeviceSellerAddressCountry() {
		String sql="select id,count(id) as num,country from device_selleraddress group by country";
		return getEntryList(sql,new DeviceSellerAddressListRowMapper(),new Object[]{});
	}

	@Override
	public List<DeviceCountList> getUserCountListByCondition() {
		String sql="select id,count(id) as num,createtime from deviceinfo where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= createtime  and companycode !='-1' GROUP BY TO_DAYS(createtime) order by createtime desc";
		return getEntryList(sql,new DeviceCountListRowMapper(),new Object[]{});
	}

	@Override
	public List<DeviceBreakDownList> getListDeviceBreakDown() {
		String sql="select id,count(id) as num,content from device_breakdown group by breakdownid  order by create_time desc";
		return getEntryList(sql,new DeviceBreakDownListRowMapper(),new Object[]{});
	}

	@Override
	public List<DeviceType> getListDeviceType() {
		String sql="select * from device_type ";
		return getEntryList(sql,new DeviceTypeRowMapper(),new Object[]{});
	}

	@Override
	public void insertDeviceType(String typecode, String companycode, String pici) {
		String sql="insert into device_type(companycode,typecode,pici,create_time) values(?,?,?,?)";
		update(sql,new Object[]{companycode,typecode,pici,new Date()});
	}

    @Override
    public List<DeviceSeller> getListDeviceSeller() {
        String sql="select * from sellers ";
        return getEntryList(sql,new DeviceSellersRowMapper(),new Object[]{});
    }

    @Override
    public void insertDeviceSeller(String sellername, String country) {
        String sql="insert into sellers(name,country,create_time) values(?,?,?)";
        update(sql,new Object[]{sellername,country,new Date()});
    }

	@Override
	public List<AdminUser> getListAdminUser() {
		String sql="select * from admin_user ";
		return getEntryList(sql, new AdminUserRowMapper(),
				new Object[] {  });
	}

	@Override
	public long insertAdminUser(String content, String loginname, String loginpwd, String role) {
		String sql="insert into admin_user(loginname,loginpwd,role,content,createtime) values(?,?,?,?,?)";
		return saveAndGetId(sql,new Object[]{loginname,loginpwd,role,content,new Date()});
	}

	@Override
	public List<AdminUser> getListAdminUserBySdk() {
		String sql="select * from admin_user where role=5";
		return getEntryList(sql, new AdminUserRowMapper(),
				new Object[] {  });
	}

	@Override
	public List<SdkApp> getListAdminSdk() {
		String sql="select * from sdk_app where status != -1 order by id desc";
		return getEntryList(sql, new SdkAppRowMapper(),
				new Object[] {  });
	}

	@Override
	public List<SdkApp> getListUserSdk(int userid) {
		String sql="select * from sdk_app where userid=? and status in(-1,0,1,2) order by id desc";
		return getEntryList(sql, new SdkAppRowMapper(),
				new Object[] { userid });
	}

	@Override
	public void updateAppStatus(String appid, String status) {
		String sql="update sdk_app set status=? where id=?";
		update(sql,new Object[]{status,appid});
	}

	@Override
	public void insertUserAPP(String appname, String packagename, String appkey, int userid) {
		String sql="insert into sdk_app(appname,appkey,packagename,status,userid,createtime) values(?,?,?,?,?,?)";
		update(sql,new Object[]{appname,appkey,packagename,-1,userid,new Date()});
	}

	@Override
	public SdkApp getSdkAppById(String appid) {
		String sql="select * from sdk_app where id=?";
		return getEntry(sql,new SdkAppRowMapper(),new Object[]{appid});
	}

	@Override
	public void updateUserAPP(String appname, String packagename, String appid) {
		String sql="update sdk_app set appname=?,packagename=? where id=?";
		update(sql,new Object[]{appname,packagename,appid});
	}

	@Override
	public List<SdkApp> getListAdminSdkByCondition(String appname) {
		String sql="select * from sdk_app where appname like '%"+appname+"%' order by id desc";
		return getEntryList(sql, new SdkAppRowMapper(),
				new Object[] {  });
	}

	@Override
	public void updateUserStatus(String appid, String status) {
		String sql="update admin_user set status=? where id=?";
		update(sql,new Object[]{status,appid});
	}

	@Override
	public List<DeviceCompany> getDeviceCompanyListByCondition() {
		String sql="select * from device_company  order by id asc";
		return getEntryList(sql, new DeviceCompanyRowMapper(),
				new Object[] {  });
	}

	@Override
	public void insertNewDeviceCompany(String companyname, String companycode,String adminuserid) {
		String sql="insert into device_company(name,code,status,create_time,adminuserid) values(?,?,?,?,?)";
		update(sql,new Object[]{companyname,companycode,1,new Date(),adminuserid});
	}

	@Override
	public AdminUser getAdminUserById(String adminuserid) {
		String sql="select * from admin_user where id=?";
		return getEntry(sql,new AdminUserRowMapper(),new Object[]{adminuserid});
	}

	@Override
	public DeviceCompany getDeviceCompanyById(String id) {
		String sql="select * from device_company where id=?";
		return getEntry(sql,new DeviceCompanyRowMapper(),new Object[]{id});
	}

	@Override
	public void updateAdminUserById(String companyname, String loginname, String loginpwd, String adminuserid) {
		String sql="update admin_user set contente=?,loginname=?,loginpwd=? where id=?";
		update(sql,new Object[]{companyname,loginname,loginpwd,adminuserid});
	}

	@Override
	public void updateDeviceCompany(String companyname, String companycode, String companyid) {
		String sql="update device_company set name=?,code=? where id=?";
		update(sql,new Object[]{companyname,companycode,companyid});
	}

	@Override
	public String getCompanyIdBycompanycode(String companycode) {
		String sql="select id from device_company where code=?";
		return queryString(sql,new Object[]{companycode});
	}

	@Override
	public String getCompanyIdByLoginName(String loginname) {
		String sql="select id from admin_user where loginname=?";
		return queryString(sql,new Object[]{loginname});
	}

	@Override
	public int getOutNumByCompanyCode(String  code) {
		String sql="select count(id) from deviceinfo where companycode='"+code+"'";
		return queryInteger(sql,new Object[]{});
	}

	@Override
	public DeviceCompany getDeviceCompanyByCode(String companycode) {
		String sql =  "select * from device_company where code = ? ";
		return getEntry(sql,new DeviceCompanyRowMapper(),new Object[]{companycode});
	}

	@Override
	public int getDeviceNumByType(int i) {
		String sql="";
		if(i==0){
			sql="SELECT count(id) FROM deviceinfo WHERE date(createtime) = curdate() and companycode !=null and  companycode != '-1'";

		}
		if(i==1){
			sql="SELECT count(id) FROM deviceinfo WHERE YEARWEEK(date_format(createtime,'%Y-%m-%d')) = YEARWEEK(now()) and  companycode != '-1'";

		}
		if(i==2){
			sql="SELECT count(id) FROM deviceinfo WHERE date_format(createtime,'%Y-%m') = date_format(now(),'%Y-%m') and  companycode != '-1'";
		}
		if(i==3){
			sql="SELECT count(id) FROM deviceinfo where companycode != '-1'";
		}
		if(i==4){
			sql="SELECT count(id) FROM deviceinfo ";
		}

		return queryInteger(sql,new Object[]{});
	}

	@Override
	public int getDeviceBreakDownNum() {
		String sql="select count(id) from device_breakdown  group by deviceid";
		return queryInteger(sql,new Object[]{});
	}


}
