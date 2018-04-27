package com.towmagic.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.towmagic.dao.ITowDAO;
import com.towmagic.dto.Customer;
import com.towmagic.dto.Employee;
import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;

@Named
public class TowService implements ITowService {
	@Inject 
	private ITowDAO towDAO;

	@Override
	public List<Tow> filterTows(String filter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tow> getAllTows() throws Exception {
		List<Tow> towList = new ArrayList<Tow>();
		try {
			towList = towDAO.getTows();
		} catch (Exception e) {
			throw new Exception("Get Tows Failed");
		}
		return towList;
	}

	@SuppressWarnings("unused")
	@Override
	public void insert(Tow tow) throws Exception {
		
		if (tow.getEstimateTime() == null) {
			throw new Exception("Time Estimate must be entered");
		}
		if (tow.getStatus() == null) {
			throw new Exception("Status must be entered");
		}
		if ((Integer)tow.getCustomerID() == null) {
			throw new Exception("Customer must be entered");
		}
		if ((Integer)tow.getDriverID()== null ) {
			throw new Exception("Driver must be entered");
		}
		if ((Integer)tow.getVehicleID()== null ) {
			throw new Exception("Vehicle must be entered");
		}
		towDAO.insertTow(tow);
	}

	@Override
	public void update(Tow tow) throws Exception {
		try {
			towDAO.updateTow(tow);
		} catch (Exception e) {
			throw new Exception("Update Tow" + Integer.toString(tow.getId()) + " Failed");
		}

	}

	@Override
	public void remove(int towID) throws Exception {
		try {
			towDAO.removeTow(towID);
		} catch (Exception e) {
			throw new Exception("Delete Tow: " + Integer.toString(towID) + " Failed");
		}

	}

	public ITowDAO getTowDAO() {
		return towDAO;
	}

	public void setTowDAO(ITowDAO towDAO) {
		this.towDAO = towDAO;
	}
	
	public Customer getCustomer(int customerID) throws Exception {
		Customer customer = new Customer();
		try {
			customer = towDAO.getCustomerFromTow(customerID);
		} catch (Exception e) {
			throw new Exception("Retrieve Tow Customer for ID: " + Integer.toString(customerID) + "Failed. " + e.getMessage());
		}
		return customer;
	}
	
	public Employee getDriver(int driverID) throws Exception {
		Employee driver = new Employee();
		try {
			driver = towDAO.getEmployeeFromTow(driverID);
		} catch (Exception e) {
			throw new Exception("Retrieve Tow Driver for ID: " + Integer.toString(driverID) + "Failed. " + e.getMessage());
		}
		return driver;
	}
	
	public Vehicle getVehicle(int vehicleID) throws Exception {
		Vehicle truck = new Vehicle();
		try {
			truck = towDAO.getVehicleFromTow(vehicleID);
		} catch (Exception e) {
			throw new Exception("Retrieve Tow Vehicle for ID: " + Integer.toString(vehicleID) + "Failed. " + e.getMessage());
		}
		return truck;
	}
	
	

	
}
