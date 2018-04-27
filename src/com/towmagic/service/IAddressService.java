package com.towmagic.service;

import com.towmagic.dto.Address;

public interface IAddressService {
	
	public int insert(Address address) throws Exception;
	public Address getCustomerAddress(int addressID) throws Exception;

}
