package com.fp.service;

import java.math.BigDecimal;
import java.util.List;

import com.fp.domain.Outcome;

public interface OutcomeService {
	Outcome save(Outcome item);

	List<Outcome> getList();
	
	Outcome getOutcomeById(Long id);

    void deleteOutcome(Long id);
    
    BigDecimal getPlannedSum();
    
    BigDecimal getFactSum();
}
