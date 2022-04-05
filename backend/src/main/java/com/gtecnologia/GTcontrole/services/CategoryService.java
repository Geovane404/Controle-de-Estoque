package com.gtecnologia.GTcontrole.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTcontrole.dto.CategoryDTO;
import com.gtecnologia.GTcontrole.entities.Category;
import com.gtecnologia.GTcontrole.repositories.CategoryRepository;
import com.gtecnologia.GTcontrole.services.exception.ResourceNotFoundException;

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
		
		Page<Category>page = repository.findAll(pageable);
		return page.map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		Optional<Category> obj = repository.findById(id);
		
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category();
	
		entity.setName(dto.getName());
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(CategoryDTO dto, Long id) {
		try {
			Category entity = repository.getOne(id);
			
			entity.setName(dto.getName());
			entity = repository.save(entity);
	
			return new CategoryDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado!");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado!");
		}
		//Implementar tratamento de exceção para integridade de dados
	}
}
