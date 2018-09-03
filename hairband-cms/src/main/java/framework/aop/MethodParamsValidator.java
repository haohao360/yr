package framework.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import framework.exception.ApplicationException;
import framework.web.ReqBo;
import framework.web.ResBo;

public class MethodParamsValidator implements MethodBeforeAdvice{
	
	@Override
	public void before(Method method, Object[] params, Object target)
			throws Throwable {
		
		if (params !=null && params.length !=0&&(params.length!=1||!(params[0] instanceof ReqBo)) ) {
			throw new ApplicationException("Service params must be ReqBo.class");
		}
		if(!method.getReturnType().equals(void.class)&&!method.getReturnType().equals(ResBo.class)){
			throw new ApplicationException("Service return must be ResBo.class");
		}
	}

}
