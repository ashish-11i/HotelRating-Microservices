package com.cwa.rating.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwa.rating.entities.Rating;
import com.cwa.rating.repositories.RatingRepository;
import com.cwa.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository repository;

	@Override
	public Rating create(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRating() {
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return repository.findByUserId(userId);
		
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return repository.findByHotelId(hotelId);
	}
}
