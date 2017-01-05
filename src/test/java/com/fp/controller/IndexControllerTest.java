package com.fp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fp.controllers.IndexController;

public class IndexControllerTest {

	@InjectMocks
	private IndexController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();
	}

	@Test
	public void test() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(forwardedUrl("index"));
	}

}
