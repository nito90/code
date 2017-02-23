package com.toulios.carstatistics.exceptions;

/**
 * 
 * Runtime exception for not found error in the execution of the task
 *
 */
public class StatisticsServiceValidationException extends RuntimeException{

	private static final long serialVersionUID = 2393877234980252780L;

	public StatisticsServiceValidationException(String message) {
		super(message);
	}
}
