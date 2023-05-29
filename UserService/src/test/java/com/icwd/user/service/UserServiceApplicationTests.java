package com.icwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.icwd.user.service.entities.Ratings;
import com.icwd.user.service.externalservice.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

//	@Test
//	void addRating() {
//		Ratings r = Ratings.builder().rating("109087").remarks("This is feign client test").hotelId("").userId("").build();
//		Ratings saverating = ratingService.addRating(r);
//		System.out.println("Rating created successfully");
//	}

}
