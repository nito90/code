package com.toulios.carstatistics.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toulios.carstatistics.api.HelperMappings;
import com.toulios.carstatistics.api.StatisticsService;
import com.toulios.carstatistics.exceptions.StatisticsServiceFileNotFoundException;
import com.toulios.carstatistics.exceptions.StatisticsServiceValidationException;
import com.toulios.carstatistics.model.Task;
import com.toulios.carstatistics.model.VehicleInfo;

/**
 * Created by ntoulios on 22/2/2017.
 */
@Service
public class StatisticsServiceIml implements StatisticsService {

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 *	Prints all the tasks
	 *
	 * @param tasksInfo
	 * 		List which holds the string representation of the services execution
	 * @param vehicles
	 * 		List which holds the information for each vehicle
	 */
	private void printAllTheTasks(List<String> tasksInfo, List<VehicleInfo> vehicles) {

		tasksInfo.add("Task 1");
		printAscendingPriceOrder(tasksInfo, vehicles);
		tasksInfo.add("Task 2");
		printvehicleSpecificationFromSIPP(tasksInfo, vehicles);
		tasksInfo.add("Task 3");
		printTheHighestRatedSupplierPerCarTypeInDescendingOrder(tasksInfo,
				vehicles);
		tasksInfo.add("Task 4");
		vehicleScoresOrderedPerScore(tasksInfo, vehicles);
	}

	/**
	 *	Print a list of all the cars, in ascending price order, in the following format:
	 *		
	 *		{Vehicle name} – {Price}
	 *
	 * @param tasksInfo
	 * 		List which holds the string representation of the services execution
	 * @param vehicles
	 * 		List which holds the information for each vehicle
	 */
	private void printAscendingPriceOrder(List<String> tasksInfo, List<VehicleInfo> vehicles) {

		// Sort by price in ascending order
		vehicles.stream()
		.sorted(
				(VehicleInfo vehicle1, VehicleInfo vehicle2) -> vehicle1.getPrice().compareTo(vehicle2.getPrice()))
		.forEach(vehicle -> {
			StringBuilder result = new StringBuilder();
			result.append(vehicle.getName());
			result.append("-");
			result.append(vehicle.getPrice());
			tasksInfo.add(result.toString());
		});
	}

	/**
	 *	Using the table below, calculate the specification of the vehicles based on their SIPP. Print the specification out using the following format:
	 *		
	 *		{Vehicle name} – {SIPP} – {Car type} – {Car type/doors} – {Transmission} – {Fuel} – {Air con}
	 *
	 * @param tasksInfo
	 * 		List which holds the string representation of the services execution
	 * @param vehicles
	 * 		List which holds the information for each vehicle
	 */
	private void printvehicleSpecificationFromSIPP(List<String> tasksInfo, List<VehicleInfo> vehicles) {
		vehicles.forEach(vehicle -> {
			vehicle.evaluateSIPP();
			StringBuilder result = new StringBuilder();
			result.append(vehicle.getVehicleSpecification());
			tasksInfo.add(result.toString());
		});
	}

	/**
	 *	Print out the highest rated supplier per car type, descending order, in the following format:
	 *		
	 *		{Vehicle name} – {Car type} – {Supplier} – {Rating}
	 *
	 * @param tasksInfo
	 * 		List which holds the string representation of the services execution
	 * @param vehicles
	 * 		List which holds the information for each vehicle
	 */
	private void printTheHighestRatedSupplierPerCarTypeInDescendingOrder(List<String> tasksInfo,
			List<VehicleInfo> vehicles) {

		vehicles.stream()
				.collect(Collectors.groupingBy(VehicleInfo::getCarType,
						Collectors.maxBy(Comparator.comparing(VehicleInfo::getRating))))
				.values().stream().sorted((Optional<VehicleInfo> info1, Optional<VehicleInfo> info2) -> info2.get()
						.getRating().compareTo(info1.get().getRating()))
				.forEach((value) -> {
					StringBuilder builder = new StringBuilder();
					if (value.isPresent()) {
						builder.append(value.get().getName());
						builder.append("-");
						builder.append(value.get().getCarType());
						builder.append("-");
						builder.append(value.get().getSupplier());
						builder.append("-");
						builder.append(value.get().getRating());
						tasksInfo.add(builder.toString());
					}

				});
	}

	/**
	 *	Give each vehicle a score based on the below breakdown, then combine this score with the suppliers rating. Print out a list of vehicles, 
	 *	ordered by the sum of the scores in descending order, in the following format:
	 *		
	 *		{Vehicle name} – {Vehicle score} – {Supplier rating} – {Sum of scores}
	 *
	 * @param tasksInfo
	 * 		List which holds the string representation of the services execution
	 * @param vehicles
	 * 		List which holds the information for each vehicle
	 */
	private void vehicleScoresOrderedPerScore(List<String> tasksInfo, List<VehicleInfo> vehicles) {
		vehicles.forEach(vehicle -> {
			vehicle.evaluateSIPP();
			Integer vehicleScore = HelperMappings.POINTS.get(vehicle.getTransmission())
					+ HelperMappings.POINTS.get(vehicle.getAircondition());
			vehicle.setVehicleRating(vehicleScore);
			vehicle.setSumScore(vehicleScore + vehicle.getRating());
		});

		vehicles.stream().sorted(Comparator.comparing(VehicleInfo::getSumScore).reversed()).forEach(vehicle -> {
			StringBuilder builder = new StringBuilder();
			builder.append(vehicle.getName());

			builder.append("-");
			builder.append(vehicle.getVehicleRating());
			builder.append("-");
			builder.append(vehicle.getRating());
			builder.append("-");
			builder.append(vehicle.getSumScore());
			tasksInfo.add(builder.toString());
		});
	}

	@Override
	public List<String> printTheTask(File fileValue, int taskNumber) {
		if(fileValue == null){
			throw new StatisticsServiceValidationException("You must provide a file");
		}
		List<String> tasksInfo = new ArrayList<>();
		Task searchValues;

		try {
			//Read the JSon file and use the Task object to de-serialize it.
			searchValues = mapper.readValue(fileValue, Task.class);
			
			if(searchValues.getSearch() != null){
				if(!searchValues.getSearch().getVehicleList().isEmpty()){
					searchValues.getSearch().getVehicleList().forEach(vehicle -> vehicle.evaluateSIPP());
					
					switch (taskNumber){
					case 1:
						printAscendingPriceOrder(tasksInfo, searchValues.getSearch().getVehicleList());
						break;
					case 2:
						printvehicleSpecificationFromSIPP(tasksInfo, searchValues.getSearch().getVehicleList());
						break;
					case 3:
						printTheHighestRatedSupplierPerCarTypeInDescendingOrder(tasksInfo,
								searchValues.getSearch().getVehicleList());
						break;
					case 4:
						vehicleScoresOrderedPerScore(tasksInfo, searchValues.getSearch().getVehicleList());
						break;
					default:
						printAllTheTasks(tasksInfo, searchValues.getSearch().getVehicleList());
						break;
					}
				}
			}

		} catch (JsonParseException e) {
			throw new StatisticsServiceValidationException("Problem with the json file's parsing");
		} catch (JsonMappingException e) {
			throw new StatisticsServiceValidationException("Problem with the json file's mapping.");
		} catch (IOException e) {
			throw new StatisticsServiceFileNotFoundException("Problem with the loading of the file");
		}

		return tasksInfo;
	}
}
