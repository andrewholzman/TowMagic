package com.towmagic.dto;
import javax.annotation.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;


@Named
@ManagedBean
@Scope("session")
public class Address {
	
	private int id;
	private String lineOne;
	private String lineTwo;
	private String city;
	private String state;
	private String postal;
	
	
	public void setAddress(String lineOne, String lineTwo, String city, String state, String postal) {
		this.setLineOne(lineOne);
		this.setLineTwo(lineTwo);
		this.setCity(city);
		this.setState(state);
		this.setPostal(postal);
	}
	
	public Address getAddress() {
		return this;
	}

	public String getLineOne() {
		return lineOne;
	}

	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}

	public String getLineTwo() {
		return lineTwo;
	}

	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Overridden toString() method to return formatted string of address for display
	 */
	@Override 
	public String toString() {
		String lineOne = getLineOne();
		String lineTwo = getLineTwo();
		String city = getCity();
		String state = getState();
		String zip = getPostal();
		
		return lineOne +"," + lineTwo + "," + city + "," + state +"," + zip;
	}

}