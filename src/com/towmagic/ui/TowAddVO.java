package com.towmagic.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
public class TowAddVO {
	@Inject
	private ITowService towService;
	@Inject
	private ICustomerService customerService;
	@Inject
	private IEmployeeService employeeService;
	@Inject
	private IVehicleService vehicleService;

	private FacesContext currentInstance;

	@Inject
	private Tow towVar;

	private Customer customer;
	private Vehicle vehicle;
	private Employee driver;


	private List<Customer> customerObjectList;
	private List<Vehicle> vehicleObjectList;
	private List<Employee> driverObjectList;

	@PostConstruct
	public void init() {
		setCustomerObjectList(getCustomerObjectList());
		setVehicleObjectList(getVehicleObjectList());
		setDriverObjectList(getDriverObjectList());
	}

	
	/**
	 * this would be the method that would grab the customer, vehicle, and drivers selected by the user from the drop
	 * downs, but I could not get the Converter objects to work correctly in the time frame, so i'm mocking a submission below 
	 * to show how it would've worked.
	 */
	public void submitInfo() {


		//creating these for the mock. This would normally be the objects selected in the drop down
		Customer customer = customerObjectList.get(0);
		Vehicle vehicle = vehicleObjectList.get(0);
		Employee driver = driverObjectList.get(0);

		Tow tow = new Tow();
		tow.setCustomerID(customer.getId());
		tow.setDriverID(driver.getId());
		tow.setVehicleID(vehicle.getId());
		tow.setStatus("Dummy Status. Created for demonstration");
		tow.setEstimateTime(new Date());
		try {
			towService.insert(tow);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
					"Failed to Update All Tow Data");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");

	}

	public void updateCustomer(Integer cust) {
		this.setCustomer(this.getCustomer(cust.toString()));
		// this.setCustomer(cust);
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

	



	public List<Customer> getCustomerObjectList() {
		List<Customer> customerItemList = new ArrayList<Customer>();
		if ((customerObjectList == null) || (customerObjectList.isEmpty())) {
			try {
				customerItemList = customerService.getAllCustomers();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
						"Failed to retrieve Customers from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		}
		return customerItemList;
	}

	public void setCustomerObjectList(List<Customer> customerObjectList) {
		this.customerObjectList = customerObjectList;
	}

	/**
	 * used in custom CustomerConvert converter's getObjectFromString() method for drop downs.
	 * Woujld be duplicated for Vehicle and Employee
	 * @param id - customer from the List<Customer> to return the object for
	 * @return
	 */
	public Customer getCustomer(String id) {
		if (id == null) {
			throw new IllegalArgumentException("No ID Provided");
		}

		for (Customer customer : customerObjectList) {
			if (id.equals(Integer.toString(customer.getId()))) {
				return customer;
			}
		}
		return null;
	}

	public List<Vehicle> getVehicleObjectList() {
		List<Vehicle> vehicleItemList = new ArrayList<Vehicle>();
		if ((vehicleObjectList == null) || (vehicleObjectList.isEmpty())) {
			try {
				vehicleItemList = vehicleService.getAllVehicles();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
						"Failed to retrieve Vehicles from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		}
		return vehicleItemList;
	}

	public void setVehicleObjectList(List<Vehicle> vehicleObjectList) {
		this.vehicleObjectList = vehicleObjectList;
	}

	public List<Employee> getDriverObjectList() {
		List<Employee> driverItemList = new ArrayList<Employee>();
		if ((driverObjectList == null) || (driverObjectList.isEmpty())) {
			try {
				driverItemList = employeeService.getAllEmployees();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
						"Failed to retrieve Employees from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		}
		return driverItemList;
	}

	public void setDriverObjectList(List<Employee> driverObjectList) {
		this.driverObjectList = driverObjectList;
	}
}