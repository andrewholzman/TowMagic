package com.towmagic.dao;

import com.towmagic.dto.Person;

public interface IPersonDAO {
	/**
	 * Interface to provide for business logic related to a Person
	 */
	

	public void insert(Person person) throws Exception;
	
	public void update(Person persons) throws Exception;
	
	public void delete(Person person) throws Exception;
}
