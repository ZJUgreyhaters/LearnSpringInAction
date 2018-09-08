package com.quantchi.authority.aop;

import com.quantchi.authority.shiro.ShiroService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UptPermAspect {

	private static final Logger logger = LoggerFactory.getLogger(UptPermAspect.class);

	@Autowired
	ShiroService shiroService;

	public UptPermAspect(){
		System.out.print("******UptPermAspect is loading*****");
	}

	@Pointcut("execution(public * com.quantchi.authority.serviceImpl.*.addAuthAndDataDetail(..))")
	public void callUptMethod(){}

	@After("callUptMethod()")
	public void after() {
		System.out.println(" addAuthAndDataDetail method after");
		shiroService.updatePermission();
	}

}
