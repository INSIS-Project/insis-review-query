package com.isep.acme.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;

@Service
public interface ReviewService {

	Iterable<Review> getAll();

	List<Review> getReviewsOfProduct(String sku, String status);

	Review create(Review review);

	Double getWeightedAverage(Product product);

	Boolean deleteReview(Long reviewId);

	List<Review> findPendingReview();

    List<Review> findApprovedReview();

	Review moderateReview(Long reviewID, String approved);

	List<Review> findReviewsByUser(String user);
}
