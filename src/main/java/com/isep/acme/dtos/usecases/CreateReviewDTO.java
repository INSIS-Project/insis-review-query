package com.isep.acme.dtos.usecases;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateReviewDTO {

	private String reviewText;

	private String userName;

	private Double rating;

	public CreateReviewDTO() {
	}

	public CreateReviewDTO(String reviewText) {
		this.reviewText = reviewText;
	}

	public CreateReviewDTO(Double rating) {
		this.rating = rating;
	}

}
