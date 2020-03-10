package com.mvcweb.service.imp;

import com.mvcweb.model.*;
import com.mvcweb.service.WelcomeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class WelcomeServiceImp extends BaseService implements WelcomeService {

	@Override
	public AdminUser getAdminUserByLoginName(String loginname) {
		String sql = "select * from admin_user where loginname=?";
		return getEntry(sql, new AdminUserRowMapper(),
				new Object[] { loginname });
	}

	@Override
	public AdminUser getAdminUserByLoginNameAndLoginPwd(String loginname,
			String loginpwd) {
		String sql = "select * from admin_user where loginname=? and loginpwd=?";
		return getEntry(sql, new AdminUserRowMapper(), new Object[] {
				loginname, loginpwd });
	}

	@Override
	public List<AdminPush> getListAdminPush() {
		String sql = "select * from admin_push ";
		return getEntryList(sql, new AdminPushRowMapper(), new Object[] {});
	}

	@Override
	public String insertNewPush(String pushtitle, String pushcontent, String pushplatform) {
		String sql="insert into admin_push(push_title,push_content,push_platform,create_time,is_success)values(?,?,?,?,?)";
		return saveAndGetId(sql,new Object[]{pushtitle,pushcontent,pushplatform,new Date(),0})+"";
	}

    @Override
    public List<AdminNews> getListAdminNews() {
        String sql = "select * from admin_new ";
        return getEntryList(sql, new AdminNewsRowMapper(), new Object[] {});
    }

	@Override
	public void insertAdminNews(String content, String title) {
		String sql="insert into admin_new(title,content,create_time,status)values(?,?,?,?)";
		update(sql,new Object[]{title,content,new Date(),0});
	}

	@Override
	public AdminNews getAdminNewsById(String id) {
		String sql = "select * from admin_new where id=? ";
		return getEntry(sql, new AdminNewsRowMapper(), new Object[] {id});
	}

	@Override
	public void updateAdminNews(String content, String title, String id) {
		String sql="update admin_new set title=? ,content=? where id=?";
		update(sql,new Object[]{title,content,id});
	}

	@Override
	public void updateAdminNewsStatus(String id, String status) {
		String sql="update admin_new set status=?  where id=?";
		update(sql,new Object[]{status,id});
	}


}
