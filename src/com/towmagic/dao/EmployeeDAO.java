package com.towmagic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.towmagic.dto.Employee;

@Named("employeeDAO")
public class EmployeeDAO implements IEmployeeDAO {

	@Override
	public List<Employee> getEmployees() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		//query database and build employeeList out of each result
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Employee where id is not null");
		for (Object o : query.list()) { 
			employeeList.add((Employee) o);
		} 
		return employeeList;
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception {
		//hash the users password
		String encryptedPass = BCrypt.hashpw(employee.getPassword(),BCrypt.gensalt());
		employee.setPassword(encryptedPass);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(employee);
		session.getTransaction().commit();
	}

	@Override
	public void removeEmployee(int employeeID) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from Employee where ID = :id");
		query.setParameter("id", employeeID);
		query.executeUpdate();
		session.getTransaction().commit();
		//session.close();
	}

	@Override
	public void insertEmployee(Employee employee) throws Exception {
		
		//save employee to the database
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		
	}

}
