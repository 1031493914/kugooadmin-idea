package com.mvcweb.controller;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mvcweb.base.Constants;
import com.mvcweb.model.AdminNews;
import com.mvcweb.model.AdminPush;
import com.mvcweb.service.WelcomeService;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Controller
public class PushController extends BaseController {

    private static Logger logger = Logger.getLogger(PushController.class);

    @Autowired
    private WelcomeService welcomeService;

    public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";


    @RequestMapping(value = "/updateAdminNewStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAdminNewStatus(HttpServletRequest req, ModelMap modelMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        welcomeService.updateAdminNewsStatus(id,status);
        //更新推送状态
        map.put("error", "0");
        return map;
    }


    @RequestMapping(value = {"/updateAdminNews"})
    public ModelAndView updateAdminNews(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("addAdminNews");
        String id=req.getParameter("id");
        AdminNews adminNews=welcomeService.getAdminNewsById(id);
        mav.addObject("adminNews", adminNews);
        return mav;
    }



    @RequestMapping(value = {"/createaddAdminNews"})
    public ModelAndView createaddAdminNews(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("addAdminNews");
        return mav;
    }


    @RequestMapping(value = { "/updateAdminNewsSubmit"})
    public ModelAndView updateAdminNewsSubmit(HttpServletRequest req) {
        String title=req.getParameter("pushtitle");
        String content=req.getParameter("pushcontent");
        String id=req.getParameter("id");
        welcomeService.updateAdminNews(content,title,id);
        ModelAndView mav = new ModelAndView("operationmanagelist");
        List<AdminNews> listAdminNews = welcomeService.getListAdminNews();
        mav.addObject("listAdminNews", listAdminNews);
        return mav;
    }


    @RequestMapping(value = { "/addAdminNewsSubmit"})
    public ModelAndView addAdminNewsSubmit(HttpServletRequest req) {
        String title=req.getParameter("pushtitle");
        String content=req.getParameter("pushcontent");
        welcomeService.insertAdminNews(content,title);
        ModelAndView mav = new ModelAndView("operationmanagelist");
        List<AdminNews> listAdminNews = welcomeService.getListAdminNews();
        mav.addObject("listAdminNews", listAdminNews);
        return mav;
    }

    /**
     * 运营管理列表
     **/
    @RequestMapping(value = {"/operationmanagelist"})
    public ModelAndView operationmanagelist(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("operationmanagelist");
        List<AdminNews> listAdminNews = welcomeService.getListAdminNews();
        mav.addObject("listAdminNews", listAdminNews);
        return mav;
    }

    /**
     * 推送列表
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/pushlist"})
    public ModelAndView pushlist(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("pushlist");
        List<AdminPush> listAdminPush = welcomeService.getListAdminPush();
        mav.addObject("listAdminPush", listAdminPush);
        return mav;
    }


    /**
     * 创建推送
     *
     * @param args
     * @throws Exception
     */
    @RequestMapping(value = {"/createaddAdminPush"})
    public ModelAndView createaddAdminPush(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("addAdminPush");
        return mav;
    }


    @RequestMapping(value = "/addAdminPush", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAdminPush(HttpServletRequest req, ModelMap modelMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        String pushtitle = req.getParameter("pushtitle");
        String pushcontent = req.getParameter("pushcontent");
        String pushplatform = req.getParameter("pushplatform");
        //记录发送推送记录
        String pushid = welcomeService.insertNewPush(pushtitle, pushcontent, pushplatform);

        //发送推送
        int pushcode = testSendPush(pushtitle, pushcontent, pushplatform);

        //更新推送状态
        if (pushcode == 0) {
            map.put("error", "0");
        } else {
            map.put("error", "1");
        }

        return map;
    }


    public static void main(String[] args) throws Exception {

        // 回调参数可参考下面方法
        testSendPush("title", "content", "0");


    }


    public static int testSendPush(String pushtitle, String pushcontent, String pushplatform) {
        int pushcode = 1;
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(Constants.JPUSH_APP_SECRET, Constants.JPUSH_APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_android_and_ios(pushtitle, pushcontent, pushplatform);
        try {
            PushResult result = jpushClient.sendPush(payload);
            pushcode = result.statusCode;
            System.out.println("Got result - " + result.statusCode);
            System.out.println(result);
        } catch (APIConnectionException e) {
            System.out.println("Connection error. Should retry later. ");
            System.out.println("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            System.out.println("Error response from JPush server. Should review and fix it. ");
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("Msg ID: " + e.getMsgId());
            System.out.println("Sendno: " + payload.getSendno());
        }
        return pushcode;
    }


    private static void testSendPushWithCustom() throws Exception {

        ClientConfig clientConfig = ClientConfig.getInstance();
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        final NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(Constants.JPUSH_APP_KEY, Constants.JPUSH_APP_SECRET),
                null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushObject_all_alias_alert();
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    System.out.println("Got result: " + responseWrapper.responseContent);
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }

    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    public static PushPayload buildPushObject_android_and_ios(String pushtitle, String pushcontent, String pushplatform) {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("test", "https://community.jiguang.cn/push");
        Platform platfrom = null;
        if (pushplatform.equals("0")) {
            platfrom = Platform.android_ios();
        }
        if (pushplatform.equals("1")) {
            platfrom = Platform.ios();
        }
        if (pushplatform.equals("2")) {
            platfrom = Platform.android();
        }
        return PushPayload.newBuilder()
                .setPlatform(platfrom)
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(pushcontent)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(pushtitle)
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    public static void buildPushObject_with_extra() {

        JsonObject jsonExtra = new JsonObject();
        jsonExtra.addProperty("extra1", 1);
        jsonExtra.addProperty("extra2", false);

        Map<String, String> extras = new HashMap<String, String>();
        extras.put("extra_1", "val1");
        extras.put("extra_2", "val2");

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("Android Title")
                                .addExtras(extras)
                                .addExtra("booleanExtra", false)
                                .addExtra("numberExtra", 1)
                                .addExtra("jsonExtra", jsonExtra)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();

        System.out.println(payload.toJSON());
    }

    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        JsonObject sound = new JsonObject();
        sound.add("critical", new JsonPrimitive(1));
        sound.add("name", new JsonPrimitive("default"));
        sound.add("volume", new JsonPrimitive(0.2));
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setMutableContent(false)
//                                .setSound("happy")
                                .setSound(sound)
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(MSG_CONTENT))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_android_newly_support() {

        JsonObject inbox = new JsonObject();
        inbox.add("line1", new JsonPrimitive("line1 string"));
        inbox.add("line2", new JsonPrimitive("line2 string"));
        inbox.add("contentTitle", new JsonPrimitive("title string"));
        inbox.add("summaryText", new JsonPrimitive("+3 more"));

        JsonObject intent = new JsonObject();
        intent.add("url", new JsonPrimitive("intent:#Intent;component=com.jiguang.push/com.example.jpushdemo.SettingActivity;end"));

        Notification notification = Notification.newBuilder()
                .addPlatformNotification(AndroidNotification.newBuilder()
                        .setAlert(ALERT)
                        .setBigPicPath("path to big picture")
                        .setBigText("long text")
                        .setBuilderId(1)
                        .setCategory("CATEGORY_SOCIAL")
                        .setInbox(inbox)
                        .setStyle(1)
                        .setTitle("Alert test")
                        .setPriority(1)
                        .setLargeIcon("http://www.jiguang.cn/largeIcon.jpg")
                        .setIntent(intent)
                        .build())
                .build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(notification)
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .setSendno(ServiceHelper.generateSendno())
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_all_tag_not() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag_not("abc", "123"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_cid() {
        Collection<String> list = new LinkedList<String>();
        list.add("1507bfd3f79558957de");
        list.add("1507bfd3f79554957de");
        list.add("1507bfd3f79555957de");
        list.add("1507bfd3f79556957de");
        list.add("1507ffd3f79545957de");
        list.add("1507ffd3f79457957de");
        list.add("1507ffd3f79456757de");


        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
//                .setAudience(Audience.registrationId("1507bfd3f79558957de"))
                .setAudience(Audience.registrationId(list))
                .setNotification(Notification.alert(ALERT))
                .setCid("cid")
                .build();
    }


}