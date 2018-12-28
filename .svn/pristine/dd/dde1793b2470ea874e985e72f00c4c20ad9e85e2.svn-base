package com.lyarc.tp.corp.weixin.workflow.controller;

import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.DeptService;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.corp.bean.Corp;
import com.lyarc.tp.corp.corp.service.CorpService;
import com.lyarc.tp.corp.login.LoginConstant;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.controller.AdminController;
import com.lyarc.tp.corp.user.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 企业
 * 
 * @version 1.0
 * @since JDK1.8
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月3日 上午10:52:36
 */
@Controller
public class WeixinController {
    private static Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    @Qualifier("wxCpWorkflowService")
    public WxCpService wxCpWorkflowService;

    @Autowired
    private UserService userService;
    @Autowired
    private CorpService corpService;
    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;

    /**
     * 返回应用前端 spa 首页 - rx
     */
    @RequestMapping(value = {"/weixin/workflow/index", "/test"}, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String index(Model model) {
        
        String html = getFileContent("static/weixin/workflow/index.html");
        return html;
    }
    
    private String getFileContent(String path){
        String html = "";
        try {
            InputStream streamReader = new ClassPathResource(path).getInputStream();
            if (streamReader != null) { 
                html = IOUtils.toString(streamReader, "UTF-8"); 
            }else{
                html = "读取文件失败！";
            }
        } catch (Exception ex) {
            //logger.error("读取文件'public/index.html'失败",ex);
            html = "读取文件失败！";
        }

        return html;
    }



    /**
     * 返回应用前端 spa 首页 - rx
     */
    @RequestMapping(value = {"/weixin/workflow/login"}, produces = "text/html;charset=UTF-8")
    public String login(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
    	String basePath = getBasePath(request);
        String url = basePath + "/weixin/workflow/loginCallback";
        String loginURL = URLEncoder.encode(url, "utf-8");
        String redirectUrl = wxCpWorkflowService.getOauth2Service().buildAuthorizationUrl(loginURL,"");
        logger.debug(" - \r\n【微信工作流登录回调地址】" + redirectUrl);
        return "redirect:" + redirectUrl;
    }


    public String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + path;
        return basePath;
    }

    /**
     * 返回应用前端 spa 首页 - rx
     */
    @RequestMapping(value = {"/weixin/workflow/loginCallback"})
    public  String loginCallback(HttpServletRequest request,Model model) throws WxErrorException {
        String code = request.getParameter("code");
        String[] userInfo = wxCpWorkflowService.getOauth2Service().getUserInfo(code);

        if(userInfo == null || userInfo.length < 2 || StringUtils.isEmpty(userInfo[0])){
            return "forward:/login";
        }
        return doLogin(request, userInfo[0]);
    }

    private String doLogin(HttpServletRequest request,String empId){

        User user = userService.getByEmpId(empId);
        if (null == user) {
            logger.error("根据empId 没有获取用户信息，请联系管理员");
            return "根据empId 没有获取用户信息，请联系管理员";
        }
        Integer status = user.getStatus();
        if (null == status || status == 2) {
            logger.error("账号已被禁用，请联系管理员");
            return "账号已被禁用，请联系管理员";
        }
        Integer userId = user.getUserId();

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);
        loginUser.setLoginName(user.getLoginName());
        loginUser.setNickName(user.getNickName());
        loginUser.setHeader(user.getHeader());
        List<Corp> list = corpService.list();
        if(list!= null && list.size()>0){
            Corp corp = list.get(0);
            loginUser.setCorpId(corp.getCorpId());
            loginUser.setCorpName(corp.getCorpName());
            loginUser.setCorpShortName(corp.getCorpShortName());

        }
        loginUser.setEmpId(user.getEmpId());

        EmployeeBean empInfo = empService.getByEmpId(user.getEmpId());
        loginUser.setEmpInfo(empInfo);
        loginUser.setTrueName(empInfo.getTrueName());
        String deptIds =empInfo.getDeptId();
        String deptId = deptIds.split(",")[0];
        DeptBean dept = deptService.getDeptById(Integer.parseInt(deptId));
        loginUser.setDeptName(dept.getDeptName());
        HttpSession session = request.getSession();
        session.setAttribute(LoginConstant.SESSION_LOGIN_USER, loginUser);

        return "forward:/weixin/workflow/index";
    }

}

