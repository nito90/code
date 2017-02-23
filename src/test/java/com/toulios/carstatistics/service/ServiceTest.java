package com.toulios.carstatistics.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toulios.carstatistics.TestApplciationConfiguration;
import com.toulios.carstatistics.api.StatisticsService;
import com.toulios.carstatistics.exceptions.StatisticsServiceFileNotFoundException;
import com.toulios.carstatistics.exceptions.StatisticsServiceValidationException;

/**
 * Created by ntoulios on 22/2/2017.
 */
public class ServiceTest extends TestApplciationConfiguration{

	@Autowired
	private StatisticsService service;

	@Test(expected = StatisticsServiceFileNotFoundException.class)
	public void testFailServiceWithNotExistedFile() throws Exception{
		File file = new File("failFile");
		service.printTheTask(file, 5);
	}
	
	@Test(expected = StatisticsServiceValidationException.class)
	public void testFailServiceJsonMappingError() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("carsValidationExceptionMappingError.json").getFile());
		service.printTheTask(file, 5);
	}
	
	@Test(expected = StatisticsServiceValidationException.class)
	public void testSuccessService2() throws Exception{
		service.printTheTask(null, 5);
	}
	
	@Test
	public void testSuccessService() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		List<String> result = service.printTheTask(file, 5);
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void testSuccessServiceEmptyList() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("emptyCarsInfo.json").getFile());
		
		List<String> result = service.printTheTask(file, 5);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testSuccessServiceEmptyFile() throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("emptyFile.json").getFile());
		
		List<String> result = service.printTheTask(file, 5);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testFirstask(){
		List<String> results = Arrays.asList(  "ChevroletSpark-120.16",
				  "Kia Picanto-136.57",
				  "Vauxhall Corsa-139.93",
				  "Citroen C4-146.7",
				  "Ford Focus-157.85",
				  "Ford Focus-157.85",
				  "VW Golf-188.97",
				  "VW Golf-209.97",
				  "VW Golf-209.97",
				  "Skoda Fabia-219.71",
				  "Skoda Fabia-219.71",
				  "Chevrolet Cruze-229.45",
				  "Audi A1-234.76",
				  "Skoda Octavia-236.24",
				  "VW Golf Estate-239.56",
				  "Skoda Fabia-240.71",
				  "Kia Ceed Estate-311.03",
				  "Kia Ceed Estate-311.03",
				  "Vauxhall Zafira-319.63",
				  "Vauxhall Zafira-323.63",
				  "Ford Mondeo-339.31",
				  "Nissan Juke-356.28",
				  "Ford Mondeo-365.31",
				  "Ford Mondeo-365.31",
				  "Nissan Juke-463.56",
				  "VW Passat Estate-469.37",
				  "VW Jetta-508.96",
				  "VW Jetta-508.96",
				  "Ford Galaxy-706.89",
				  "VW Sharan-753.75",
				  "VW Sharan-789.75");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		List<String> actualResults = service.printTheTask(file, 1);
		assertEquals(actualResults,results);
	}
	
	@Test
	public void testSecondtask(){
		List<String> results = Arrays.asList( "Ford Focus-CDMR-Compact-5 doors-Manual-Petrol-AC",
				  "Ford Galaxy-FVAR-Full size-Passenger Van-Automatic-Petrol-AC",
				  "VW Passat Estate-IWMR-Intermediate-Estate-Manual-Petrol-AC",
				  "Kia Ceed Estate-CWMR-Compact-Estate-Manual-Petrol-AC",
				  "VW Jetta-IDAR-Intermediate-5 doors-Automatic-Petrol-AC",
				  "Nissan Juke-CCAR-Compact-4 doors-Automatic-Petrol-AC",
				  "Ford Mondeo-SDMR-Standard-5 doors-Manual-Petrol-AC",
				  "Skoda Octavia-IDMR-Intermediate-5 doors-Manual-Petrol-AC",
				  "Vauxhall Corsa-ECMR-Economy-4 doors-Manual-Petrol-AC",
				  "Kia Picanto-MBMN-Mini-2 doors-Manual-Petrol-no AC",
				  "ChevroletSpark-ECMR-Economy-4 doors-Manual-Petrol-AC",
				  "Audi A1-CCMR-Compact-4 doors-Manual-Petrol-AC",
				  "Citroen C4-CDMR-Compact-5 doors-Manual-Petrol-AC",
				  "VW Golf Estate-CWMR-Compact-Estate-Manual-Petrol-AC",
				  "Chevrolet Cruze-IDMR-Intermediate-5 doors-Manual-Petrol-AC",
				  "Skoda Fabia-CDAR-Compact-5 doors-Automatic-Petrol-AC",
				  "VW Golf-CXMR-Compact-Special-Manual-Petrol-AC",
				  "Vauxhall Zafira-IVMR-Intermediate-Passenger Van-Manual-Petrol-AC",
				  "VW Sharan-FVMR-Full size-Passenger Van-Manual-Petrol-AC",
				  "Skoda Fabia-CDAR-Compact-5 doors-Automatic-Petrol-AC",
				  "VW Golf-CXMR-Compact-Special-Manual-Petrol-AC",
				  "Vauxhall Zafira-IVMR-Intermediate-Passenger Van-Manual-Petrol-AC",
				  "VW Sharan-FVMR-Full size-Passenger Van-Manual-Petrol-AC",
				  "VW Jetta-IDAR-Intermediate-5 doors-Automatic-Petrol-AC",
				  "Nissan Juke-CCAR-Compact-4 doors-Automatic-Petrol-AC",
				  "Ford Mondeo-SDMR-Standard-5 doors-Manual-Petrol-AC",
				  "Ford Focus-CDMR-Compact-5 doors-Manual-Petrol-AC",
				  "VW Golf-CXMR-Compact-Special-Manual-Petrol-AC",
				  "Ford Mondeo-SDMR-Standard-5 doors-Manual-Petrol-AC",
				  "Skoda Fabia-CDAR-Compact-5 doors-Automatic-Petrol-AC",
				  "Kia Ceed Estate-CWMR-Compact-Estate-Manual-Petrol-AC");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		List<String> actualResults = service.printTheTask(file, 2);
		assertEquals(actualResults,results);
	}
	
	@Test
	public void testThirdtask(){
		List<String> results = Arrays.asList(  "Kia Picanto-Mini-Hertz-8.9",
				  "Vauxhall Corsa-Economy-Hertz-8.9",
				  "VW Passat Estate-Intermediate-Hertz-8.9",
				  "Ford Galaxy-Full size-Hertz-8.9",
				  "Ford Mondeo-Standard-Hertz-8.9",
				  "Ford Focus-Compact-Hertz-8.9");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		List<String> actualResults = service.printTheTask(file, 3);
		assertEquals(actualResults,results);
	}
	
	@Test
	public void testFourthtask(){
		List<String> results = Arrays.asList(  "Ford Galaxy-7-8.9-15.9",
				  "VW Jetta-7-8.9-15.9",
				  "Nissan Juke-7-8.9-15.9",
				  "Skoda Fabia-7-8.2-15.2",
				  "Skoda Fabia-7-8.0-15.0",
				  "VW Jetta-7-8.0-15.0",
				  "Nissan Juke-7-8.0-15.0",
				  "Skoda Fabia-7-7.8-14.8",
				  "Ford Focus-3-8.9-11.9",
				  "VW Passat Estate-3-8.9-11.9",
				  "Kia Ceed Estate-3-8.9-11.9",
				  "Ford Mondeo-3-8.9-11.9",
				  "Skoda Octavia-3-8.9-11.9",
				  "Vauxhall Corsa-3-8.9-11.9",
				  "ChevroletSpark-3-8.2-11.2",
				  "Audi A1-3-8.2-11.2",
				  "Citroen C4-3-8.2-11.2",
				  "VW Golf Estate-3-8.2-11.2",
				  "Chevrolet Cruze-3-8.2-11.2",
				  "VW Golf-3-8.2-11.2",
				  "Vauxhall Zafira-3-8.2-11.2",
				  "VW Sharan-3-8.2-11.2",
				  "VW Golf-3-8.0-11.0",
				  "Vauxhall Zafira-3-8.0-11.0",
				  "VW Sharan-3-8.0-11.0",
				  "Ford Mondeo-3-8.0-11.0",
				  "Ford Focus-3-7.8-10.8",
				  "VW Golf-3-7.8-10.8",
				  "Ford Mondeo-3-7.8-10.8",
				  "Kia Ceed Estate-3-7.8-10.8",
				  "Kia Picanto-1-8.9-9.9");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		List<String> actualResults = service.printTheTask(file, 4);
		assertEquals(actualResults,results);
	}
	
	@Test
	public void testDefaulttask(){
		List<String> results = Arrays.asList(  
				  "Task 1",
				  "ChevroletSpark-120.16",
				  "Kia Picanto-136.57",
				  "Vauxhall Corsa-139.93",
				  "Citroen C4-146.7",
				  "Ford Focus-157.85",
				  "Ford Focus-157.85",
				  "VW Golf-188.97",
				  "VW Golf-209.97",
				  "VW Golf-209.97",
				  "Skoda Fabia-219.71",
				  "Skoda Fabia-219.71",
				  "Chevrolet Cruze-229.45",
				  "Audi A1-234.76",
				  "Skoda Octavia-236.24",
				  "VW Golf Estate-239.56",
				  "Skoda Fabia-240.71",
				  "Kia Ceed Estate-311.03",
				  "Kia Ceed Estate-311.03",
				  "Vauxhall Zafira-319.63",
				  "Vauxhall Zafira-323.63",
				  "Ford Mondeo-339.31",
				  "Nissan Juke-356.28",
				  "Ford Mondeo-365.31",
				  "Ford Mondeo-365.31",
				  "Nissan Juke-463.56",
				  "VW Passat Estate-469.37",
				  "VW Jetta-508.96",
				  "VW Jetta-508.96",
				  "Ford Galaxy-706.89",
				  "VW Sharan-753.75",
				  "VW Sharan-789.75",
				  "Task 2",
				  "Ford Focus-CDMR-Compact-5 doors-Manual-Petrol-AC",
				  "Ford Galaxy-FVAR-Full size-Passenger Van-Automatic-Petrol-AC",
				  "VW Passat Estate-IWMR-Intermediate-Estate-Manual-Petrol-AC",
				  "Kia Ceed Estate-CWMR-Compact-Estate-Manual-Petrol-AC",
				  "VW Jetta-IDAR-Intermediate-5 doors-Automatic-Petrol-AC",
				  "Nissan Juke-CCAR-Compact-4 doors-Automatic-Petrol-AC",
				  "Ford Mondeo-SDMR-Standard-5 doors-Manual-Petrol-AC",
				  "Skoda Octavia-IDMR-Intermediate-5 doors-Manual-Petrol-AC",
				  "Vauxhall Corsa-ECMR-Economy-4 doors-Manual-Petrol-AC",
				  "Kia Picanto-MBMN-Mini-2 doors-Manual-Petrol-no AC",
				  "ChevroletSpark-ECMR-Economy-4 doors-Manual-Petrol-AC",
				  "Audi A1-CCMR-Compact-4 doors-Manual-Petrol-AC",
				  "Citroen C4-CDMR-Compact-5 doors-Manual-Petrol-AC",
				  "VW Golf Estate-CWMR-Compact-Estate-Manual-Petrol-AC",
				  "Chevrolet Cruze-IDMR-Intermediate-5 doors-Manual-Petrol-AC",
				  "Skoda Fabia-CDAR-Compact-5 doors-Automatic-Petrol-AC",
				  "VW Golf-CXMR-Compact-Special-Manual-Petrol-AC",
				  "Vauxhall Zafira-IVMR-Intermediate-Passenger Van-Manual-Petrol-AC",
				  "VW Sharan-FVMR-Full size-Passenger Van-Manual-Petrol-AC",
				  "Skoda Fabia-CDAR-Compact-5 doors-Automatic-Petrol-AC",
				  "VW Golf-CXMR-Compact-Special-Manual-Petrol-AC",
				  "Vauxhall Zafira-IVMR-Intermediate-Passenger Van-Manual-Petrol-AC",
				  "VW Sharan-FVMR-Full size-Passenger Van-Manual-Petrol-AC",
				  "VW Jetta-IDAR-Intermediate-5 doors-Automatic-Petrol-AC",
				  "Nissan Juke-CCAR-Compact-4 doors-Automatic-Petrol-AC",
				  "Ford Mondeo-SDMR-Standard-5 doors-Manual-Petrol-AC",
				  "Ford Focus-CDMR-Compact-5 doors-Manual-Petrol-AC",
				  "VW Golf-CXMR-Compact-Special-Manual-Petrol-AC",
				  "Ford Mondeo-SDMR-Standard-5 doors-Manual-Petrol-AC",
				  "Skoda Fabia-CDAR-Compact-5 doors-Automatic-Petrol-AC",
				  "Kia Ceed Estate-CWMR-Compact-Estate-Manual-Petrol-AC",
				  "Task 3",
				  "Kia Picanto-Mini-Hertz-8.9",
				  "Vauxhall Corsa-Economy-Hertz-8.9",
				  "VW Passat Estate-Intermediate-Hertz-8.9",
				  "Ford Galaxy-Full size-Hertz-8.9",
				  "Ford Mondeo-Standard-Hertz-8.9",
				  "Ford Focus-Compact-Hertz-8.9",
				  "Task 4",
				  "Ford Galaxy-7-8.9-15.9",
				  "VW Jetta-7-8.9-15.9",
				  "Nissan Juke-7-8.9-15.9",
				  "Skoda Fabia-7-8.2-15.2",
				  "Skoda Fabia-7-8.0-15.0",
				  "VW Jetta-7-8.0-15.0",
				  "Nissan Juke-7-8.0-15.0",
				  "Skoda Fabia-7-7.8-14.8",
				  "Ford Focus-3-8.9-11.9",
				  "VW Passat Estate-3-8.9-11.9",
				  "Kia Ceed Estate-3-8.9-11.9",
				  "Ford Mondeo-3-8.9-11.9",
				  "Skoda Octavia-3-8.9-11.9",
				  "Vauxhall Corsa-3-8.9-11.9",
				  "ChevroletSpark-3-8.2-11.2",
				  "Audi A1-3-8.2-11.2",
				  "Citroen C4-3-8.2-11.2",
				  "VW Golf Estate-3-8.2-11.2",
				  "Chevrolet Cruze-3-8.2-11.2",
				  "VW Golf-3-8.2-11.2",
				  "Vauxhall Zafira-3-8.2-11.2",
				  "VW Sharan-3-8.2-11.2",
				  "VW Golf-3-8.0-11.0",
				  "Vauxhall Zafira-3-8.0-11.0",
				  "VW Sharan-3-8.0-11.0",
				  "Ford Mondeo-3-8.0-11.0",
				  "Ford Focus-3-7.8-10.8",
				  "VW Golf-3-7.8-10.8",
				  "Ford Mondeo-3-7.8-10.8",
				  "Kia Ceed Estate-3-7.8-10.8",
				  "Kia Picanto-1-8.9-9.9");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("cars.json").getFile());
		
		List<String> actualResults = service.printTheTask(file, 5);
		assertEquals(actualResults,results);
	}
}
