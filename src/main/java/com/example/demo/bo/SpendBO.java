package com.example.demo.bo;

import com.example.demo.dao.SpendRepo;
import com.example.demo.service.RegionSpend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class SpendBO {

	@Autowired
	SpendRepo repo;
	

	public List<RegionSpend> getRegionSpend(){
		List<RegionSpend> rs = repo.getRegionSpend();
		return rs;
	}
	
	
	
}
