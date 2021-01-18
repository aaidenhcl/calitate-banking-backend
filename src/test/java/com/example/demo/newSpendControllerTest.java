package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.controller.SpendController;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.service.RegionSpend;

@DataJpaTest
class newSpendControllerTest {

	@Autowired
	private SpendController sc;
	
	//need to fix testing
	@Test
	void testSpendController() throws NotAuthorizedException{
		List<RegionSpend> rs = sc.getRegionSpend("Authorization");
		assertTrue(rs.isEmpty());
	}

}
