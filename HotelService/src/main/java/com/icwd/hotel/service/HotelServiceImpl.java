package com.icwd.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icwd.hotel.entities.Hotel;
import com.icwd.hotel.exception.ResourceNotFoundException;
import com.icwd.hotel.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	@Override
	public Hotel create(Hotel h) {
		String uuid=UUID.randomUUID().toString();
		h.setId(uuid);
		return hotelRepository.save(h);
	}

	@Override
	public List<Hotel> getListOfHotels() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String id) throws ResourceNotFoundException {
		Hotel h=hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found in Db"+id));
		return h;
	}

}
