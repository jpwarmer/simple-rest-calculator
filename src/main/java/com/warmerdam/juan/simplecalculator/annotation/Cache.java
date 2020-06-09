package com.warmerdam.juan.simplecalculator.annotation;

import static com.warmerdam.juan.simplecalculator.model.CacheType.UNIQUE_KEY;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.warmerdam.juan.simplecalculator.model.CacheType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
	
	CacheType type() default UNIQUE_KEY;
}
