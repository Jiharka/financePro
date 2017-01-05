package com.fp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fp.domain.Outcome;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

}