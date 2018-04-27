package com.towmagic.dao;

import com.towmagic.dto.Address;

public interface IAddressDAO {
	/**
	 * Interface to provide for business logic related to an Address
	 */
	
	public int insert(Address address) throws Exception;
	
	public void update(Address address) throws Exception;
	
	public void delete(Address address) throws Exception;
	
	public Address getCustomerAddress(int addressID) throws Exception;
}
