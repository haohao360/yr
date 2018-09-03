package framework.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import framework.exception.BusinessException;
import framework.web.SuperRes;

public class DefaultExceptionResolver implements HandlerExceptionResolver{
	
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionResolver.class);
	
	private String errPage;
	
	private boolean onlyAjax;

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		log.error(ex.getMessage(), ex);
		if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))||onlyAjax){
			response.setHeader("Content-Type", "application/json");
			try{
				long errCode = 9999l;
				if(ex instanceof BusinessException){
					errCode = ((BusinessException)ex).getErrCode();
				}
				SuperRes sr = new SuperRes();
				sr.setSuccess(false);
				sr.setErrCode(errCode);
				sr.setErrMsg(ex.getMessage());
				(new ObjectMapper()).writeValue(response.getOutputStream(),sr);
			}catch(Exception e){
				log.error("DefaultExceptionResolver writer json error",e);
			}
			return null;
		}
		return new ModelAndView(errPage);
	}

	public String getErrPage() {
		return errPage;
	}

	public void setErrPage(String errPage) {
		this.errPage = errPage;
	}

	public boolean isOnlyAjax() {
		return onlyAjax;
	}

	public void setOnlyAjax(boolean onlyAjax) {
		this.onlyAjax = onlyAjax;
	}

}
