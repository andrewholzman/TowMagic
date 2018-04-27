package com.towmagic.service;

import java.util.List;

import com.towmagic.dto.Vehicle;

public interface IVehicleService {

	public List<Vehicle> filterVehicles(String filter) throws Exception;
	/**
	 * Returns all vehicles from the database 
	 * @return
	 * @throws Exception
	 */
	public List<Vehicle> getAllVehicles() throws Exception;
	
	/**
	 * Save updated Vehicle info to vehicle table
	 * @param vehicle - Vehicle object containing updated info
	 * @throws Exception
	 */
	public void insert(Vehicle vehicle) throws Exception;
	
	/**
	 * Update the vehicle in the database
	 * @param vehicle - vehicle object to update
	 * @throws Exception
	 */
	public void update(Vehicle vehicle) throws Exception;
	/**
	 * Remove  vehicle record from the database 
	 * @param vehicleID - ID of the vehicle to delete
	 * @throws Exception
	 */
	public void remove(int vehicleID) throws Exception;
}
