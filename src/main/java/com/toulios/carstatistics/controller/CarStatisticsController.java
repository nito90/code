package com.toulios.carstatistics.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toulios.carstatistics.api.Response;
import com.toulios.carstatistics.api.StatisticsService;
import com.toulios.carstatistics.exceptions.StatisticsServiceFileNotFoundException;
import com.toulios.carstatistics.exceptions.StatisticsServiceValidationException;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest controller to handle the requests and the mappings.
 */

@RestController
@RequestMapping(value = "/car/statistics")
public class CarStatisticsController {

	@Autowired
	private StatisticsService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@ApiResponses({
		@ApiResponse(code = 200, message = "Returns list of the tasks's printings"),
		@ApiResponse(code = 400, message = "Validation/mapping problem of the json"),
		@ApiResponse(code = 404, message = "The provided file not found")
	})
	@RequestMapping(value = "/tasks/{num}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getStatisticsPerTask (@PathVariable("num") Integer taskNumber,
			@RequestParam(value = "filePath", required = true) String filePath) throws Exception {
		
		File fileValue = new File(filePath);
		return mapper.writeValueAsString(service.printTheTask(fileValue, taskNumber));
	}
	
	@ExceptionHandler(StatisticsServiceValidationException.class)
	public Response invalid(Exception e, HttpServletResponse response) throws IOException {
		return new Response(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	@ExceptionHandler(StatisticsServiceFileNotFoundException.class)
	public Response NotFound(Exception e, HttpServletResponse response) throws IOException {
		return new Response(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
}
