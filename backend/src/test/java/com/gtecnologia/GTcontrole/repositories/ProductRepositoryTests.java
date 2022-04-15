package com.gtecnologia.GTcontrole.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gtecnologia.GTcontrole.entities.Product;
import com.gtecnologia.GTcontrole.factory.Factory;

//TESTES||COMPONENTES RELACIONADOS AO SPRING DATA JPA||VALIDAR METODOS DO MEU REPOSITÓRIO:
@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repository;

	private long exintingId;
	private long nonExistingId;
	private long countTotalProduct;

	// FIXTURES
	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 1000L;
		countTotalProduct = 25L;
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

	//---TESTES PARA VALIDAR INSERÇÃO E ATUALIZAÇÃO( save ):
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {

		Product product = Factory.createProduct();
		product.setId(null);
		product = repository.save(product);

		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProduct + 1, product.getId());
	}

}
