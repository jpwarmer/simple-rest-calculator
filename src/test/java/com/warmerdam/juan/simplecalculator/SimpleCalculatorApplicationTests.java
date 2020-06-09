package com.warmerdam.juan.simplecalculator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warmerdam.juan.simplecalculator.model.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SimpleCalculatorApplicationTests {
	
	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	private <T> T mapFromJson(String json, Class<T> clazz) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	private void testGETMethodWithoutError(String uri, int expectedStatus, Double expectedResult ) throws Exception {
		MockHttpServletResponse httpResponse = makeCallToService(uri);
		
		assertEquals(expectedStatus, httpResponse.getStatus());
		
		Result result = mapFromJson(httpResponse.getContentAsString(), Result.class);
		assertTrue(result.getResult().equals(expectedResult));
	}
	
	
	private void testGETMethodWithError(String uri, int expectedStatus ) throws Exception {
		MockHttpServletResponse httpResponse = makeCallToService(uri);
		
		assertEquals(expectedStatus, httpResponse.getStatus());
	}
	
	private MockHttpServletResponse makeCallToService(String uri) throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		return mvcResult.getResponse();
	}
	
	@Test
	public void getAdd() throws Exception {
		testGETMethodWithoutError("/add/2/2", 200, 4.0);
	}
	@Test
	public void getAddThreeNumbers() throws Exception {
		testGETMethodWithoutError("/add/2/2/4", 200, 8.0);
	}
	
	
	@Test
	public void getMultiply() throws Exception {
		testGETMethodWithoutError("/multiply/8/2", 200, 16.0);
	}
	@Test
	public void getMultiplyThreeNumbers() throws Exception {
		testGETMethodWithoutError("/multiply/2/5/4", 200, 40.0);
	}
	
	
	@Test
	public void getSusbtract() throws Exception {
		testGETMethodWithoutError("/substract/8/2", 200, 6.0);
	}
	
	@Test
	public void getSusbtractAnotherOrder() throws Exception {
		testGETMethodWithoutError("/substract/2/8", 200, -6.0);
	}
	@Test
	public void getSubstractThreeNumbers() throws Exception {
		testGETMethodWithoutError("/substract/2/5/4", 200, -7.0);
	}
	
	@Test
	public void getSubstractThreeNumbersAnotherOrder() throws Exception {
		testGETMethodWithoutError("/substract/5/2/4", 200, -1.0);
	}
	
	@Test
	public void getDivide() throws Exception {
		testGETMethodWithoutError("/divide/8/2", 200, 4.0);
	}
	
	@Test
	public void getDivideAnotherOrder() throws Exception {
		testGETMethodWithoutError("/divide/2/4", 200, 0.5);
	}
	
	@Test
	public void getSqrt() throws Exception {
		testGETMethodWithoutError("/sqrt/25", 200, 5.0);
	}
	@Test
	public void getSqrtNegative() throws Exception {
		testGETMethodWithError("/sqrt/-25", 500);
	}
	@Test
	public void getAbs() throws Exception {
		testGETMethodWithoutError("/abs/25", 200, 25.0);
	}
	@Test
	public void getAbsNegativeValue() throws Exception {
		testGETMethodWithoutError("/abs/-25", 200, 25.0);
	}
	
	
	@Test
	public void getDivideByZero() throws Exception {
		testGETMethodWithError("/divide/2/0", 500);
	}
	
	@Test
	public void getBadRequest() throws Exception {
		testGETMethodWithError("/add/2as/asd0", 400);
	}
	
}
