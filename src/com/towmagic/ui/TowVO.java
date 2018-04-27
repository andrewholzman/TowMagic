package com.towmagic.ui;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.towmagic.dto.Customer;
import com.towmagic.dto.Employee;
import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;
import com.towmagic.service.ICustomerService;
import com.towmagic.service.IEmployeeService;
import com.towmagic.service.ITowService;
import com.towmagic.service.IVehicleService;

@Named
@ManagedBean
@Scope("session")
public class TowVO {
	@Inject
	private ITowService towService;
	@Inject
	private ICustomerService customerService;
	@Inject
	private IEmployeeService employeeService;
	@Inject
	private IVehicleService vehicleService;

	private FacesContext currentInstance;

	private Tow towVar;
	private Customer customer;
	private Vehicle vehicle;
	private Employee driver;

	public Customer getCustomer(int customerID) {
		Customer customer = new Customer();
		try {
			customer = towService.getCustomer(customerID);
		} catch (Exception e) {
			currentInstance = FacesContext.getCurrentInstance();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Failed to retrieve Tow Customer With ID: " + Integer.toString(customerID) + ". " + e.getMessage());
			currentInstance.addMessage(null, message);
		}
		return customer;
	}

	public Employee getDriver(int driverID) {
		Employee driver = new Employee();
		try {
			driver = towService.getDriver(driverID);
		} catch (Exception e) {
			currentInstance = FacesContext.getCurrentInstance();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Failed to retrieve Tow Driver With ID: " + Integer.toString(driverID) + ". " + e.getMessage());
			currentInstance.addMessage(null, message);
		}
		return driver;
	}

	public Vehicle getVehicle(int vehicleID) {
		Vehicle truck = new Vehicle();
		try {
			truck = towService.getVehicle(vehicleID);
		} catch (Exception e) {
			currentInstance = FacesContext.getCurrentInstance();
			FacesMessage message = null;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Failed to retrieve Tow Vehicle With ID: " + Integer.toString(vehicleID) + ". " + e.getMessage());
			currentInstance.addMessage(null, message);
		}
		return truck;
	}

	public void updateTowInfo() {
		// call update methods for Customer, Employee, and Vehicle
		String towUpdate = updateTow();
		String customerUpdate = updateCustomer();
		String vehicleUpdate = updateVehicle();
		String employeeUpdate = updateEmployee();

		if ((towUpdate != "Success") || (customerUpdate != "Success") || (vehicleUpdate != "Success")
				|| (employeeUpdate != "Success")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Failed to Update All Tow Data");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
	}

	/**
	 * call update methods from service layers for all Tow-Referenced objects
	 * 
	 * @return success/failure
	 */
	private String updateTow() {
		String status;
		try {
			towService.update(this.getTowVar());
			status = "Success";
		} catch (Exception e) {
			status = "Failure: " + e.getMessage();
		}
		return status;
	}

	private String updateCustomer() {
		String status;
		try {
			customerService.update(this.getCustomer());
			status = "Success";
		} catch (Exception e) {
			status = "Failure: " + e.getMessage();
		}
		return status;
	}

	private String updateEmployee() {
		String status;
		try {
			employeeService.update(this.getDriver());
			status = "Success";
		} catch (Exception e) {
			status = "Failure: " + e.getMessage();
		}
		return status;
	}

	private String updateVehicle() {
		String status;
		try {
			vehicleService.update(this.getVehicle());
			status = "Success";
		} catch (Exception e) {
			status = "Failure: " + e.getMessage();
		}
		return status;
	}

	public Tow getTowVar() {
		return towVar;
	}

	public void setTowVar(Tow towVar) {
		this.towVar = towVar;
		this.customer = getCustomer(towVar.getCustomerID());
		this.driver = getDriver(towVar.getDriverID());
		this.vehicle = getVehicle(towVar.getVehicleID());
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Employee getDriver() {
		return driver;
	}

	public void setDriver(Employee driver) {
		this.driver = driver;
	}
}
