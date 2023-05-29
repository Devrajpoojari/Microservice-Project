package com.icwd.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Hotel {

	private String id;
	private String name;
	private String location;
	private String about;

}
