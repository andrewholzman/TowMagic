package com.towmagic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;


import com.towmagic.dto.Vehicle;

@Named("vehicleDAO")
public class VehicleDAO implements IVehicleDAO {

	@Override
	public List<Vehicle> getVehicles() throws Exception {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		//query database and build employeeList out of each result
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Vehicle where id is not null");
		for (Object o : query.list()) { 
			vehicleList.add((Vehicle) o);
		} 
		return vehicleList;
	}

	@Override
	public void insertVehicle(Vehicle vehicle) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.getTransaction().commit();
	}

	@Override
	public void updateVehicle(Vehicle vehicle) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(vehicle);
		session.getTransaction().commit();
	}

	@Override
	public void removeVehicle(int vehicleId) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("delete from Vehicle where ID = :id");
		query.setParameter("id", vehicleId);
		query.executeUpdate();
	}

}
