package com.isep.acme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;

@Repository
public interface AggregatedRatingRepository extends JpaRepository<AggregatedRating, Long> {

	@Query("SELECT a FROM AggregatedRating a WHERE a.product=:product")
	Optional<AggregatedRating> findByProductId(Product product);

}
