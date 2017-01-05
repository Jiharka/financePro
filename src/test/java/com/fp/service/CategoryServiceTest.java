package com.fp.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fp.domain.Category;
import com.fp.exception.InvalidCategoryException;
import com.fp.repository.CategRepository;
import com.fp.service.impl.CategServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	@Mock
	private CategRepository repo;
	@InjectMocks
	CategServiceImpl service;

	private Category categ1, categ2, categ3;

	private List<Category> list;
	private Long id;
	private String food = "Food";
	private String fun = "Fun";
	private String avto = "Avto";

	@Before
	public void setUpRepoMock() throws Exception {
		categ1 = new Category(5L, food);
		categ2 = new Category(6L, fun);
		categ3 = new Category(7L, avto);

		list = new ArrayList<Category>(2);
		list.add(categ1);
		list.add(categ2);

		id = categ1.getId();
		
		when(repo.save(categ3)).thenReturn(categ3);
		when(repo.findAll()).thenReturn(list);
		when(repo.findOne(any(Long.class))).thenReturn(categ1);
	}

	@Test
	public void saveSuccessTest() {
		Category actual = service.save(categ3);

		assertEquals(categ3, actual);
		verify(repo, times(1)).save(any(Category.class));
	}

	@Test(expected = InvalidCategoryException.class)
	public void saveExceptionTdest() {
		Category existedCategory = new Category();
		existedCategory.setName("Food");
		assertNull(service.save(existedCategory));
		verify(repo, never()).save(any(Category.class));
	}

	@Test
	public void getListTest() {
		List<Category> actual = service.getList();

		assertEquals(list, actual);
		verify(repo, times(1)).findAll();
	}

	@Test
	public void getCtegByIdTest() {
		Category ctegById = service.getCtegById(id);
		assertEquals(categ1, ctegById);
		verify(repo, times(1)).findOne(id);
	}
	
	@Test
	public void deleteCategTest() throws Exception {
	    service.deleteCateg(id);
		verify(repo, times(1)).delete(id);
	}

}
