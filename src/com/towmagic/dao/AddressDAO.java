package com.towmagic.dao;


import javax.inject.Named;
import javax.persistence.ParameterMode;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;

import com.towmagic.dto.Address;

@Named("addressDAO")
public class AddressDAO implements IAddressDAO {

	/**
	 * Long winded way of calling session.Save(Address)
	 * Address is inserted with the customer record - customer table saves the AddressID that ties to the customer
	 * Using a stored procedure InsertAddress which inserts into the address table, and then returns the
	 * Last inserted ID
	 * @output LastInsertedID - the ID of the record that was just inserted to save with Customer.
	 */
	@Override
	public int insert(Address address) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ProcedureCall query = session.createStoredProcedureCall("InsertAddress");
		query.registerParameter("LineOne", String.class, ParameterMode.IN).bindValue(address.getLineOne());
		query.registerParameter("LineTwo", String.class, ParameterMode.IN).bindValue(address.getLineTwo());
		query.registerParameter("City", String.class, ParameterMode.IN).bindValue(address.getCity());
		query.registerParameter("State", String.class, ParameterMode.IN).bindValue(address.getState());
		query.registerParameter("ZipCode", String.class, ParameterMode.IN).bindValue(address.getPostal());
		query.registerParameter("InsID", Integer.class, ParameterMode.OUT);
		ProcedureOutputs result = query.getOutputs();
		session.disconnect().close();
		int lastInsertedID = (int)result.getOutputParameterValue("InsID");
		return lastInsertedID;

	}

	@Override
	public void update(Address address) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Address address) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * query the database and return the matching address record for given ID
	 * @param addressID to query DB on
	 * @return Address object populated from DB
	 */
	@Override
	public Address getCustomerAddress(int addressID) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Address where id = :addressID");
		query.setParameter("addressID", addressID);
		Address address = (Address)query.uniqueResult();
		session.disconnect().close();
		return address;
	}

}
