package com.towmagic.dao;

import com.towmagic.dto.Tow;
import com.towmagic.dto.Vehicle;

public interface IManagerDAO extends IPersonDAO {
/*
 * Interface to provide for business logic related to a Manager, implementing log from Person
 */
	
	public void addToActiveTows(Tow tow) throws Exception;
	public void addToCompletedTows(Tow tow) throws Exception;
	public void addVehicleToInventory(Vehicle vehicle) throws Exception;
	public void removeVehicleFromInventory(Vehicle vehicle) throws Exception;
}
