package com.towmagic.dao;

import java.util.List;

import com.towmagic.dto.Vehicle;

public interface IVehicleDAO {
	/**
	 * Interface to provide for business logic related to a Vehicle
	 */
	
	public List<Vehicle> getVehicles() throws Exception;
	public void insertVehicle(Vehicle vehicle) throws Exception;
	public void updateVehicle(Vehicle vehicle) throws Exception;
	public void removeVehicle(int vehicleId) throws Exception;
}
