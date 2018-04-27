package com.towmagic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import com.towmagic.dto.Customer;


@Named("customerDAO")
public class CustomerDAO implements ICustomerDAO {
	/**
	 * Implementation of ICustomer - crud statements and retrieval methods
	 */
	

	/**
	 * query the database to return a list of all customers
	 * @return list of Customer objects
	 */
	@Override
	public List<Customer> getCustomers() throws Exception {
		List<Customer> customerList = new ArrayList<Customer>();
		//query database and build customerList out of each result
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Customer where id is not null");
		for (Object o : query.list()) { //iterate over the list of objects returned by HQL query.list() and cast to Customer
			customerList.add((Customer) o);
		} 
		return customerList; //customer list ends up having 10,  when there are only 5 customer records in the database
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(customer);
		session.getTransaction().commit();
	}

	@Override
	public void removeCustomer(int customerID) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("delete from Customer where id = :id");
		query.setParameter("id", customerID);
		query.executeUpdate();
	}


	@Override
	public void insertCustomer(Customer customer) throws Exception {
		//save customer to the database
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		
	}
}