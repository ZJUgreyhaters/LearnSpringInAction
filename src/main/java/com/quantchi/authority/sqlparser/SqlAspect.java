package com.quantchi.authority.sqlparser;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SqlAspect {

	private static final Logger logger = LoggerFactory.getLogger(SqlAspect.class);

	public SqlAspect(){
		System.out.print("******SqlAspect is loading*****");
	}

	@Pointcut("execution(public * com.quantchi.intelquery.*.*.execsql*(..))")
	public void myMethod(){}

	@Around("myMethod()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		args[0] = modifySqlByDataAuth(args[0].toString());
		Object retVal = pjp.proceed(args);
		return retVal;
	}

	private String modifySqlByDataAuth(String sql){
		sql = sql + " limit 5";
		return sql;
	}

}
