package com.icwd.hotel.service;

import java.util.List;

import com.icwd.hotel.entities.Hotel;
import com.icwd.hotel.exception.ResourceNotFoundException;

public interface HotelService {
	
	Hotel create(Hotel h);
	
	List<Hotel> getListOfHotels();
	
	Hotel getHotelById(String id) throws ResourceNotFoundException;

}
