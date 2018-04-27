package com.towmagic.dto;

import javax.annotation.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@ManagedBean
@Scope("session")
public class Person  {
	private int id;
	private String name;
	private String phone;
	private Address address;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(String lineOne, String lineTwo, String city, String state, String postal) {
		address = new Address();
		address.setAddress(lineOne, lineTwo, city, state, postal);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
