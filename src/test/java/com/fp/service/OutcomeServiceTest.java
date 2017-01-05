package com.fp.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.fp.domain.Outcome;
import com.fp.repository.OutcomeRepository;
import com.fp.service.impl.OutcomeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OutcomeServiceTest {
	@Mock
	private OutcomeRepository repo;
	@InjectMocks
	OutcomeServiceImpl service;

	private Outcome firstOut, thecondOut;

	private List<Outcome> list;
	private Long id;

	@Before
	public void setUpRepoMock() throws Exception {
		
		firstOut = new Outcome();
		firstOut.setId(1L);
		thecondOut = new Outcome();

		list = new ArrayList<Outcome>(2);
		list.add(firstOut);
		list.add(thecondOut);

		id = firstOut.getId();
		
		when(repo.save(any(Outcome.class))).thenReturn(firstOut);
		when(repo.findAll()).thenReturn(list);
		when(repo.findOne(any(Long.class))).thenReturn(thecondOut);
	}

	@Test
	public void saveTest() {
		Outcome actual = service.save(firstOut);

		assertEquals(firstOut, actual);
		verify(repo, times(1)).save(any(Outcome.class));
	}

	

	@Test
	public void getListTest() {
		List<Outcome> actual = service.getList();

		assertEquals(list, actual);
		verify(repo, times(1)).findAll();
	}

	@Test
	public void getOutcomeByIdTest() {
		Outcome actual = service.getOutcomeById(id);
		assertEquals(thecondOut, actual);
		verify(repo, times(1)).findOne(id);
	}
	
	@Test
	public void deleteCategTest() throws Exception {
	    service.deleteOutcome(id);
		verify(repo, times(1)).delete(id);
	}

}
