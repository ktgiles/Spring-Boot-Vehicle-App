package com.cgi.scratch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.scratch.exceptions.IdNotPresent;
import com.cgi.scratch.exceptions.VehicleWithIdPresent;
import com.cgi.scratch.model.Vehicle;
import com.cgi.scratch.repo.VehicleRepo;

@Service
public class VehicleServiceImpl implements VehicleService{

	@Autowired
	private VehicleRepo vRepo;
	
	
	public List<Vehicle> getAllVehicles() {
		return vRepo.findAll();
	}

	public Vehicle getVehicleByID(int id) throws IdNotPresent {
		Optional<Vehicle> optional = vRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new IdNotPresent();
		}
	}

	
	public Vehicle addNewVehicle(Vehicle vehicle) throws VehicleWithIdPresent {
		Optional<Vehicle> optional = vRepo.findById(vehicle.getId());
		
		if(optional.isEmpty()) {
			vRepo.save(vehicle);
			return vehicle;
		} else {
			throw new VehicleWithIdPresent();
		}
	}
	

	public void deleteVehicle(int id) throws IdNotPresent {
		Optional<Vehicle> optional = vRepo.findById(id);
		
		if (optional.isPresent()) {
			vRepo.deleteById(id);
		} else {
			throw new IdNotPresent();
		}
		
	}

	public Vehicle updateVehicle(Vehicle vehicle) throws IdNotPresent {
		Optional<Vehicle> optional = vRepo.findById(vehicle.getId());
		if (optional.isPresent()) {
			vRepo.save(vehicle);
			return vehicle;
		} else {
			throw new IdNotPresent();
		}
	}
	

}
