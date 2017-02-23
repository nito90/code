package com.toulios.carstatistics.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toulios.carstatistics.TestApplciationConfiguration;

@WebAppConfiguration
public class CarStatisticsControllerTest extends TestApplciationConfiguration{

	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private WebApplicationContext webContext;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	public void successService() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		MvcResult result = mockMvc.perform(get("/car/statistics/tasks/1?filePath="+file.getAbsolutePath()))
			.andExpect(status().isOk())
			.andReturn();
		
		List<String> actualResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){
		});
		assertFalse(actualResult.isEmpty());
	}
	
	@Test
	public void testFailServiceWithNotExistedFile() throws Exception{
		File file = new File("failFile");
		mockMvc.perform(get("/car/statistics/tasks/5?filePath="+file.getAbsolutePath()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("result", equalTo(404)))
				.andReturn();
	}
	
	@Test
	public void testFailServiceJsonMappingError() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("carsValidationExceptionMappingError.json").getFile());
		mockMvc.perform(get("/car/statistics/tasks/5?filePath="+file.getAbsolutePath()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("result", equalTo(400)))
				.andReturn();
	}
	
	
	@Test
	public void testSuccessServiceEmptyList() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("emptyCarsInfo.json").getFile());
		
		MvcResult result = mockMvc.perform(get("/car/statistics/tasks/1?filePath="+file.getAbsolutePath()))
				.andExpect(status().isOk())
				.andReturn();
		
		List<String> actualResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){
		});
		assertTrue(actualResult.isEmpty());
	}
	
	@Test
	public void testSuccessServiceEmptyFile() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("emptyFile.json").getFile());
		
		MvcResult result = mockMvc.perform(get("/car/statistics/tasks/1?filePath="+file.getAbsolutePath()))
				.andExpect(status().isOk())
				.andReturn();
		
		List<String> actualResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>(){
		});
		assertTrue(actualResult.isEmpty());
	}
}
