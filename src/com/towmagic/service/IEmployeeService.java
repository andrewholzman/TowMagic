package com.towmagic.service;

import java.util.List;

import com.towmagic.dto.Employee;

public interface IEmployeeService {

	public List<Employee> filterEmployees(String filter) throws Exception;
	/**
	 * Returns all Employee from the backend 
	 * @return
	 * @throws Exception
	 */
	public List<Employee> getAllEmployees() throws Exception;
	
	/**
	 * Save new Employee info to Employee table
	 * @param employee - Employee object containing new info
	 * @throws Exception
	 */
	public void insert(Employee employee) throws Exception;
	
	/**
	 * update an existing employee object in the database
	 * @param employee
	 * @throws Excepion
	 */
	public void update(Employee employee) throws Exception;
	
	/**
	 * remove an employee record from the database
	 * @param employeeID - database ID of employee record to remove
	 * @throws Exception
	 */
	public void remove(int employeeID) throws Exception;
}
