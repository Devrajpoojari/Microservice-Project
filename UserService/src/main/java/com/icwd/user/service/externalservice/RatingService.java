package com.icwd.user.service.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.icwd.user.service.entities.Ratings;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
	
	@PostMapping("/ratings/addRating")
	public Ratings addRating(Ratings ratings );

	
}
