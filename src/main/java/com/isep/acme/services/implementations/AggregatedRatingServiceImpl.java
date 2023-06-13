package com.isep.acme.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.AggregatedRatingRepository;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.services.AggregatedRatingService;
import com.isep.acme.services.ProductService;
import com.isep.acme.services.ReviewService;

@Service
public class AggregatedRatingServiceImpl implements AggregatedRatingService {

	@Autowired
	AggregatedRatingRepository arRepository;

	@Autowired
	private ProductRepository pRepository;

	@Autowired
	ReviewService rService;

	@Autowired
	ProductService productService;

	@Override
	public AggregatedRating save(String sku) {

		Product product = pRepository.findBySku(sku)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + sku));

		Double average = rService.getWeightedAverage(product);

		AggregatedRating r = arRepository.findByProductId(product)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + sku));
		AggregatedRating aggregateF;

		if (r != null) {
			r.setAverage(average);
			aggregateF = arRepository.save(r);
		} else {
			AggregatedRating f = new AggregatedRating(average, product);
			aggregateF = arRepository.save(f);
		}
        
		return aggregateF;
	}
}
