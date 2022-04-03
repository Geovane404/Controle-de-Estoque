package com.gtecnologia.GTcontrole.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtecnologia.GTcontrole.dto.CategoryDTO;
import com.gtecnologia.GTcontrole.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll (){
		
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/paged")
	public ResponseEntity<Page<CategoryDTO>> findAllPaged(Pageable pageable) {
		
		Page<CategoryDTO> pagelist = service.findALLPaged(pageable);
		return ResponseEntity.ok().body(pagelist);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		
		CategoryDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}

