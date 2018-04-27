package com.towmagic.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.towmagic.dao.IEmployeeDAO;
import com.towmagic.dto.Employee;

@Named
public class EmployeeService implements IEmployeeService {
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

	@Override
	public void update(Employee employee) throws Exception {
		try {
			employeeDAO.updateEmployee(employee);
		} catch (Exception e) {
			throw new Exception("Update Employee" + Integer.toString(employee.getId()) + " Failed");
		}
		
	}

	@Override
	public void remove(int employeeID) throws Exception {
		try {
			employeeDAO.removeEmployee(employeeID);
		} catch (Exception e) {
			throw new Exception("Delete Employee: " + Integer.toString(employeeID) + " Failed");
		}
	}

}
