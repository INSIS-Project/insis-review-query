package com.isep.acme.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.isep.acme.model.Product;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Optional<Product> findBySku(String sku);

	// Delete the product when given the SKU
	@Transactional
	@Modifying
	@Query("DELETE FROM Product p WHERE p.sku=:sku")
	void deleteBySku(@Param("sku") String sku);

}
