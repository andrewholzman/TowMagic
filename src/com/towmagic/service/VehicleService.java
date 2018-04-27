package com.towmagic.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.towmagic.dao.IVehicleDAO;
import com.towmagic.dto.Vehicle;

@Named
public class VehicleService implements IVehicleService {
	@Inject
	private IVehicleDAO vehicleDAO;
	
	@Override
	public List<Vehicle> filterVehicles(String filter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> getAllVehicles() throws Exception {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		try {
			vehicleList = vehicleDAO.getVehicles();
		} catch (Exception e) {
			throw new Exception("Get Vehicles Failed");
		}
		return vehicleList;
	}

	@Override
	public void insert(Vehicle vehicle) throws Exception {
		if (vehicle.getMake().isEmpty()) {
			throw new Exception("Make must be entered");
		}
		if (vehicle.getModel().isEmpty()) {
			throw new Exception("Model must be entered");
		}
		if (vehicle.getColor().isEmpty()) {
			throw new Exception("Color must be entered");
		}
		if (vehicle.getPlate().isEmpty()) {
			throw new Exception("Plate must be entered");
		}
		if (vehicle.getYear().isEmpty()) {
			throw new Exception("Year must be entered");
		}

		vehicleDAO.insertVehicle(vehicle);
		
	}

	@Override
	public void update(Vehicle vehicle) throws Exception {
		try {
			vehicleDAO.updateVehicle(vehicle);
		} catch (Exception e) {
			throw new Exception("Update Vehicle" + Integer.toString(vehicle.getId()) + " Failed");
		}
		
	}

	@Override
	public void remove(int vehicleID) throws Exception {
		try {
			vehicleDAO.removeVehicle(vehicleID);
		} catch (Exception e) {
			throw new Exception("Delete Vehicle: " + Integer.toString(vehicleID) + " Failed");
		}
	}
	
//	public IVehicleDAO getVehicleDAO() {
//		return vehicleDAO;
//	}
//
//	public void setTowDAO(IVehicleDAO vehicleDAO) {
//		this.vehicleDAO = vehicleDAO;
//	}

}


/**
@Inject
	private IEmployeeDAO employeeDAO;

	@Override
	public List<Employee> filterEmployees(String filter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			employeeList = employeeDAO.getEmployees();
		} catch (Exception e) {
			throw new Exception("Get Employees Failed");
		}
		return employeeList;
	}

	@Override
	public void insert(Employee employee) throws Exception {
		if (employee.getFirstName().isEmpty()) {
			throw new Exception("First Name must be entered");
		}
		if (employee.getLastName().isEmpty()) {
			throw new Exception("Last Name must be entered");
		}
		if (employee.getUserName().isEmpty()) {
			throw new Exception("Username must be entered");
		}
		if (employee.getPassword().isEmpty()) {
			throw new Exception("Password must be entered");
		}

		employeeDAO.insertEmployee(employee);
	}
**/