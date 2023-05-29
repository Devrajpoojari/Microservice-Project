package com.icwd.user.service.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icwd.user.service.entities.User;
import com.icwd.user.service.exceptions.ResourceNotFoundException;
import com.icwd.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User u) {
		User u1 = service.saveUser(u);

		return ResponseEntity.status(HttpStatus.CREATED).body(u1);
	}

	@GetMapping("/{id}")
//	@Retry(name="abcd",fallbackMethod = "ratingFallBackMethod")
//	@CircuitBreaker(name ="ratingHotelBreaker", fallbackMethod ="ratingFallBackMethod")
	@RateLimiter(name="ratelimiter",fallbackMethod = "ratingFallBackMethod")
	public ResponseEntity<User> getUser(@PathVariable String id) throws ResourceNotFoundException {
		return ResponseEntity.ok(service.getUserById(id));
	}

	public ResponseEntity<User> ratingFallBackMethod(Exception ex) {
		logger.info("Fall Back is executed becouse service is down😲😲😲.....", ex.getMessage());
		User u = User.builder().email("Devrajpoojari@gmail.com").name("Devraj").about("I'm a Developer from capgemini")
				.ratings(Arrays.asList()).userId("345").build();
		return new ResponseEntity<>(u,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(service.getAllUser());
	}
}
