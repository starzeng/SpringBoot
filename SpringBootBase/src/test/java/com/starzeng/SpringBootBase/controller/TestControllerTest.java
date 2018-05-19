package com.starzeng.SpringBootBase.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TestControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
	}

	@Test
	public void testTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON_UTF8))//
					.andDo(MockMvcResultHandlers.print())//
					.andExpect(MockMvcResultMatchers.status().isOk())//
					.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
