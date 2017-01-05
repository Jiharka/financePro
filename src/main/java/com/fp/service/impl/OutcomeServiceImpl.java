package com.fp.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fp.domain.Outcome;
import com.fp.repository.OutcomeRepository;
import com.fp.service.OutcomeService;

@Service
@Validated
public class OutcomeServiceImpl implements OutcomeService {

	private OutcomeRepository repo;

	@Inject
	public OutcomeServiceImpl(OutcomeRepository repo) {
		this.repo = repo;
	}

	@Transactional
	@Override
	public Outcome save(Outcome item) {
		return repo.save(item);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Outcome> getList() {
		return repo.findAll();
	}

	@Override
	public Outcome getOutcomeById(Long id) {
		return repo.findOne(id);
	}

	@Override
	public void deleteOutcome(Long id) {
		repo.delete(id);
	}

	@Override
	public BigDecimal getPlannedSum() {
		List<Outcome> outcomes = getList();
		BigDecimal sum = new BigDecimal(0);
		for (Outcome out : outcomes) {
			BigDecimal planned = out.getPlanned();
			if (planned == null) {
				planned = new BigDecimal(0);
			}
			sum = sum.add(planned);
		}
		return sum;
	}

	@Override
	public BigDecimal getFactSum() {
		List<Outcome> incomes = getList();
		BigDecimal sum = new BigDecimal(0);
		;
		for (Outcome inc : incomes) {
			sum = sum.add(inc.getFact());
		}
		return sum;
	}

}
