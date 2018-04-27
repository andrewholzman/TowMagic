package com.towmagic.ui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;

import com.towmagic.dto.Customer;
import com.towmagic.dto.Employee;
import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;
import com.towmagic.service.ITowService;

@Named
@ManagedBean
@Scope("request")
public class TowUtility {
	@Inject
	private ITowService towService; // instance of the service layer object

	@Inject
	private Tow towVar;
	
	@Inject
	private TowVO towVO;

	private List<Tow> tows;
	private FacesContext currentInstance;

	/**
	 * Method is used to call the getAllCustomers() method from the service
	 * object to populate on create of the managed bean Used so that the
	 * List<Customer> customers contains data when customers.xhtml renders
	 */
	@PostConstruct
	public void init() {
		if (tows == null) {
			try {
				tows = getTows();
			} catch (Exception e) {
				//
			}
		}
	}

	/**
	 * Returns list of tows
	 * @return
	 */
	public List<Tow> getTows() {
		// call DAO method getTows to retrieve list of customer objects from
		List<Tow> towList = new ArrayList<Tow>();
		if ((tows == null) || tows.isEmpty()) {
			try {
				towList = towService.getAllTows();
			} catch (Exception e) {
				currentInstance = FacesContext.getCurrentInstance();
				FacesMessage message = null;
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed",
						"Failed to retrieve tows from the database" + e.getMessage());
				currentInstance.addMessage(null, message);
			}
		} else {
			towList = tows;
		}
		return towList;
	}
	
	/**
	 * calls the insert business logic for Tow
	 * @property tow - tow object to insert to the db
	 */
	public void addVehicle() {
		currentInstance = FacesContext.getCurrentInstance();
		FacesMessage message = null;
		try {
			towService.insert(towVar);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved","Tow Saved");
		} catch (Exception e) {
			message =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Tow Failed to be Saved: " + e.getMessage());
		}
		currentInstance.addMessage(null, message);
	}
	
	/**
	 * Retrieve customer info in formatted string
	 * @param customerID
	 * @return
	 */
	public String getCustomerString(int customerID) {
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
		return customer.toString();
	}
	
	/**
	 * retrieve Driver info in formatted string
	 * @param driverID
	 * @return
	 */
	public String getDriverString(int driverID) {
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
		return driver.toString();
	}
	
	/**
	 * retrieve truck info in formatted string
	 * @param vehicleID
	 * @return
	 */
	public String getVehicleString(int vehicleID) {
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
		return truck.toString();
	}
	

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
	
	//
	public void onRowEdit(RowEditEvent event) {
    	//call update of DB
    	currentInstance = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		Tow editedTow = (Tow)event.getObject();
		
    	try {
    		towService.update(editedTow);
    		msg = new FacesMessage("Tow Edited", Integer.toString(((Tow)event.getObject()).getId()));
    	} catch (Exception e) {
    		msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Tow Failed to be Updated: " + e.getMessage());
    	}
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String editHandler(Tow tow) {
    	FacesMessage msg = new FacesMessage("Edit Selected ID: " + Integer.toString(tow.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        towVO.setTowVar(tow);
        return "editTow.xhtml?faces-redirect=true";
    }
	
    public String removeHandler(Tow tow) {
    	int towId = tow.getId();
    	FacesMessage msg = null;
    	try {
    		towService.remove(towId);
    		msg = new FacesMessage("Truck: " + Integer.toString(towId) +" Deleted");
    	} catch (Exception e) {
    		msg =  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed","Tow Failed to be Deleted: " + e.getMessage());
    	}
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }
}
