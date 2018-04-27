package com.towmagic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;


import com.towmagic.dto.Customer;
import com.towmagic.dto.Employee;
import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;

@Named("towDAO")
public class TowDAO implements ITowDAO {

	@Override
	public List<Tow> getTows() throws Exception {
		List<Tow> towList = new ArrayList<Tow>();
		//query database and build employeeList out of each result
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Tow where id is not null");
		for (Object o : query.list()) { 
			towList.add((Tow) o);
		} 
		return towList;
	}

	@Override
	public void insertTow(Tow tow) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(tow);
		session.getTransaction().commit();

	}

	@Override
	public void updateTow(Tow tow) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(tow);
		session.getTransaction().commit();

	}

	@Override
	public void removeTow(int towId) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("delete from Tow where ID = :id");
		query.setParameter("id", towId);
		query.executeUpdate();

	}
	
	/**
	 * Query the database to find the matching Customer object for given customerID
	 * @param customerID
	 * @return Customer object matching id
	 * @throws Exception
	 */
	public Customer getCustomerFromTow(int customerID) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Customer where id = :customerID");
		query.setParameter("customerID", customerID);
		Customer customer = (Customer)query.uniqueResult();
		return customer;
	}
	
	/**
	 * Query the database to find the matching Employee object for given employeeID
	 * @param employeeID
	 * @return Employee object matching id
	 * @throws Exception
	 */
	public Employee getEmployeeFromTow(int employeeID)  throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Employee where id = :employeeID");
		query.setParameter("employeeID", employeeID);
		Employee employee = (Employee)query.uniqueResult();
		return employee;
	}
	
	/**
	 * Query the database to find the matching Vehicle object for given vehicleID
	 * @param vehicleID
	 * @return Vehicle object matching id
	 * @throws Exception
	 */
	public Vehicle getVehicleFromTow(int vehicleID)  throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Vehicle where id = :vehicleID");
		query.setParameter("vehicleID", vehicleID);
		Vehicle vehicle = (Vehicle)query.uniqueResult();
		return vehicle;
	}

}
