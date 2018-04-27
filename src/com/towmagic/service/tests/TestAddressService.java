package com.towmagic.service.tests;


import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.towmagic.dao.IAddressDAO;
import com.towmagic.dto.Address;
import com.towmagic.service.AddressService;

import junit.framework.TestCase;

public class TestAddressService extends TestCase {

	private AddressService addressService;
	private Address emptyAddress;

	@Test (expected = Exception.class)
	public void testInsertEmptyAddress() {
		givenTheAddressListHasAAddressDOA();
		whenAAddressIsCreatedWithEmptyFields();
		thenExceptionIsThrown();
	}
	
	private void givenTheAddressListHasAAddressDOA() {
		addressService = new AddressService();
		
		// mock address object
		IAddressDAO addressDAO = mock(IAddressDAO.class);
		
		//addressService.setAddressDAO(addressDAO);	
	}

	private void whenAAddressIsCreatedWithEmptyFields() {
		emptyAddress = new Address();
		emptyAddress.setId(1);
		emptyAddress.setLineOne("555 Street Dr.");
		emptyAddress.setCity("Cincinnati");
		emptyAddress.setState("");
		emptyAddress.setPostal("45220");
	}

	private void thenExceptionIsThrown() {
		try {
			addressService.insert(emptyAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
