package com.cgi.scratch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.scratch.exceptions.IdNotPresent;
import com.cgi.scratch.exceptions.VehicleWithIdPresent;
import com.cgi.scratch.model.Vehicle;
import com.cgi.scratch.service.VehicleService;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
	
	@Autowired
	private VehicleService vServe;
	
	@GetMapping(value = "/vehicles")
	public ResponseEntity<?> getAllVehiclesHandler(){
		ResponseEntity<?> response;
		List<Vehicle> vehicles = vServe.getAllVehicles();
		response = new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
		return response;
	}
	
	@PostMapping(value = "/vehicles")
	public ResponseEntity<?> addNewVehicleHandler(@RequestBody Vehicle vehicle){
		ResponseEntity<?> response;
		try {
			Vehicle newVehicle = vServe.addNewVehicle(vehicle);
			response = new ResponseEntity<Vehicle>(newVehicle, HttpStatus.CREATED);
		} catch (VehicleWithIdPresent e) {
			response = new ResponseEntity<String>("Vehicle with that ID already exists", HttpStatus.CONFLICT);
		}
		return response;
	}

	@GetMapping(value = "/vehicles/{vId}")
		public ResponseEntity<?> getByIdHandler(@PathVariable("vId") int id){
			ResponseEntity<?> response;
			
			try {
				Vehicle v = vServe.getVehicleByID(id);
				response = new ResponseEntity<Vehicle>(v, HttpStatus.OK);
			} catch (IdNotPresent e) {
				response = new ResponseEntity<String>("No vehicle with that ID found", HttpStatus.BAD_REQUEST);
			}
			return response;
		}
	
	@DeleteMapping(value = "/vehicles/{vId}")
		public ResponseEntity<?> deleteByIdHandler(@PathVariable("vId") int id){
		ResponseEntity<?> response;
		
		try {
			vServe.deleteVehicle(id);
			String deleted = "Vehicle with id: " + id + " deleted.";
			response = new ResponseEntity<String>(deleted, HttpStatus.OK);
		} catch (IdNotPresent e) {
			response = new ResponseEntity<String>("No vehicle with that ID found", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping(value = "/vehicles/{vId}")
		public ResponseEntity<?> updateHandler(@PathVariable("vId") int id, @RequestBody Vehicle v) throws IdNotPresent{
		ResponseEntity<?> response;
		
		try {
			vServe.getVehicleByID(id);
		
			if (v.getId() == id) {
				Vehicle updated = vServe.updateVehicle(v);
				response = new ResponseEntity<Vehicle>(updated,HttpStatus.OK);
			} else  {
				response = new ResponseEntity<String>("Cannot change vehicle ID during update.", HttpStatus.BAD_REQUEST);
			}
		} catch (IdNotPresent e) {
			response = new ResponseEntity<String>("No vehicle with that ID found", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	}


