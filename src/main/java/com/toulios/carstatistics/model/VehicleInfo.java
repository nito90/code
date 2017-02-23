package com.toulios.carstatistics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toulios.carstatistics.api.HelperMappings;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents the information for each vehicle
 */
public class VehicleInfo implements Serializable, HelperMappings{

	private static final long serialVersionUID = 9052667206757562014L;

	/**
	 * Helper mapping to evaluate the SIPP code for each vehicle
	 */
	private final static List<Map<Character, String>> SIPP_INFORMATION = createTheSIPPMap();
	
	/**
	 * Helper values to avoid magic-numbers
	 */
	private final static Integer SIPP_LENGTH = 4;
	private final static Integer CAR_TYPE_INDEX = 0;
	private final static Integer CAR_DOOR_INDEX = 1;
	private final static Integer TRANSMISSION_INDEX = 2;
	private final static Integer FUEL_AIR_INDEX = 3;
	
	/**
	 * Vehicle's SIPP code
	 */
	private String sipp;
	
	/**
	 * Vehicle's name
	 */
	private String name;
	
	/**
	 * Vehicle's price
	 */
	private Double price;
	
	/**
	 * Vehicle's supplier
	 */
	private String supplier;
	
	/**
	 * supplier's rating
	 */
	private Double rating;

	/**
	 * Vehicle's fuel system
	 */
	@JsonIgnore
	private String fuel;

	/**
	 * Vehicle's AC system
	 */
	@JsonIgnore
	private String aircondition;
	
	/**
	 * Vehicle's rating
	 */
	@JsonIgnore
	private Integer vehicleRating;

	/**
	 * Vehicles score vehicleRating + supplier's rating
	 */
	@JsonIgnore
	private Double sumScore;
	
	/**
	 * Vehicle's transmission system
	 */
	@JsonIgnore
	private String transmission;
	
	/**
	 * Vehicle's type
	 */
	@JsonIgnore
	private String carType;
	
	/**
	 * Vehicle's door type
	 */
	@JsonIgnore
	private String carTypeDoor;
	
	public VehicleInfo(){
		
	}
	
	public VehicleInfo(String sipp, String name, Double price, String supplier, Double rating) {
		this.sipp = sipp;
		this.name = name;
		this.price = price;
		this.supplier = supplier;
		this.rating = rating;
	}

	public String getSipp() {
		return sipp;
	}

	public void setSipp(String sipp) {
		this.sipp = sipp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getAircondition() {
		return aircondition;
	}

	public void setAircondition(String aircondition) {
		this.aircondition = aircondition;
	}
	
	public String getVehicleSpecification(){
		StringBuilder builder = new StringBuilder();
		builder.append(this.name);
		builder.append('-');
		builder.append(this.sipp);
		builder.append('-');
		builder.append(this.carType);
		builder.append('-');
		builder.append(this.carTypeDoor);
		builder.append('-');
		builder.append(this.transmission);
		builder.append('-');
		builder.append(this.fuel);
		builder.append('-');
		builder.append(this.aircondition);
		return builder.toString();
	}

	public Double getSumScore() {
		return sumScore;
	}

	public void setSumScore(Double sumScore) {
		this.sumScore = sumScore;
	}

	public String getTransmission() {
		return transmission;
	}

	public String getCarTypeDoor() {
		return carTypeDoor;
	}

	public void setCarTypeDoor(String carTypeDoor) {
		this.carTypeDoor = carTypeDoor;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	
	public void setVehicleRating(Integer vehicleRating) {
		this.vehicleRating = vehicleRating;
	}
	
	public Integer getVehicleRating() {
		return vehicleRating;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public void evaluateSIPP(){
		
		for(int i = 0; i < SIPP_LENGTH; i++){
			if(i == (FUEL_AIR_INDEX)){
				String[] fuelAirInfo = SIPP_INFORMATION.get(i).get(this.sipp.charAt(i)).split("/");
				this.aircondition = fuelAirInfo[1];
				this.fuel = fuelAirInfo[0];
			}
			else{
				String indexedSippInformation = SIPP_INFORMATION.get(i).get(this.sipp.charAt(i));
				if(i == (TRANSMISSION_INDEX)){
					this.transmission = indexedSippInformation;
				}
				else if(i == (CAR_TYPE_INDEX)){
					this.carType = indexedSippInformation;
				}
				else if(i == (CAR_DOOR_INDEX)){
					this.carTypeDoor = indexedSippInformation;
				}
			}
		}
	}
	
	private static List<Map<Character, String>> createTheSIPPMap() {

		return Collections.unmodifiableList(Stream.of(
				HelperMappings.CAR_TYPE_MAP,
				HelperMappings.CAR_TYPE_DOOR_MAP,
				HelperMappings.TRANSIMISSION_MAP,
				HelperMappings.FUEL_MAP)
				.collect(Collectors.toList()));
	}

}
