package com.toulios.carstatistics.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Helper class, which holds a list with the information of each vehicle
 * It is used for JSon de-serialization.
 */
public class SearchValue implements Serializable {
	private static final long serialVersionUID = -440653172785591887L;

	@JsonProperty("VehicleList")
	private List<VehicleInfo> vehicleList;

	public SearchValue(){
		
	}
	
	public SearchValue(List<VehicleInfo> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public void setVehicleList(List<VehicleInfo> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List<VehicleInfo> getVehicleList() {
		return vehicleList;
	}
}
