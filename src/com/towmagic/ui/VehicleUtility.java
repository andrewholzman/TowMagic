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

import com.towmagic.dto.Vehicle;
import com.towmagic.service.IVehicleService;

@Named
@ManagedBean
@Scope("request")
public class VehicleUtility {
	
	@Inject 
	private IVehicleService vehicleService; //instance of the service layer object customerService
	@Inject
	private Vehicle vehic;
	
	private List<Vehicle> vehicles;
	private FacesContext currentInstance;
	
	/**
	 * Method is used to call the getAllVehicles() method from the service object to populate on create of the managed bean
	 * Used so that the List<vehicle> vehicles contains data when vehicles.xhtml renders
	 */
	@PostConstruct
	public void init() {
		if (vehicles == null) {
			try {
				vehicles = getVehicles();
			} catch (Exception e) {
				//
			} 
		}
	}
	
	/**
	 * Returns list of Vehicles
	 * @return
	 */
	public List<Vehicle> getVehicles() {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		if ((vehicles == null)||(vehicles.isEmpty())) { // call DAO method getCustomers to retrieve list of customer objects from database
			try {
				vehicleList = vehicleService.getAllVehicles();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Failed to retrieve vehicles from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		} else {
			vehicleList = vehicles;
		}
		return vehicleList;
	}
	
	
	
	/**
	 * calls the insert business logic for Vehicle
	 * @property vehicle - vehicle object to insert to the db
	 */
	public void addVehicle() {
		currentInstance = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			vehicleService.insert(vehic);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved","Vehicle Saved");
		} catch (Exception e) {
			message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Vehicle Failed to be Saved: " + e.getMessage());
		}
		currentInstance.addMessage(null, message);
	}
	
    public void onRowEdit(RowEditEvent event) {
    	//call update of DB
    	currentInstance = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		Vehicle editedVehic = (Vehicle)event.getObject();
		
    	try {
    		vehicleService.update(editedVehic);
    		msg = new FacesMessage("Truck Edited", Integer.toString(((Vehicle)event.getObject()).getId()));
    	} catch (Exception e) {
    		msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Vehicle Failed to be Updated: " + e.getMessage());
    	}
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    public String removeHandler(Vehicle vehic) {
    	int vehicleId = vehic.getId();
    	FacesMessage msg = null;
    	try {
    		vehicleService.remove(vehicleId);
    		msg = new FacesMessage("Truck: " + Integer.toString(vehicleId) +" Deleted");
    	} catch (Exception e) {
    		msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Vehicle Failed to be Deleted: " + e.getMessage());
    	}
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }
    

}
