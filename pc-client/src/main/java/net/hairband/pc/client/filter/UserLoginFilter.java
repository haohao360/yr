package net.hairband.pc.client.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.hairband.pc.client.annotation.NoNeedUserLogin;
import net.hairband.pc.client.util.Constants;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import util.framework.SessionUtil;
public class UserLoginFilter implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//用户已登录，无需拦截
		if(SessionUtil.get(Constants.SESSION_USER_INFO) != null){
			return true;
		}
		//用户未登录，拦截请求，进入主页
		HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        NoNeedUserLogin annotation = method.getAnnotation(NoNeedUserLogin.class);
        if(annotation != null) {
        	return true;
        }
        response.sendRedirect(Constants.SERVER_URL + "/main/index.htm");
    	return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
}
}
