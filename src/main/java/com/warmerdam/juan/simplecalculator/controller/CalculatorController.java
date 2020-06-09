package com.warmerdam.juan.simplecalculator.controller;

import static com.warmerdam.juan.simplecalculator.model.CacheType.GROUPED_KEY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.warmerdam.juan.simplecalculator.annotation.Cache;
import com.warmerdam.juan.simplecalculator.model.Result;
import com.warmerdam.juan.simplecalculator.service.CalculatorService;

@RestController
public class CalculatorController {

  	@Autowired
	private CalculatorService service;
	
	
	@GetMapping({"/add/{a}/{b}/{c}", "/add/{a}/{b}"})
	@Cache(type = GROUPED_KEY)
	public Result add(@PathVariable(value = "a") Double a,
					  @PathVariable(value = "b") Double b,
					  @PathVariable(value = "c", required = false) Double c) {
		return new Result(service.add(a, b, c));
	}
	
	@GetMapping({"/substract/{a}/{b}/{c}", "/substract/{a}/{b}"})
	@Cache
	public Result substract(@PathVariable(value = "a") Double a,
					  @PathVariable(value = "b") Double b,
					  @PathVariable(value = "c", required = false) Double c) {
		return new Result(service.substract(a, b, c));
	}
	
	@GetMapping({"/multiply/{a}/{b}/{c}", "/multiply/{a}/{b}"})
	@Cache(type = GROUPED_KEY)
	public Result multiply(@PathVariable(value = "a") Double a,
					  @PathVariable(value = "b") Double b,
					  @PathVariable(value = "c", required = false) Double c) {
		return new Result(service.multiply(a, b, c));
	}
	
	@GetMapping("/divide/{a}/{b}")
	@Cache
	public Result divide(@PathVariable(value = "a") Double a,
						 @PathVariable(value = "b") Double b) {
		return new Result(service.divide(a, b));
	}
	
	@GetMapping("/sqrt/{a}")
	@Cache
	public Result sqrt(@PathVariable(value = "a") Double a) {
		return new Result(service.sqrt(a));
	}
	
	@GetMapping("/abs/{a}")
	@Cache
	public Result abs(@PathVariable(value = "a") Double a) {
		return new Result(service.abs(a));
	}
	
	
}
