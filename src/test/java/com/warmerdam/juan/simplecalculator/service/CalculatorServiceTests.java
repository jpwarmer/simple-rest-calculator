package com.warmerdam.juan.simplecalculator.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.warmerdam.juan.simplecalculator.exception.CustomArithmeticException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorServiceTests {

	@Autowired
	private CalculatorService service;
	
	@Test
	public void testAdd() {
		assertTrue(Double.valueOf(4.0).equals(service.add(2.0 , 2.0)));
		assertTrue(Double.valueOf(12.0).equals(service.add(2.0 , 2.0,  8.0)));
		assertTrue(Double.valueOf(8.0).equals(service.add(6.0 , 2.0)));
		assertTrue(Double.valueOf(2.0).equals(service.add(4.0 , -2.0)));
		assertTrue(Double.valueOf(0).equals(service.add(2.0 , 2.0, -4.0)));
	}
	@Test
	public void testSubstract() {
		assertTrue(Double.valueOf(0).equals(service.substract(2.0 , 2.0,  null)));
		assertTrue(Double.valueOf(-8.0).equals(service.substract(2.0 , 2.0,  8.0)));
		assertTrue(Double.valueOf(4.0).equals(service.substract(6.0 , 2.0,  0.0)));
		assertTrue(Double.valueOf(6.0).equals(service.substract(4.0 , -2.0,  null)));
		assertTrue(Double.valueOf(4.0).equals(service.substract(2.0 , 2.0, -4.0)));
	}
	
	@Test
	public void testMultiply() {
		assertTrue(Double.valueOf(4.0).equals(service.multiply(2.0 , 2.0)));
		assertTrue(Double.valueOf(32.0).equals(service.multiply(2.0 , 2.0,  8.0)));
		assertTrue(Double.valueOf(12.0).equals(service.multiply(6.0 , 2.0)));
		assertTrue(Double.valueOf(-8.0).equals(service.multiply(4.0 , -2.0)));
		assertTrue(Double.valueOf(-16.0).equals(service.multiply(2.0 , 2.0, -4.0)));
	}
	
	@Test
	public void testDividedNoError() {
		assertTrue(Double.valueOf(4.0).equals(service.divide(8.0 , 2.0)));
		assertTrue(Double.valueOf(0.25).equals(service.divide(2.0,  8.0)));
		
	}
	
	@Test(expected = CustomArithmeticException.class)
	public void testDividedByZero() {
		service.divide(8.0 , 0.0);
	}
	
	@Test
	public void testSqrt() {
		assertTrue(Double.valueOf(4.0).equals(service.sqrt(16.0)));
		assertTrue(Double.valueOf(0).equals(service.sqrt(0.0)));
		
	}
	
	@Test(expected = CustomArithmeticException.class)
	public void testSqrtNegative() {
		service.sqrt(-16.0);
	}
	
	@Test
	public void testAbs() {
		assertTrue(service.abs(4.0).equals(service.abs(-4.0)));
	}
}
