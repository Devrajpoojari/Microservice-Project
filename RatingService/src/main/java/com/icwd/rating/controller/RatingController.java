package com.icwd.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icwd.rating.entities.Rating;
import com.icwd.rating.exceptions.ResourceNotFoundException;
import com.icwd.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRating());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRatingById(@PathVariable String id) {
		return ResponseEntity.ok(ratingService.getByUserId(id));
	}

	@PostMapping("/addRating")
	public ResponseEntity<?> addRating(@RequestBody Rating r) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(r));
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getRatingByUserId(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getByUserId(id));
	}

	@GetMapping("/hotel/{id}")
	public ResponseEntity<?> getRatingByHotelId(@PathVariable String id) throws ResourceNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(id));
	}

}
