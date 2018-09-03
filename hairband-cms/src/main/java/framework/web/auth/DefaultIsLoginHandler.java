package framework.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultIsLoginHandler implements IsLoginHandler{

	@Override
	public boolean handler(HttpServletRequest req, HttpServletResponse res,
			String value) {
		return true;
	}

}
