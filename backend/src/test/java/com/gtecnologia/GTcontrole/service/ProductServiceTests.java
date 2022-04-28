package com.gtecnologia.GTcontrole.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gtecnologia.GTcontrole.repositories.ProductRepository;
import com.gtecnologia.GTcontrole.services.ProductService;
import com.gtecnologia.GTcontrole.services.exception.ResourceNotFoundException;

//TESTES || UNITARIO COM MOCKITO - MOCK || VALIDAR METODOS DA MINHA CLASSE SERVICE:
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	
	private long existingId;
	private long nonExintingId;
	
	// FIXTURES
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExintingId = 3L;
		
		
		// 2°-VOID => ação -- quando (Comportamento simulado do Repository)
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExintingId);
		
	}
	
	//---TESTES PARA VALIDAR BUSCAS:
	
	
	// ---TESTES PARA VALIDAR INSERÇÕES E ATUALIZAÇÕES:
	
	
	// ---TESTES PARA VALIDAR DELEÇÕES:
	@Test
	public void deleteShouldDoNothingWhenIdexist() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteThrowResourceNotFoundExceptionWhenIdNoExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExintingId);
		});

		verify(repository).deleteById(nonExintingId);
	}
	
}
