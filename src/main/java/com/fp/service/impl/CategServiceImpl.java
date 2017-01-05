package com.fp.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fp.domain.Category;
import com.fp.exception.InvalidCategoryException;
import com.fp.repository.CategRepository;
import com.fp.service.CategService;

@Service
@Validated
public class CategServiceImpl implements CategService {
	@Inject
	private CategRepository repo;

	@Transactional
	@Override
	public Category save(Category item) throws InvalidCategoryException {
		String categName = item.getName();
		List<Category> allCateg = this.getList();
		boolean isItemAlreadyExist = allCateg.stream().map((s) -> s.getName())
				.anyMatch(categName::equals);
		if (isItemAlreadyExist) {
			throw new InvalidCategoryException("Sorry. The Category with name "
					+ categName + " already exist! ");
		}
		return repo.save(item);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Category> getList() {
		return repo.findAll();
	}

	@Override
	public Category getCtegById(Long id) {
		return repo.findOne(id);
	}

	@Override
	public void deleteCateg(Long id) throws Exception {
		if (repo.findOne(id) != null){
			repo.delete(id);
		}else{
			throw new Exception();
		}
		
	}

	@PostConstruct
	public void defaultCategory() {
		Category i = new Category("Food");
		Category i2 = new Category("Avto");
		Category i3 = new Category("Funs");
		repo.save(i);
		repo.save(i2);
		repo.save(i3);
	}

}
