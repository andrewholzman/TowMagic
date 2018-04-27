package com.towmagic.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.towmagic.dao.IAddressDAO;
import com.towmagic.dto.Address;

@Named
public class AddressService implements IAddressService {
	@Inject 
	private IAddressDAO addressDAO;

	@Override
	public int insert(Address address) throws Exception {
		if (address.getLineOne().isEmpty()) {
			throw new Exception("Address Line must be entered");
		}
		if (address.getLineTwo().isEmpty()) {
			address.setLineTwo(null); //set line two to null, so null value is inserted through mySQL sp
		}
		if (address.getCity().isEmpty()) {
			throw new Exception("City must be entered");
		}
		if (address.getState().isEmpty()) {
			throw new Exception("State must be entered");
		}
		if (address.getPostal().isEmpty()) {
			throw new Exception("Postal Code must be entered");
		}
		
		int addressID;
		try {
			addressID = addressDAO.insert(address);
		} catch (Exception e) {
			throw new Exception("Address Insert Failed -" + e.getMessage());
		}
		return addressID;
	}

	/**
	 * Method to call addressDAO getCustomerAddress to return Address object for given addressID from database
	 * @param addressID - addressID to search DB for
	 * @return Address object that matches the given ID
	 */
	@Override
	public Address getCustomerAddress(int addressID) throws Exception {
		Address customerAddress = null;
		try {
			customerAddress = addressDAO.getCustomerAddress(addressID);
		} catch (Exception e) {
			throw new Exception("Get Customer Address Failed for " + Integer.toString(addressID) + e.getMessage());
		}
		return customerAddress;
	}

//	public IAddressDAO getAddressDAO() {
//		return addressDAO;
//	}
//
//	public void setAddressDAO(IAddressDAO addressDAO) {
//		this.addressDAO = addressDAO;
//	}

}
