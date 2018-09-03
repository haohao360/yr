package framework.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import framework.exception.ApplicationException;

public class ClassNameValidator implements MethodBeforeAdvice{

	private String regex;
	
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		if (!target.getClass().getName().matches(regex)) {
			throw new ApplicationException("class name error class matches regex = "+regex);
		}
		
	}
	
}
