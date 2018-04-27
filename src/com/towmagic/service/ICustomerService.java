package com.towmagic.service;

import java.util.List;

import com.towmagic.dto.Customer;


public interface ICustomerService {
	/**
	 * Interface to provide for busienss logic related to the CustomerService
	 */
	
	/**
	 * Returns a filtered list of customers given a filter string
	 * @param filter
	 * @return List<Customer> of Customer objects matching the filter param
	 * @throws Exception
	 */
	public List<Customer> filterCustomers(String filter) throws Exception;
	/**
	 * Returns all customers from the backend 
	 * @return
	 * @throws Exception
	 */
	public List<Customer> getAllCustomers() throws Exception;
	
	/**
	 * Save new customer info to customer tabe
	 * @param customer - Customer object containing new info
	 * @throws Exception
	 */
	
	public void insert(Customer customer) throws Exception;
	/**
	 * update an existing customer object in the database
	 * @param customer
	 * @throws Excepion
	 */
	public void update(Customer customer) throws Exception;
	
	/**
	 * remove an customer record from the database
	 * @param customerID - database ID of customer record to remove
	 * @throws Exception
	 */
	public void remove(int customerID) throws Exception;
}
