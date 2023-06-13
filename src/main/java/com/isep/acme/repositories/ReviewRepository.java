package com.isep.acme.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

	@Query("SELECT r FROM Review r WHERE r.product=:product")
	Optional<List<Review>> findByProductId(Product product);

	@Query("SELECT r FROM Review r WHERE r.approvalStatus='pending'")
	Optional<List<Review>> findPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='approved'")
	Optional<List<Review>> findApprovedReview();

	@Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status")
	Optional<List<Review>> findByProductIdStatus(Product product, String status);

	@Query("SELECT r FROM Review r WHERE r.userName=:user")
    Optional<List<Review>> findByUser(String user);
}
