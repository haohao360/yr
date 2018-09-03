package framework.web.aop;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import framework.jredis.IJredis;
import framework.web.session.CookieSessionAdapter;
import framework.web.session.JredisSessionAdapter;
import framework.web.session.Session;

public class DefaultCookieSessionListener implements HandlerInterceptor{

	private static Logger log = LoggerFactory.getLogger(DefaultCookieSessionListener.class);
	
	private IJredis redis;
	
	private int expire = 60*60*24;
	
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
		String token = null;
		CookieSessionAdapter cookieSession = new CookieSessionAdapter();
		CookieSessionAdapter.setCookieSession(request.getCookies());
		token = cookieSession.getC(this.token);
		Session session = null;
		if(token == null){
			session = createSession(token);
			Cookie cookie = new Cookie(this.token, session.getToken());
			cookie.setDomain(this.domain);
			cookie.setPath("/");
			cookie.setMaxAge(expire);
			response.addCookie(cookie);
		}else{
			session = redis.getValue(token, Session.class);
			if(session == null){
				session = createSession(token);
			}
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
		Cookie[] cookies = CookieSessionAdapter.getCookieSession();
		for(Cookie cookie:cookies){
			response.addCookie(cookie);
		}
		if(this.token != null){
			Session session = JredisSessionAdapter.getSession();
			redis.setValue(session.getToken(), session);
			JredisSessionAdapter.destorySession();
		}
	}
	
	private Session createSession(String token){
		if(StringUtils.isEmpty(token)){
			token = this.token + "_" + UUID.randomUUID().toString().replace("-", "");
		}
		Session session = new Session();
		session.setToken(token);
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
