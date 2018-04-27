package com.towmagic.dao;

import java.util.List;

import com.towmagic.dto.Employee;

public interface IEmployeeDAO {
	/**
	 * Interface to provide for business logic specific to a customer, implementing logic pertaining to a Person
	 * @return 
	 */

	public List<Employee> getEmployees() throws Exception;
	public void insertEmployee(Employee employee) throws Exception;
	public void updateEmployee(Employee employee) throws Exception;
	public void removeEmployee(int employeeID) throws Exception;
}