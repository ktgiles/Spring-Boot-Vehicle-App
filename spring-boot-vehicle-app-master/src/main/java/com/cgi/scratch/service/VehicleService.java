package com.cgi.scratch.service;

import java.util.List;

import com.cgi.scratch.exceptions.IdNotPresent;
import com.cgi.scratch.exceptions.VehicleWithIdPresent;
import com.cgi.scratch.model.Vehicle;

public interface VehicleService {
	
	List<Vehicle> getAllVehicles();
	Vehicle getVehicleByID(int id) throws IdNotPresent;
	Vehicle addNewVehicle(Vehicle vehicle) throws VehicleWithIdPresent;
	void deleteVehicle(int id) throws IdNotPresent;
	Vehicle updateVehicle(Vehicle vehicle) throws IdNotPresent;

}
