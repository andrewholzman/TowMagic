package com.towmagic.dto;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
@Named
@ManagedBean
@Scope("session")
public class Manager extends Person {
	private List<Tow> activeTows;
	private List<Tow> completedTows;
	private List<Vehicle> inventory;
	public List<Tow> getActiveTows() {
		return activeTows;
	}
	public void setActiveTows(List<Tow> activeTows) {
		this.activeTows = activeTows;
	}
	public List<Tow> getCompletedTows() {
		return completedTows;
	}
	public void setCompletedTows(List<Tow> completedTows) {
		this.completedTows = completedTows;
	}
	public List<Vehicle> getInventory() {
		return inventory;
	}
	public void setInventory(List<Vehicle> inventory) {
		this.inventory = inventory;
	}
}
