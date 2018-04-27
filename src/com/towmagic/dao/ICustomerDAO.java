package com.towmagic.dao;

import java.util.List;

import com.towmagic.dto.Customer;

public interface ICustomerDAO {
	/**
	 * Interface to provide for business logic specific to a customer, implementing logic pertaining to a Person
	 * @return 
	 */

	public List<Customer> getCustomers() throws Exception;
	public void insertCustomer(Customer customer) throws Exception;
	public void updateCustomer(Customer customer) throws Exception;
	public void removeCustomer(int customerID) throws Exception;
}