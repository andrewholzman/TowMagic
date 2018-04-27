package com.towmagic.service.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.towmagic.dao.IVehicleDAO;
import com.towmagic.dto.Vehicle;
import com.towmagic.service.VehicleService;

import junit.framework.TestCase;

public class TestVehicleService extends TestCase {

	private VehicleService vehicleService;
	private List<Vehicle> vehicleList;
	private Vehicle emptyVehicle;
	
	@Test
	public void testGetAllVehicles() throws Exception {
		givenTheVehicleListHasAVehicleDOA();
		whenAVehicleListIsFetched();
		thenVehicleListIsReturnedWithCorrectSize(2);
	}

	@Test (expected = Exception.class)
	public void testInsertVehicle() {
		givenTheVehicleListHasAVehicleDOA();
		whenAVehicleIsCreatedWithEmptyFields();
		thenExceptionIsThrown();
	}

	@Test
	public void testRemoveVehicle() throws Exception {
		givenTheVehicleListHasAVehicleDOA();
		whenAVehicleIsInputtedAndRemoved();
		thenVehicleServiceListIsReturnedWithCorrectSize();
	}
	
	private void givenTheVehicleListHasAVehicleDOA() {
		vehicleService = new VehicleService();
	
		// mock vehicle object
		IVehicleDAO vehicleDAO = mock(IVehicleDAO.class);
	
		// the created vehicle list
		List<Vehicle> constructVehicleList = constructVehicleList();
	
		try {
			when(vehicleDAO.getVehicles()).thenReturn(constructVehicleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//vehicleService.setTowDAO(vehicleDAO);
	}

	private void whenAVehicleListIsFetched() throws Exception {
		IVehicleDAO vehicleDAO = mock(IVehicleDAO.class);
		vehicleList = vehicleDAO.getVehicles();
	}

	/*
	 * @param size expected value of vehicles in list
	 */
	private void thenVehicleListIsReturnedWithCorrectSize(int size) {
		assertEquals(size, vehicleList.size());		
	}

	private void whenAVehicleIsCreatedWithEmptyFields() {
		emptyVehicle = new Vehicle();
		emptyVehicle.setMake("Honda");
		emptyVehicle.setModel("Accord");
		emptyVehicle.setYear("");
		emptyVehicle.setColor("");
	}

	private void thenExceptionIsThrown() {
		try {
			vehicleService.insert(emptyVehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void whenAVehicleIsInputtedAndRemoved() throws Exception {		
		Vehicle v1 = new Vehicle();
		v1.setId(1);
		v1.setMake("Honda");
		v1.setModel("Accord");
		v1.setYear("2010");
		v1.setColor("Black");
		v1.setPlate("UC0012");
		vehicleService.insert(v1);
		
		Vehicle v2 = new Vehicle();
		v2.setId(2);
		v2.setMake("Mazda");
		v2.setModel("Mazda6");
		v2.setYear("2004");
		v2.setColor("Gold");
		v2.setPlate("CU0998");
		vehicleService.insert(v2);		
		
		vehicleService.remove(1);
		vehicleService.remove(2);
	}

	private void thenVehicleServiceListIsReturnedWithCorrectSize() throws Exception {
		IVehicleDAO vehicleDAO = mock(IVehicleDAO.class);
		assertEquals(0,vehicleDAO.getVehicles().size());		
	}

	private List<Vehicle> constructVehicleList() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Vehicle v1 = new Vehicle();
		v1.setMake("Honda");
		v1.setModel("Accord");
		v1.setYear("2010");
		v1.setColor("Black");
		vehicles.add(v1);
		
		Vehicle v2 = new Vehicle();
		v2.setMake("Mazda");
		v2.setModel("Mazda6");
		v2.setYear("2004");
		v2.setColor("Gold");
		vehicles.add(v2);
		
		return vehicles;
	}
}
