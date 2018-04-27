package com.towmagic.dao;


import java.util.List;

import com.towmagic.dto.Customer;
import com.towmagic.dto.Employee;
import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;


public interface ITowDAO {
	/**
	 * Interface to provide for business logic related to a Tow
	 */
	
	public List<Tow> getTows() throws Exception;
	public void insertTow(Tow tow) throws Exception;
	public void updateTow(Tow tow) throws Exception;
	public void removeTow(int towId) throws Exception;
	
	public Customer getCustomerFromTow(int customerID) throws Exception;
	public Vehicle getVehicleFromTow(int vehicleID) throws Exception;
	public Employee getEmployeeFromTow(int employeeID) throws Exception;
}
