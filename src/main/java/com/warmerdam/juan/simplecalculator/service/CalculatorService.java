package com.warmerdam.juan.simplecalculator.service;

import org.springframework.stereotype.Service;
import com.warmerdam.juan.simplecalculator.exception.CustomArithmeticException;

@Service
public class CalculatorService {
	
	public Double add(Double ... terms) {
		Double result = Double.valueOf(0);
		for (Double term: terms) {
			if (term != null) result += term;
		}
		
		return result;
	}
	
	public Double substract(Double a, Double b, Double c) {
		return c != null ? a - b - c : a - b;
	}
	
	public Double multiply(Double ... terms) {
		Double result = Double.valueOf(1);
		for (Double term: terms) {
			if (term != null) result *= term;
		}
		
		return result;
	}
	
	public Double divide(Double a, Double b) {
		if (b == 0) {
			throw new CustomArithmeticException("Division by zero");
		}
		return a / b;
	}
	
	public Double sqrt(Double a) {
		if (a < 0) {
			throw new CustomArithmeticException("Result is an imaginary number");
		}
		return Math.sqrt(a);
	}
	
	public Double abs(Double a) {
		return Math.abs(a);
	}
}
