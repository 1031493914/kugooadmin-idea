package com.mvcweb.base;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;


public class Constants {
	/** 图片访问路径  */
	public static String IMG_URL="http://www.baidu.com";
	 
	/** 默认每页的显示数 */
	public static int PAGE_DEFAULT_SIZE = 20;
	/** 默认urlencode次数 */
	public static int ENCODE_NUM = 2;
	/** 分页显示的最多页码控制 */
	public static int PAGE_SELECT_CONTROLL = 2;
	/** 当前页变量名 */
	public static String PAGE_NAME_CURRENT_PAGE = "cp";
	/** 每页大小变量名 */
	public static String PAGE_NAME_SIZE = "ps";
	/** 信息默认文件名 */
	public static String MESSAGE_CONFIG = "config_message";
	/** 信息国际化的SESSION存储KEY(session中) */
	public static String MESSAGE_I18N_KEY_SESSION = SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;
	/** 信息国际化的SESSION存储KEY(request中) */
	public static String MESSAGE_I18N_KEY_REQUEST = "lang";
	/** 权限配置文件路径（类路径的绝对目录） */
	public static String SHIRO_CONFIG = "/config_shiro.xml";
	/** 权限用户的用户名字段 */
	public static String SHIRO_USER_USERNAME = "username";
	/** 根据请求路径获取权限字符串 */
	public static String SHIRO_GET_PERMISSION_BY_RESOURCE = "getPermissionByResource";
	/** 根据用户名获取用户信息 */
	public static String SHIRO_GET_USER_BY_USERNAME = "getUserByUsername";
	/** 根据用户名获取用户的权限 */
	public static String SHIRO_GET_USER_PERMISSIONS = "getUserPermission";
	/** 根据用户名获取用户角色 */
	public static String SHIRO_GET_USER_RROLE = "getUserRole";



	/**极光推送APPKEY**/
	public static String JPUSH_APP_KEY = "45229ef65ccf340912380660";

	/**极光推送APPSECRET**/
	public static String JPUSH_APP_SECRET = "8151bf3ba0ee48f36dd117f9";
}

