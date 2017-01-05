package com.fp.service;

import java.util.List;

import com.fp.domain.Category;

public interface CategService {
	Category save(Category item);

	List<Category> getList();

	Category getCtegById(Long id);

    void deleteCateg(Long id) throws Exception;

}
