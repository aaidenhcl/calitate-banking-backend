package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrlTemplate;

import java.util.List;

import org.hibernate.hql.internal.ast.tree.ExpectedTypeAwareNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.SpendController;
import com.example.demo.service.RegionSale;
import com.example.demo.service.RegionSpend;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SpendController.class)
class spendControllerTest {
}

/*
	@Autowired
	private MockMvc mvc;
	
//	static final String auth = "Authorization";
	
	@Test
	void test() throws Exception {
  	    RequestBuilder request = MockMvcRequestBuilders.get("spends/regionSpend");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(Expected,result.getResponse().getContentAsString());
	}
	
//	@Test
//	void helloTest() {
//		SpendController sc = new SpendController();
//		List<RegionSpend> rs = sc.getRegionSpend(auth);
//		
//	}

}*/
