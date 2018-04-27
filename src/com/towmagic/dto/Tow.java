package com.towmagic.dto;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
@Named
@ManagedBean
@Scope("session")
public class Tow {
	private int id;
	private int customerID;
	private int driverID;
	private int vehicleID;
	@Temporal(TemporalType.TIMESTAMP) //used to map the mysql datetime object
	private Date estimateTime;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	public Date getEstimateTime() {
		return estimateTime;
	}
	public void setEstimateTime(Date estimateTime) {
		this.estimateTime = estimateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}
	
}
