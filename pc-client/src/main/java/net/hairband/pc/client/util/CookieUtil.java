package net.hairband.pc.client.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * 得到Cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			//先不判断域
			if (name.equals(cookies[i].getName())
					/*&& Constants.COOKIE_DOMAIN.equals(cookies[i].getDomain())*/) {
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 设置Cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value) {
		Cookie cookie = getCookie(request, name);
		if (cookie == null) {
			cookie = new Cookie(name, value == null ? "" : value);
		}else{
			cookie.setValue(value);
		}
		cookie.setMaxAge(60*60*24*30);
		cookie.setPath(Constants.PATH);
		cookie.setDomain(Constants.COOKIE_DOMAIN);
		response.addCookie(cookie);
	}
	
	/**
	 * 设置Cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 */
	public static void clearCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			cookie.setValue(null);
			cookie.setMaxAge(0);
			cookie.setPath(Constants.PATH);
			cookie.setDomain(Constants.COOKIE_DOMAIN);
			response.addCookie(cookie);
		}
}
}
