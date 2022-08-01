package com.cgi.scratch.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {
	
	@Id
	int id;
	String make;
	String model;
	int year;
	public Vehicle(int id, String make, String model, int year) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	public Vehicle() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return "Vehicle [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + "]";
	}
	
	
	

}
