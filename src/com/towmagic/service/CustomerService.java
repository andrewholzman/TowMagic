package com.towmagic.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.towmagic.dao.ICustomerDAO;
import com.towmagic.dto.Customer;

@Named
public class CustomerService implements ICustomerService {
	@Inject 
	private ICustomerDAO customerDAO;
	
	

	@Override
	public List<Customer> filterCustomers(String filter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			customerList = customerDAO.getCustomers();
		} catch (Exception e) {
			throw new Exception("Get Customers Failed");
		}
		return customerList;
	}

	@Override
	public void insert(Customer customer) throws Exception {
		if (customer.getFirstName().isEmpty()) {
			throw new Exception("First Name must be entered");
		}
		if (customer.getLastName().isEmpty()) {
			throw new Exception("Last Name must be entered");
		}
		if (customer.getPhone().isEmpty()) {
			throw new Exception("Phone Number must be entered");
		}
		if (customer.getAddressID() == 0) {
			// the value is the default of primitive type integer, so the insert statement for the address either returned 0 or nothing
			throw new Exception("Adddress ID invalid. Address did not save or returned invalid result");
		}

		customerDAO.insertCustomer(customer);
		
	}

	public ICustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public void update(Customer customer) throws Exception {
		try {
			customerDAO.updateCustomer(customer);
		} catch (Exception e) {
			throw new Exception("Update Customer" + Integer.toString(customer.getId()) + " Failed");
		}
	}

	@Override
	public void remove(int customerID) throws Exception {
		try {
			customerDAO.removeCustomer(customerID);
		} catch (Exception e) {
			throw new Exception("Delete Customer: " + Integer.toString(customerID) + " Failed");
		}
	}

}
