package com.fp.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;

import com.fp.controllers.OutcomeController;
import com.fp.domain.Outcome;
import com.fp.service.CategService;
import com.fp.service.OutcomeService;

public class OutcomeControllerTest {

	@Mock
	private OutcomeService outcomeService;
	@Mock
	private CategService categService;
	@InjectMocks
	private OutcomeController outcomeController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(outcomeController)
				.build();
	}

	@Test
	public void list() throws Exception {
		this.mockMvc.perform(get("/outcomes/")).andExpect(status().isOk())
				.andExpect(forwardedUrl("outcomes"));
		verify(outcomeService, times(1)).getList();
		verify(outcomeService, times(1)).getPlannedSum();
		verify(outcomeService, times(1)).getFactSum();
		verifyNoMoreInteractions(outcomeService);
	}

	@Test
	public void newProduct() throws Exception {
		this.mockMvc.perform(get("/outcome/new/")).andExpect(status().isOk())
				.andExpect(forwardedUrl("outcomeform"))
				.andExpect(model().attributeExists("outcome"))
				.andExpect(model().attributeExists("allCateg"));
		verify(categService, times(1)).getList();

	}

	@Test
	public void saveProduct() throws Exception {
		MvcResult returnedResult = this.mockMvc.perform(post("/outcome/"))
				.andReturn();
		String url = returnedResult.getResponse().getRedirectedUrl();
		Assert.assertEquals("/outcomes", url);
		verify(outcomeService, times(1)).save(any(Outcome.class));
		verifyNoMoreInteractions(outcomeService);
	}

	@Test
	public void delete() throws Exception {
		Long id = 1L;
		MvcResult returnedResult = this.mockMvc.perform(
				get("/outcome/delete/" + id + "/")).andReturn();
		String url = returnedResult.getResponse().getRedirectedUrl();
		Assert.assertEquals("/outcomes", url);
		verify(outcomeService, times(1)).deleteOutcome(id);
		verifyNoMoreInteractions(outcomeService);
	}

	@Test
	public void edit() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/outcome/edit/" + id + ""))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("outcomeform"))
				.andExpect(model().attributeExists("allCateg"));

		verify(outcomeService, times(1)).getOutcomeById(id);
		verify(categService, times(1)).getList();
		verifyNoMoreInteractions(outcomeService);
	}
	@Test
	public void show() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/outcome/" + id + ""))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("outcomeshow"));

		verify(outcomeService, times(1)).getOutcomeById(id);
		verifyNoMoreInteractions(outcomeService);
	}
}
