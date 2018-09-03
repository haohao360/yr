package net.hairband.controller.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hairband.dto.user.CmsUser;
import net.hairband.hairbandcontant.CarloanContant;
import net.hairband.service.user.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import framework.web.ReqBo;
import framework.web.SuperRes;
import util.framework.FindServiceUtil;
import util.framework.SessionUtil;

public class AuthHandler implements HandlerInterceptor{
	
private static Logger log = LoggerFactory.getLogger(AuthHandler.class);
	
	private String url;
	
	private final String script = "<script type=\"text/javascript\"> window.location.href = \"/hairband-cms\" </script>";
	
	private final String script2 = "<script type=\"text/javascript\"> window.parent.location.href = \"/hairband-cms\" </script>";
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String reqUrl = request.getRequestURI();
		String html = request.getParameter("html");
		String parent = request.getParameter("parent");
		if(reqUrl.endsWith(url)){
			return true;
		}
		CmsUser cu = (CmsUser) SessionUtil.get(CarloanContant.CMSUSER);
		if(cu == null){
			if("html".equals(html)){
				if("parent".equals(parent)){
					response.getWriter().write(script2);
				}else{
					response.getWriter().write(script);
				}
			}else{
				SuperRes sr = new SuperRes(7);
				new ObjectMapper().writeValue(response.getOutputStream(), sr);
			}
			return false;
		}
		IUserService us = FindServiceUtil.findService(IUserService.class);
		/*ReqBo reqBo = new ReqBo();
		reqBo.setParam("roleId", cu.getRoleId());
		reqBo.setParam("url", reqUrl);
		if(!us.isAuth(reqBo).getResult()){
			if("html".equals(html)){
				response.getWriter().write("<h1>您没有权限执行该操作</h1>");
			}else{
				SuperRes sr = new SuperRes(8);
				new ObjectMapper().writeValue(response.getOutputStream(), sr);
			}
			return false;
		}*/
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
