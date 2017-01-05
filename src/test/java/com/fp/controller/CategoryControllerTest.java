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

import com.fp.controllers.CategoryController;
import com.fp.domain.Category;
import com.fp.service.CategService;
import com.fp.service.OutcomeService;

public class CategoryControllerTest {

	@Mock
	private OutcomeService outcomeService;
	@Mock
	private CategService categService;
	@InjectMocks
	private CategoryController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void list() throws Exception {
		this.mockMvc.perform(get("/categories/")).andExpect(status().isOk())
				.andExpect(forwardedUrl("categories"))
				.andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("categsNames"));
		verify(categService, times(1)).getList();
		verify(outcomeService, times(1)).getList();
		verifyNoMoreInteractions(categService);
	}


	@Test
	public void newProduct() throws Exception {
		this.mockMvc.perform(get("/category/new/")).andExpect(status().isOk())
				.andExpect(forwardedUrl("categs_form"))
				.andExpect(model().attributeExists("category"));
	}
	
	
	@Test
	public void saveProduct() throws Exception {
		MvcResult returnedResult = this.mockMvc.perform(post("/category/"))
				.andReturn();
		String url = returnedResult.getResponse().getRedirectedUrl();
		Assert.assertEquals("/categories", url);
		verify(categService, times(1)).save(any(Category.class));
		verifyNoMoreInteractions(categService);
	}

	@Test
	public void delete() throws Exception {
		Long id = 1L;
		MvcResult returnedResult = this.mockMvc.perform(
				get("/category/delete/" + id + "/")).andReturn();
		String url = returnedResult.getResponse().getRedirectedUrl();
		Assert.assertEquals("/categories", url);
		verify(categService, times(1)).deleteCateg(id);
		verifyNoMoreInteractions(categService);
	}

	@Test
	public void edit() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/category/edit/" + id + ""))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("categs_form"));

		verify(categService, times(1)).getCtegById(id);
		verifyNoMoreInteractions(categService);
	}

	@Test
	public void show() throws Exception {
		Long id = 1L;
		this.mockMvc.perform(get("/category/" + id + ""))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("categs_details"));

		verify(categService, times(1)).getCtegById(id);
		verifyNoMoreInteractions(categService);
	}
}
