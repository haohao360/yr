package framework.web.aop;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import framework.jredis.IJredis;
import framework.web.session.JredisSessionAdapter;
import framework.web.session.Session;

public class DefaultAppSessionListener implements HandlerInterceptor{
	
	private IJredis redis;
	
	private int expire = 60*60*24*1000;
	
	private String token;

	private ConcurrentHashMap<String,Object> reqParams;
	
	private String domain;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(this.domain != null){
			response.setHeader("Access-Control-Allow-Origin", this.domain);
		}
		if(reqParams != null && reqParams.size() > 0){
			for(String key:reqParams.keySet()){
				request.setAttribute(key, reqParams.get(key));
			}
		}
		String token = request.getParameter(this.token);
		Session session = null;
		if(StringUtils.isNotEmpty(token)){
			session = redis.getValue(token, Session.class);
		}
		if(session == null){
			session = createSession(token);
		}
		JredisSessionAdapter.setSession(session);
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
		Session session = JredisSessionAdapter.getSession();
		if(StringUtils.isNotEmpty(session.getToken())){
			redis.setValue(session.getToken(), session, expire);
		}
		JredisSessionAdapter.destorySession();
	}
	
	private Session createSession(String token){
		Session session = new Session();
		if(StringUtils.isNotEmpty(token)){
			session.setToken(token);
		}
		return session;
	}

	public ConcurrentHashMap<String,Object> getReqParams() {
		return reqParams;
	}

	public void setReqParams(ConcurrentHashMap<String,Object> reqParams) {
		this.reqParams = reqParams;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public IJredis getRedis() {
		return redis;
	}

	public void setRedis(IJredis redis) {
		this.redis = redis;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}
