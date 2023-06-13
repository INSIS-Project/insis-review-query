package com.isep.acme.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
import com.isep.acme.services.RatingService;
import com.isep.acme.services.RestService;
import com.isep.acme.services.ReviewService;
import com.isep.acme.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    
    @Autowired
    RatingService ratingService;
    
    @Autowired
    RestService restService;
    
    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Iterable<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review create(Review review) {
        log.info("Aqui o ID da review: {} e o product {}", 
                        review.getIdReview(),
                        review.getProduct().getSku());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsOfProduct(String sku, String status) {

        log.info("Aqui o sku: {} e o status {}", sku, status);

        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<Review> reviews = reviewRepository.findByProductIdStatus(product, status)
                .orElseThrow(() -> new IllegalArgumentException("There is no reviews for this product"));

        return reviews;
    }

    @Override
    public Double getWeightedAverage(Product product) {

        List<Review> reviews = reviewRepository.findByProductId(product)
                            .orElseThrow(() -> new IllegalArgumentException("There is no reviews for this product"));

        double sum = 0;

        for (Review rev : reviews) {
            Double rate = rev.getRating();
            sum += rate;
        }

        return sum / reviews.size();
    }

    @Override
    public Boolean deleteReview(Long reviewId) {

        Optional<Review> rev = reviewRepository.findById(reviewId);

        if (rev.isEmpty()) {
            return null;
        }

        Review r = rev.get();
        reviewRepository.delete(r);

        return true;

    }

    @Override
    public List<Review> findPendingReview() {

        List<Review> r = reviewRepository.findPendingReviews()
                                    .orElseThrow(() -> new IllegalArgumentException("There is no pending reviews"));

        return r;
    }

    @Override
    public List<Review> findApprovedReview() {

        List<Review> r = reviewRepository.findApprovedReview()
                                    .orElseThrow(() -> new IllegalArgumentException("There is no approved reviews"));

        return r;
    }

    @Override
    public Review moderateReview(Long reviewID, String status)
            throws ResourceNotFoundException, IllegalArgumentException {

        Review reviewToUpdate = reviewRepository.findById(reviewID)
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        reviewToUpdate.setApprovalStatus(status);

        return reviewRepository.save(reviewToUpdate);
    }

    @Override
    public List<Review> findReviewsByUser(String user) {

        Optional<List<Review>> reviews = reviewRepository.findByUser(user);
        
        return reviews.get();
    }
}