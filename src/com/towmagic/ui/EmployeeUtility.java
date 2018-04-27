package com.towmagic.ui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
//import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;

import com.towmagic.dto.Employee;
import com.towmagic.service.IEmployeeService;

@Named
@ManagedBean
@Scope("request")

public class EmployeeUtility {

	@Inject
	private IEmployeeService employeeService; // instance of the service layer
												// object customerService
	@Inject
	private Employee emp;

	private List<Employee> employees;
	private FacesContext currentInstance;

	/**
	 * Method is used to call the getAllEmployees() method from the service
	 * object to populate on create of the managed bean Used so that the
	 * List<employees> employees contains data when employees.xhtml renders
	 */
	@PostConstruct
	public void init() {
		if (employees == null) {
			try {
				employees = getEmployees();
			} catch (Exception e) {
				//
			}
		}
	}

	/**
	 * Returns list of Employees
	 * 
	 * @return
	 */
	public List<Employee> getEmployees() {
		// call DAO method getCustomers to retrieve list of customer objects
		// from database
		List<Employee> employeeList = new ArrayList<Employee>();
		if ((employees == null)||(employees.isEmpty())) {
			try {
				employeeList = employeeService.getAllEmployees();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
						"Failed to retrieve employees from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		} else {
			employeeList = employees;
		}
		return employeeList;
	}

	/**
	 * calls the insert business logic for Address and then Customer
	 * 
	 * @property employee - employee to insert to the db
	 */
	public void addEmployee() {
		currentInstance = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			employeeService.insert(emp);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "Employee Saved");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Employee Failed to be Saved: " + e.getMessage());
		}
		currentInstance.addMessage(null, message);
	}

	public void onRowEdit(RowEditEvent event) {
		// call update of DB
		currentInstance = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		Employee editedEmp = (Employee) event.getObject();

		try {
			employeeService.update(editedEmp);
			msg = new FacesMessage("Employee Edited", Integer.toString(((Employee) event.getObject()).getId()));
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Employee Failed to be Updated: " + e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String removeHandler(Employee emp) {
		int employeeID = emp.getId();
		FacesMessage msg = null;
		try {
			employeeService.remove(employeeID);
			msg = new FacesMessage("Employee: " + Integer.toString(employeeID) + " Deleted");
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Employee Failed to be Deleted: " + e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
	}

}
