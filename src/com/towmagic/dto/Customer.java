package com.towmagic.dto;

import javax.annotation.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@ManagedBean
@Scope("session")
public class Customer {
	/**
	 * Customer DTO
	 */
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	private int addressID; //address handled as separate save that returns the DB id
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	
	@Override 
	public String toString() {
		String firstName = getFirstName();
		String lastName = getLastName();
		
		
		return firstName + " " + lastName;
	}
	
}
