
package com.lyarc.tp.corp.login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.DeptService;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.corp.bean.Corp;
import com.lyarc.tp.corp.corp.service.CorpService;
import com.lyarc.tp.corp.login.form.ChangePwdForm;
import com.lyarc.tp.corp.login.form.LoginForm;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.login.form.PwdInfo;
import com.lyarc.tp.corp.user.bean.User;
import com.lyarc.tp.corp.user.form.ModifyPasswordForm;
import com.lyarc.tp.corp.user.form.UserAddForm;
import com.lyarc.tp.corp.user.service.UserService;
import com.lyarc.tp.corp.util.MD5Util;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 第三方登陆验证
 * 
 * @version 1.0
 * @since JDK1.8
 * @author zhangjingtao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月7日 下午4:13:02
 */
@Controller
@Configuration
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CorpService corpService;
    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;
    
    @Autowired
    private WxCpService wxCpManageService;
    
    @Value("${server.basePath}")
    private String basePapth;;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    public String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + path;
        return basePath;
    }

    /**
     * 
     * 方法描述 首页，功能导航列表
     * 
     * @return
     * 
     * @author zhangjingtao
     * @date 2017年8月8日 下午4:44:22
     */
    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    public String navigation() {
        return "debug";
    }

    /**
     *
     * 方法描述 正式扫码登录
     *
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     *
     * @author zhangjingtao
     * @date 2017年8月9日 下午3:10:19
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String basePath = getBasePath(request);
        String url = basePath + "/login/callback";
        String loginURL = URLEncoder.encode(url, "utf-8");
        model.addAttribute("url", loginURL);
        return "login";
    }
    
    
    @RequestMapping(value = "/login/qclogin", method = RequestMethod.GET)
    public String qclogin(HttpServletRequest request) throws UnsupportedEncodingException {
    	
    	WxCpConfigStorage config = wxCpManageService.getWxCpConfigStorage();
    	 
    	String url = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid="+ config.getCorpId()
    	+"&agentid="+config.getAgentId().toString()
    	+"&redirect_uri=" + URLEncoder.encode(this.basePapth + "/login/qclogin/callback")
    	+"&state=STATE";
    	
        return "redirect:" + url; 
    }
    
    @RequestMapping(value = "/login/qclogin/callback", method = RequestMethod.GET) 
    public void qclogin(HttpServletRequest request,HttpServletResponse response,String code, HttpSession session) throws UnsupportedEncodingException {
    	
    	try { 
			String[] userInfos = wxCpManageService.getOauth2Service().getUserInfo(code);
			String empId = userInfos[0];
			logger.info("企业微信扫码登录，员工: "+empId);
			User user = userService.getByEmpId(empId);
			if(user!=null) {
				LoginUser loginUser = this.initLoginUser(user);
		        session.setAttribute(LoginConstant.SESSION_LOGIN_USER, loginUser); 
			}else {
				this.sendText(response,"员工["+ empId +"]未开通系统权限，请联系管理员");
			}
	        try {
				response.sendRedirect(request.getContextPath());
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		} catch (WxErrorException e) {
			logger.warn(e.getMessage(),e);
		    this.sendText(response,"错误："+e.getError().getErrorMsg());
		}
    }
    
    void sendText(HttpServletResponse response,String text){
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error("sendText()",e);
		}
    }

    /**
     * 
     * 方法描述 退出登录
     * 
     * @return 返回登录页面
     * 
     * @author zhangjingtao
     * @date 2017年8月11日 上午9:12:15
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(LoginConstant.SESSION_LOGIN_USER);
        return "redirect:/login";
    }


    /**
     * 
     * 方法描述 获取多级map
     * 
     * @param jsonStr
     * @return
     * 
     * @author zhangjingtao
     * @date 2017年8月9日 上午11:47:08
     */
    public static Map<String, Object> parseJSONToMaps(String jsonStr) {
        // 最外层解析
        if (jsonStr != null && jsonStr.startsWith("{") && jsonStr.endsWith("}")) {
            Map<String, Object> map = new HashMap<String, Object>();

            JSONObject json = JSONObject.parseObject(jsonStr);
            for (Object k : json.keySet()) {

                Object v = json.get(k);
                // 如果内层还是数组的话，继续解析
                if (v instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    java.util.Iterator<Object> it = ((JSONArray) v).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = (JSONObject) it.next();
                        list.add(parseJSONToMaps(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else {
                    Map<String, Object> m = parseJSONToMaps(v.toString());
                    if (m == null) {
                        map.put(k.toString(), v);
                    } else {
                        map.put(k.toString(), m);
                    }
                }
            }
            return map;
        } else {
            return null;
        }
    }

    /**
     * 获取用户功能点
     *
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月6日 下午7:01:21
     */
    @RequestMapping(path = "/api/login/getPowerKeys", method = RequestMethod.GET)
    @ResponseBody
    public ResultVo<List<String>> getPowerKeys() {
        LoginUser user = UserHolder.getUser();
        Integer userId = user.getUserId();
        List<String> powerKeys = userService.getPowersByUserId(userId);
        return ResultVo.success(powerKeys);
    }


    /**
     * 用户名密码登录
     *
     * @param model
     * @param session
     * @param form
     * @return
     * @throws UnsupportedEncodingException
     * 
     * @author lidoudou
     * @date 2017年9月10日 上午10:56:03
     */
    @RequestMapping(value = "/api/login/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> login(Model model, HttpSession session, @Valid @RequestBody LoginForm form) throws UnsupportedEncodingException {

        String loginName = form.getLoginName();
        String password = form.getPassword();
        try {
            password = MD5Util.getMD5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = userService.getByLoginNameAndPwd(loginName, password);
        if (null == user) {
            return ResultVo.build(10002, "用户名或密码不正确");
        }
        Integer status = user.getStatus();
        if (null == status || status == 2) {
            return ResultVo.build(10007, "账号已被禁用");
        }
        LoginUser loginUser = this.initLoginUser(user);
        session.setAttribute(LoginConstant.SESSION_LOGIN_USER, loginUser);
        return ResultVo.success();
    }
    
    private LoginUser initLoginUser(User user) {
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
        // loginUser.setTrueName(user.getTrueName());
        loginUser.setEmpId(user.getEmpId());

        EmployeeBean empInfo = empService.getByEmpId(user.getEmpId());
        loginUser.setEmpInfo(empInfo);
        loginUser.setTrueName(empInfo.getTrueName());
        String deptIds =empInfo.getDeptId();
        String deptId = deptIds.split(",")[0];
        DeptBean dept = deptService.getDeptById(Integer.parseInt(deptId));
        loginUser.setDeptName(dept.getDeptName()); 
        return loginUser;
    }

    @ResponseBody
    @RequestMapping(value = "api/login/get", method = RequestMethod.GET)
    public ResultVo<LoginUser> getUser() {
        LoginUser loginUserMessage = UserHolder.getUser();
        return ResultVo.success(loginUserMessage);
    }

    /**
     * 修改密码
     *
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月11日 下午2:42:58
     */
    @RequestMapping(value = "/api/login/changePwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> changePwd(@RequestBody ChangePwdForm form, HttpSession session) {
        LoginUser user = UserHolder.getUser();
        Integer userId = user.getUserId();
        User userBean = userService.getByUserId(userId);
        String password = userBean.getPassword();
        String oldPwd = form.getOldPwd();
        String newPwd = form.getNewPwd();
        try {
            oldPwd = MD5Util.getMD5(oldPwd);
            newPwd = MD5Util.getMD5(newPwd);
        } catch (Exception e) {
            logger.error("密码加密出错:{}", e.getMessage());
        }
        if (StringUtils.isBlank(oldPwd)) {
            if (!oldPwd.equals(password)) {
                return ResultVo.build(10003, "旧密码不正确");
            }
        }
        if (oldPwd.endsWith(newPwd)) {
            return ResultVo.build(10004, "旧密码与新密码不能相同");
        }

        ModifyPasswordForm pwdForm = new ModifyPasswordForm();
        pwdForm.setId(userId);
        pwdForm.setPassword(newPwd);
        userService.modifyPwd(pwdForm);
        session.removeAttribute(LoginConstant.SESSION_LOGIN_USER);
        return ResultVo.success();
    }


    /**
     * 获取密码信息
     *
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月12日 上午10:26:01
     */
    @RequestMapping(value = "/api/login/getPwdInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<PwdInfo> getPwdInfo() {
        Integer userId = UserHolder.getUser().getUserId();
        User user = userService.getByUserId(userId);
        PwdInfo info = new PwdInfo();
        info.setLoginName(user.getLoginName());
        info.setHasPassword(0);
        if (StringUtils.isNotBlank(user.getPassword())) {
            info.setHasPassword(1);
        }
        return ResultVo.success(info);
    }

    /**
     * 修改账号
     *
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月12日 上午10:26:01
     */
    @RequestMapping(value = "/api/login/changeLoginName", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<PwdInfo> changeLoginName(@RequestBody PwdInfo info, HttpSession session) {
        String loginName = info.getLoginName();
        Integer userId = UserHolder.getUser().getUserId();
        User User = userService.countByLoginName(loginName);
        if (null != User && !User.getUserId().equals(userId)) {
            return ResultVo.build(10006, "用户名已存在");
        }
        UserAddForm updateInfo = new UserAddForm();
        updateInfo.setUserId(userId);
        updateInfo.setLoginName(loginName);
        userService.edit(updateInfo);
        UserHolder.getUser().setLoginName(loginName);
        session.removeAttribute(LoginConstant.SESSION_LOGIN_USER);
        return ResultVo.success();
    }
}
