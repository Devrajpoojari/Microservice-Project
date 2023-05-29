package com.icwd.rating.service;

import java.util.List;

import com.icwd.rating.entities.Rating;
import com.icwd.rating.exceptions.ResourceNotFoundException;

public interface RatingService {
	
	List<Rating> getAllRating();
	
	List<Rating> getByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelid) throws ResourceNotFoundException;
	
	Rating saveRating(Rating rating);

}
