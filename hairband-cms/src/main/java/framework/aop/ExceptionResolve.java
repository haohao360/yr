package framework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.exception.BusinessException;
import framework.web.SuperRes;


public class ExceptionResolve implements MethodInterceptor{
	
	private Logger log = LoggerFactory.getLogger(ExceptionResolve.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			return invocation.proceed();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			SuperRes sr = (SuperRes) invocation.getMethod().getReturnType().newInstance();
			if (e instanceof BusinessException) {
				sr.setSuccess(false);
				sr.setErrCode(((BusinessException) e).getErrCode());
				sr.setErrMsg(e.getMessage());
			}else{
				sr.setSuccess(false);
				sr.setErrCode(9999L);
				sr.setErrMsg(e.getMessage());
			}
			return sr;
		}
	}
}
