package com.gtecnologia.GTcontrole.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtecnologia.GTcontrole.dto.CategoryDTO;
import com.gtecnologia.GTcontrole.dto.ProductDTO;
import com.gtecnologia.GTcontrole.entities.Category;
import com.gtecnologia.GTcontrole.entities.Product;
import com.gtecnologia.GTcontrole.repositories.CategoryRepository;
import com.gtecnologia.GTcontrole.repositories.ProductRepository;
import com.gtecnologia.GTcontrole.services.exception.DatabaseException;
import com.gtecnologia.GTcontrole.services.exception.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		
		List<Product> list = repository.findAll();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		
		Page<Product> page = repository.findAll(pageable); 
		return page.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		Optional<Product> obj = repository.findById(id);
		
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO insert( ProductDTO dto) {
		
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			
			return new ProductDTO(entity);
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
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade! você não pode excluir uma entidade que possui dependentes.");
		}
	}
	//-----------METODOS AUXILIARES------------------------------//
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
	
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		entity.setDate(dto.getDate());
		
		entity.getCategories().clear();
		
		for(CategoryDTO catDto : dto.getCategories()) {
			
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}
}
