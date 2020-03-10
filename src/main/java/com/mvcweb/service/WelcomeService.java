package com.mvcweb.service;

import com.mvcweb.model.AdminNews;
import com.mvcweb.model.AdminPush;
import com.mvcweb.model.AdminUser;

import java.util.List;

public interface WelcomeService {
	/**
	 * 验证用户是否存在
	 * @param loginname
	 * @return
	 * @author czf
	 */
	AdminUser getAdminUserByLoginName(String loginname);
	/**
	 * 验证用户登录信息
	 * @param loginname
	 * @param loginpwd
	 * @return
	 * @author czf
	 */
	AdminUser getAdminUserByLoginNameAndLoginPwd(String loginname, String loginpwd);

    List<AdminPush> getListAdminPush();

    String insertNewPush(String pushtitle, String pushcontent, String pushplatform);

    List<AdminNews> getListAdminNews();

    void insertAdminNews(String content, String title);

	AdminNews getAdminNewsById(String id);

	void updateAdminNews(String content, String title, String id);

    void updateAdminNewsStatus(String id, String status);
}
