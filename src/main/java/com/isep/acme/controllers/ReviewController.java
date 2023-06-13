package com.isep.acme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.model.Review;
import com.isep.acme.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "Endpoints for managing Review")
@RestController
class ReviewController {

	@Autowired
	private ReviewService rService;

	@Autowired
	private ReviewMapper reviewMapper;

	@Operation(summary = "finds a product through its sku and shows its review by status")
	@GetMapping("/reviews/{status}/products/{sku}")
	public ResponseEntity<List<ReviewDTO>> findById(@PathVariable(value = "sku") final String sku,
			@PathVariable(value = "status") final String status) {

		List<Review> reviews = rService.getReviewsOfProduct(sku, status);
    
        List<ReviewDTO> reviewDTOs = reviewMapper.toDtoList(reviews);

		return ResponseEntity.ok().body(reviewDTOs);
	}

	@Operation(summary = "gets review by user")
	@GetMapping("/reviews/{user}")
	public ResponseEntity<List<ReviewDTO>> findReviewByUser(@PathVariable(value = "user") final String user) {

		List<Review> reviews = rService.findReviewsByUser(user);
        List<ReviewDTO> reviewDTOs = reviewMapper.toDtoList(reviews);
		

		return ResponseEntity.ok().body(reviewDTOs);
	}

	@Operation(summary = "gets pending reviews")
	@GetMapping("/reviews/pending")
	public ResponseEntity<List<ReviewDTO>> getPendingReview() {

		List<Review> reviews = rService.findPendingReview();

		List<ReviewDTO> reviewDTOs = reviewMapper.toDtoList(reviews);
		return ResponseEntity.ok().body(reviewDTOs);
	}

    @Operation(summary = "gets approved reviews")
	@GetMapping("/reviews/approved")
	public ResponseEntity<List<ReviewDTO>> getApprovedReview() {

		List<Review> reviews = rService.findApprovedReview();

		List<ReviewDTO> reviewDTOs = reviewMapper.toDtoList(reviews);
		return ResponseEntity.ok().body(reviewDTOs);
	}

    /* @Operation(summary = "get all votes of a review")
	@GetMapping("/reviews/{id}/votes")
	public ResponseEntity<List<ReviewDTO>> getApprovedReview() {

		List<Review> reviews = rService.findApprovedReview();

		List<ReviewDTO> reviewDTOs = reviewMapper.toDtoList(reviews);
		return ResponseEntity.ok().body(reviewDTOs);
	} */

}
