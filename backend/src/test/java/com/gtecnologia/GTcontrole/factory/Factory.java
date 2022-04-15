package com.gtecnologia.GTcontrole.factory;

import java.time.Instant;

import com.gtecnologia.GTcontrole.entities.Category;
import com.gtecnologia.GTcontrole.entities.Product;

public class Factory {
	
	public static Product createProduct() {
		
		Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com//img.png",
				Instant.parse("2022-02-16T03:00:00Z"));
		product.getCategories().add(createCategory());
		
		return product;
	}

	private static Category createCategory() {
		
		return new Category(1L, "Eletronics");
	}
}
