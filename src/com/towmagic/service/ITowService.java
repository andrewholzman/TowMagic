package com.towmagic.service;

import java.util.List;

import com.towmagic.dto.Customer;
import com.towmagic.dto.Employee;
import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;

public interface ITowService {

	public List<Tow> filterTows(String filter) throws Exception;
	/**
	 * Returns all Tows from the database 
	 * @return
	 * @throws Exception
	 */
	public List<Tow> getAllTows() throws Exception;
	
	/**
	 * Save updated Tow info to Tow table
	 * @param Tow - Tow object containing updated info
	 * @throws Exception
	 */
	public void insert(Tow tow) throws Exception;
	
	/**
	 * Update the Tow in the database
	 * @param Tow - tow object to update
	 * @throws Exception
	 */
	public void update(Tow tow) throws Exception;
	/**
	 * Remove  Tow record from the database 
	 * @param TowID - ID of the tow to delete
	 * @throws Exception
	 */
	public void remove(int towID) throws Exception;
	
	public Customer getCustomer(int customerID) throws Exception;
	public Employee getDriver(int driverID) throws Exception;
	public Vehicle getVehicle(int vehicleID) throws Exception;
}
