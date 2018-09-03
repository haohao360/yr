package framework.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import framework.exception.ApplicationException;
import framework.web.ReqBo;
import framework.web.ResBo;

public class MethodNameValidator implements MethodBeforeAdvice{
	private String regex;

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		if(!method.getName().matches(regex)){
			throw new ApplicationException("method name error:method = "+method.getName()+" success method name regex = "+regex);
		}
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	

}
