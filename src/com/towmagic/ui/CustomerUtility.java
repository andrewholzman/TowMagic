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

import com.towmagic.dto.Address;
import com.towmagic.dto.Customer;
import com.towmagic.service.IAddressService;
import com.towmagic.service.ICustomerService;

@Named
@ManagedBean
@Scope("request")

public class CustomerUtility {
	
	@Inject 
	private ICustomerService customerService; //instance of the service layer object customerService
	@Inject
	private IAddressService addressService;
	@Inject
	private Customer cust;
	@Inject
	private Address address;
	
	private List<Customer> customers;
	private FacesContext currentInstance;
	
	/**
	 * Method is used to call the getAllCustomers() method from the service object to populate on create of the managed bean
	 * Used so that the List<Customer> customers contains data when customers.xhtml renders
	 */
	@PostConstruct
	public void init() {
		if (customers == null) {
			try {
				customers = getCustomers();
			} catch (Exception e) {
				//
			} 
		}
	}
	
	/**
	 * Returns list of customers
	 * @return
	 */
	public List<Customer> getCustomers() {
		// call DAO method getCustomers to retrieve list of customer objects from database
		List<Customer> customerList = new ArrayList<Customer>();
		if ((customers == null)||customers.isEmpty()) {
			try {
				customerList = customerService.getAllCustomers();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Failed to retrieve customers from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		} else {
			customerList = customers;
		}
		return customerList;
	}
	
	/**
	 * Returns the Address that matches the customer's AddressID from the database
	 * @param customer - the customer to pull address info for
	 * @return formatted Address information that matches cust's addressID prop
	 */
	public String getCustomerAddress(int addressID) {
		
		//int addressID = cust.getAddressID();
		Address customerAddress = new Address();
		//make DB call thru hibernate return the Address
		try {
			customerAddress = addressService.getCustomerAddress(addressID);
		} catch (Exception e) {
			currentInstance = FacesContext.getCurrentInstance();
			FacesMessage message = null;
			message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Failed to retrieve customer address " + e.getMessage());
			currentInstance.addMessage(null, message);
		}
		return customerAddress.toString();

	}
	
	/**
	 * calls the insert business logic for Address and then Customer
	 * @param cust - customer to insert to the db
	 * @param address - address to insert to the db
	 * @return result of the customer insert statement
	 */
	public String addCustomer() {
		String result;
		currentInstance = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		// call insert for Address, if failure return the failure message as result
		int addressID;
		try {
			addressID = addressService.insert(address);	
			result = "Address Insert Success";
		} catch (Exception e) {
			addressID = 0;
			result = "Address Insert Failed -" + e.getMessage();
			message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Address Failed to be Saved: " + e.getMessage());
			return result;
		}
		
		//address insert successful, set the address id for the customer and insert customer
		cust.setAddressID(addressID);
		try {
			customerService.insert(cust);
			result = "Customer Insert Success";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved","Customer Saved");
		} catch (Exception e) {
			result = "Customer Insert Failed -" + e.getMessage();
			message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Address Failed to be Saved: " + e.getMessage());
			return result;
		}
		currentInstance.addMessage(null, message);
		return result;
	}
	
	public void onRowEdit(RowEditEvent event) {
    	//call update of DB
    	currentInstance = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		Customer editedCust = (Customer)event.getObject();
		
    	try {
    		customerService.update(editedCust);
    		msg = new FacesMessage("Customer Edited", Integer.toString(((Customer)event.getObject()).getId()));
    	} catch (Exception e) {
    		msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Customer Failed to be Updated: " + e.getMessage());
    	}
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    public String removeHandler(Customer cust) {
    	int customerId = cust.getId();
    	FacesMessage msg = null;
    	try {
    		customerService.remove(customerId);
    		msg = new FacesMessage("Customer: " + Integer.toString(customerId) +" Deleted");
    	} catch (Exception e) {
    		msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Customer Failed to be Deleted: " + e.getMessage());
    	}
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

}
