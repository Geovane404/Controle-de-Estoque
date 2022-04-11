package com.gtecnologia.GTcontrole.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gtecnologia.GTcontrole.entities.Product;



//TESTES||COMPONENTES RELACIONADOS AO SPRING DATA JPA||VALIDAR METODOS DO MEU REPOSITÓRIO:
@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	
	private long exintingId;
	private long nonExistingId;

	//FIXTURES
	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 1000L;
	
	}

	//---TESTE PARA VALIDAR BUSCAS:
	@Test
	public void findByIdShouldReturnOptionalNotNullWhenIdExist() {

		Optional<Product> result = repository.findById(exintingId);

		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnOptionalNullWhenIdNoExist() {

		Optional<Product> result = repository.findById(nonExistingId);

		Assertions.assertFalse(result.isPresent());
		Assertions.assertTrue(result.isEmpty());
	}

}
