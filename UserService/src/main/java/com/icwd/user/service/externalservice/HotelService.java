package com.icwd.user.service.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.icwd.user.service.entities.Hotel;

@Component
@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

	@GetMapping("/hotels/getHotel/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId);

}
