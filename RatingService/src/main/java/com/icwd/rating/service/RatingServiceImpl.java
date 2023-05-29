package com.icwd.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icwd.rating.entities.Rating;
import com.icwd.rating.exceptions.ResourceNotFoundException;
import com.icwd.rating.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> getAllRating() {
		
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelid) throws ResourceNotFoundException {
		List<Rating> r = ratingRepository.findByHotelId(hotelid);
		if (r == null || r.isEmpty()) {
			throw new ResourceNotFoundException("Hotel not found with this id :" + hotelid);
		}
		return r;
	}

	@Override
	public Rating saveRating(Rating rating) {
		String id=UUID.randomUUID().toString();
		rating.setRatingId(id);
		return ratingRepository.save(rating);
	}

}
