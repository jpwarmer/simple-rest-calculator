package com.warmerdam.juan.simplecalculator.aspect;

import static com.warmerdam.juan.simplecalculator.model.CacheType.GROUPED_KEY;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.warmerdam.juan.simplecalculator.annotation.Cache;

@Aspect
@Configuration
public class CacheAspect {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CacheAspect.class);
	private ConcurrentHashMap<String, Object> customCache = new ConcurrentHashMap<>();
	
	@Pointcut("@annotation(cache)")
	public void beanAnnotated(Cache cache) {
	}
	
	@Around(value = "beanAnnotated(cache)")
	public Object aroundMethodController(final ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
		String key = getKey(joinPoint, cache);
		if (customCache.containsKey(key)) {
			LOGGER.info(String.format("Cache hit for %s", key));
			return customCache.get(key);
		}
		return joinPoint.proceed();
	}
	
	@AfterReturning(pointcut = "@annotation(cache)", returning = "result")
	public void afterMethodController(JoinPoint joinPoint, Cache cache, Object result) throws JsonProcessingException {
		String key = getKey(joinPoint, cache);
		if (!customCache.containsKey(key)) {
			LOGGER.info(String.format("New cached item under key %s", key));
			customCache.put(key, result);
		}
	}
	
	private String getKey(JoinPoint joinPoint, Cache cache) {
		LinkedList<String> keyTerms = new LinkedList<String>();
		for (Object arg: joinPoint.getArgs()) {
			if (arg != null) {
				keyTerms.add(arg + "");
			}
		}
		if (GROUPED_KEY.equals(cache.type())) {
			Collections.sort(keyTerms);
		}
		keyTerms.addFirst(joinPoint.getSignature().getName());
		return String.join("-", keyTerms);
	}
}
