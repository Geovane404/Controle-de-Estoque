package com.gtecnologia.GTcontrole.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gtecnologia.GTcontrole.entities.Product;
import com.gtecnologia.GTcontrole.repositories.CategoryRepository;
import com.gtecnologia.GTcontrole.repositories.ProductRepository;
import com.gtecnologia.GTcontrole.services.ProductService;

//TESTES || UNITARIO COM MOCKITO - MOCK || VALIDAR METODOS DA MINHA CLASSE SERVICE:
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Mock
	private CategoryRepository categoryRepository;

	private PageImpl<Product> page;

	// FIXTURES
	@BeforeEach
	void setUp() throws Exception {
		
		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		
	}
	//---TESTES PARA VALIDAR BUSCAS:
	

}
