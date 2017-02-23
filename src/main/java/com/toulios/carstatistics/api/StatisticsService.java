package com.toulios.carstatistics.api;

import java.io.File;
import java.util.List;

/**
 * Interface which handles the actions of the application
 */
public interface StatisticsService {
	/**
	 * 	The main service, which is responsible to load/read json file and operate the
	 * 	task with the provided task-number (1,2,3,4) or (5 to print all)
	 * 
	 * @param fileValue
	 * 		The provided file
	 * @param taskNumber
	 * 		The number of the task
	 * @return
	 * 		A list with the string representations of task with number the taskNumber. 
	 */
	public List<String> printTheTask(File fileValue, int taskNumber);
	

}
