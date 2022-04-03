package com.gtecnologia.GTcontrole.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTcontrole.dto.CategoryDTO;
import com.gtecnologia.GTcontrole.entities.Category;
import com.gtecnologia.GTcontrole.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private	CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll () {
		
		List<Category> list  =  repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findALLPaged(Pageable pageable) {
		
		Page<Category>pagelist = repository.findAll(pageable);
		return pagelist.map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		Category entity = repository.findById(id).get();
		return new CategoryDTO(entity);
	}
}
