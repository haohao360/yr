package framework.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IsLoginHandler {

	public boolean handler(HttpServletRequest req,HttpServletResponse res,String value);
}
