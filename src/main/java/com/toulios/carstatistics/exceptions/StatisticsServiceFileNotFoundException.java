package com.toulios.carstatistics.exceptions;

/**
 * 
 * Runtime exception for validation error in the execution of the task
 *
 */
public class StatisticsServiceFileNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2545488228734191212L;

	public StatisticsServiceFileNotFoundException(String message) {
		super(message);
	}

}
