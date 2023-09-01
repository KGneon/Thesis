package com.kg.thesis.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.kg.thesis.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) throws Exception {
		String message = "After: " + exception.getMessage();
		LOGGER.error(message, exception);
	}
}
