package com.lyarc.tp.corp;

import com.lyarc.tp.corp.login.LoginConstant;
import com.lyarc.tp.corp.login.form.LoginUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 实现拦截器
 * 
 * @version 1.0
 * @since JDK1.8
 * @author zhangjingtao
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月9日 下午6:26:34
 */
public class ActionInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("=====controller发生后拦截信息" + request.getRequestURI());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        //System.out.println("=====controller请求时拦截信息" + request.getRequestURI());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	StringBuilder stringBuilder = new  StringBuilder();
    	stringBuilder.append("===========>http 请求\r\n");
    	stringBuilder.append(request.getRequestURL()+"\r\n");
    	stringBuilder.append("<====");
    	
    	String uri = request.getRequestURI().toString();
    	String context = request.getContextPath();
    	
    	System.out.println(stringBuilder.toString());
    	
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute(LoginConstant.SESSION_LOGIN_USER);
        
        
        // 登录用户
        if (loginUser == null) {
        	if(isIgnore(request)){
                return true;
        	}else{
        		// api 请求处理，未登录返回  401 by rx
                if (uri.startsWith(context + "/api/")){
        			returnJson(response,"{\"code\":401,\"message\":\"未登录！\"}");
        		}else{
        			// 页面请求返回登录页面
        			response.sendRedirect(request.getContextPath() + "/login");
        		} 
                return false;
        	}
        } else {
            UserHolder.setUser(loginUser);
//            if(uri.endsWith("/login")){
//                response.sendRedirect(request.getContextPath() + "/");
//            }
            return true;
        }

//        return true;
    }
    
    private void returnJson(HttpServletResponse response, String json) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    public boolean isIgnore(HttpServletRequest request) {
        String context = request.getContextPath();
        String uri = request.getRequestURI();
        String[] ignore = new String[] {
        		//"/index",
        		"/test",
                "/suite", 
                "/suite/auth", 
                "/suite/auth/callback", 
                "/suite/app/callback",
                "/debug", 
                "/debug/setup", 
                "/login/qclogin",
                "/api/login/login",
                "/api/inner",
                "/api/inner/weixin/getUserByCode",
                "/api/inner/weixin/jssdk",
                "/api/cag/order/paycallback",
                "/login",
                "/error",
                "/weixin/workflow"
                };
        for (String str : ignore) {
            if (uri.startsWith(context + str)) {
                return true;
            }
        }
        return false;
    }

}
