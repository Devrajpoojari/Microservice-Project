package com.icwd.user.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icwd.user.service.entities.Hotel;
import com.icwd.user.service.entities.Ratings;
import com.icwd.user.service.entities.User;
import com.icwd.user.service.exceptions.ResourceNotFoundException;
import com.icwd.user.service.externalservice.HotelService;
import com.icwd.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RestTemplate restTemplate;

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User u) {
		String randm = UUID.randomUUID().toString();
		u.setUserId(randm);
		return userRepository.save(u);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public User getUserById(String id) throws ResourceNotFoundException {
		User u = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not present : " + id));
		Ratings[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + id, Ratings[].class);
		List<Ratings> listOfRating = Arrays.stream(ratingOfUser).toList();
//		u.setRatings(ratingOfUser);
		List<Ratings> ratingList = listOfRating.stream().map(rating -> {
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTELSERVICE/hotels/getHotel/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();

			// fiegn client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		u.setRatings(ratingList);
		return u;
	}
}
