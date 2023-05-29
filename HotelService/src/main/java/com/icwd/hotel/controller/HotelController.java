package com.icwd.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icwd.hotel.entities.Hotel;
import com.icwd.hotel.exception.ResourceNotFoundException;
import com.icwd.hotel.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@PostMapping("/add")
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}

	@GetMapping("/getHotels")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(hotelService.getListOfHotels());
	}

	@GetMapping("getHotel/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) throws ResourceNotFoundException {
		return ResponseEntity.ok(hotelService.getHotelById(id));
	}

}
