package framework.web.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import framework.web.session.HttpSessionAdapter;

public class DefaultHttpSessionListener implements HandlerInterceptor{
	
	private String staUrl;
	private String dynUrl;
	private String rootUrl;
	private String imgUrl;
	
	public String getStaUrl() {
		return staUrl;
	}

	public void setStaUrl(String staUrl) {
		this.staUrl = staUrl;
	}

	public String getDynUrl() {
		return dynUrl;
	}

	public void setDynUrl(String dynUrl) {
		this.dynUrl = dynUrl;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpSessionAdapter.setHttpSession(request.getSession(true));
		request.setAttribute("staUrl", staUrl);
		request.setAttribute("dynUrl", dynUrl);
		request.setAttribute("rootUrl", rootUrl);
		request.setAttribute("imgUrl", imgUrl);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HttpSessionAdapter.destoryHttpSession();
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
