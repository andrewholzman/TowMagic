package com.towmagic.service.tests;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.towmagic.dao.ICustomerDAO;
import com.towmagic.dto.Customer;
import com.towmagic.service.CustomerService;

import junit.framework.TestCase;

public class TestCustomerService extends TestCase {
	
	private CustomerService custService;
	private List<Customer> allCustomers;

	@Test
	public void testInsertCustomer() throws Exception {
		givenThatCustomerServiceIsPopulatedWithCustomerDOA();
		whenCustomersAreInserted();
		thenCustomerListIsUpdated();
	}

	private void thenCustomerListIsUpdated() {
		assertEquals(3, allCustomers.size());
	}

	private void whenCustomersAreInserted() throws Exception {
		allCustomers = custService.getAllCustomers();
	}

	private void givenThatCustomerServiceIsPopulatedWithCustomerDOA() {
		custService = new CustomerService();
		
		// a mock customer that will do what we want
		ICustomerDAO custDAO = mock(ICustomerDAO.class);
		
		//the created customer list
		List<Customer> constructCustomerList = constructCustomerList();

		try {
			when(custDAO.getCustomers()).thenReturn(constructCustomerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		custService.setCustomerDAO(custDAO);
	}
	
	private List<Customer> constructCustomerList() {
		List<Customer> allCustomers = new ArrayList<Customer>();
		
		Customer micah = new Customer();
		micah.setFirstName("Micah");
		micah.setLastName("Johnson");
		micah.setAddressID(1);
		micah.setPhone("6145551786");
		allCustomers.add(micah);
		
		Customer nick = new Customer();
		nick.setFirstName("Nick");
		nick.setLastName("");
		nick.setAddressID(4);
		nick.setPhone("6145555546");
		allCustomers.add(nick);
		
		Customer sam = new Customer();
		sam.setFirstName("Sam");
		sam.setLastName("Smith");
		sam.setAddressID(2);
		sam.setPhone("6145552346");
		allCustomers.add(sam);
	
		return allCustomers;	
	}

	/*
	@Test
	public void testSetCustomer() {}
	*/
	
}
