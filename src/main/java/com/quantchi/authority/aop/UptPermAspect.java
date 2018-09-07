package com.quantchi.authority.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UptPermAspect {

	private static final Logger logger = LoggerFactory.getLogger(UptPermAspect.class);

	public UptPermAspect(){
		System.out.print("******UptPermAspect is loading*****");
	}

	@Pointcut("execution(public * com.quantchi.intelquery.*.*.execsql*(..))")
	public void callUptMethod(){}

	@After("callUptMethod()")
	public void after() {
		System.out.println("method after");
	}

}
