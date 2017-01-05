package com.fp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fp.domain.Category;

@Repository
public interface CategRepository extends JpaRepository<Category, Long> {

}

