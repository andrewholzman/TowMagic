package com.towmagic.service.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.towmagic.dao.ITowDAO;
import com.towmagic.dto.Tow;
import com.towmagic.service.TowService;

import junit.framework.TestCase;

public class TestTowService extends TestCase {

	private TowService towService;
	private List<Tow> towList;

	@Test
	public void testGetAllTows() throws Exception {
		givenTheTowListHasATowDOA();
		whenATowListIsFetched();
		thenTowListIsReturnedWithCorrectSize();
	}

	@Test
	public void testInsertTow() {
		givenTheTowListHasATowDOA();
		whenATowObjectIsInsertedWithoutNullFields();
		thenTowListIsReturnedWithCorrectSize();
	}
	
	@Test
	public void testRemoveTow() throws Exception {
		givenTheTowListHasATowDOA();
		whenAFoundTowIDIsInputtedAndRemoved();
		thenTowListIsReturnedWithCorrectSize();
	}

	private void givenTheTowListHasATowDOA() {
		towService = new TowService();
	
		// mock tow object
		ITowDAO towDAO = mock(ITowDAO.class);
	
		// the created tow list
		List<Tow> constructTowList = constructTowList();
	
		try {
			when(towDAO.getTows()).thenReturn(constructTowList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		towService.setTowDAO(towDAO);
	}

	private void whenATowListIsFetched() throws Exception {
		towList = towService.getAllTows();
	}

	private void whenAFoundTowIDIsInputtedAndRemoved() throws Exception {
		towList = new ArrayList<Tow>();
		
		Tow tow = new Tow();
		tow.setId(1);
		tow.setCustomerID(1);
		tow.setVehicleID(200);
		tow.setEstimateTime(new Date(2018, 4, 1));
		tow.setStatus("");
		towList.add(tow);
		
		Tow towTwo = new Tow();
		towTwo.setId(2);
		towTwo.setCustomerID(2);
		towTwo.setVehicleID(250);
		towTwo.setEstimateTime(new Date(2018, 4, 1));
		towTwo.setStatus("");
		towList.add(towTwo);
		
		towList = towService.getAllTows();
		towService.remove(1);
	}

	private void whenATowObjectIsInsertedWithoutNullFields() {
		try {
			Tow tow = new Tow();
			tow.setId(1);
			tow.setCustomerID(2);
			tow.setVehicleID(200);
			tow.setEstimateTime(new Date(2018, 4, 1));
			tow.setStatus("");

			towService.getTowDAO().insertTow(tow);
			towList = new ArrayList<Tow>();
			towList.add(tow);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void thenTowListIsReturnedWithCorrectSize() {
		assertEquals(1, towList.size());
	}

	private List<Tow> constructTowList() {
		List tows = new ArrayList<Tow>();

		Tow firstTow = new Tow();
		firstTow.setId(1);
		firstTow.setCustomerID(2);
		firstTow.setVehicleID(200);
		firstTow.setEstimateTime(new Date(2018, 4, 1));
		firstTow.setStatus("");
		tows.add(firstTow);

		return tows;
	}
}
