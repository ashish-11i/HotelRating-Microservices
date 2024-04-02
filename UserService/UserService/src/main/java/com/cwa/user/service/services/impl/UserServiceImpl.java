package com.cwa.user.service.services.impl;

import java.util.ArrayList;
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

import com.cwa.user.service.entities.Hotel;
import com.cwa.user.service.entities.Rating;
import com.cwa.user.service.entities.User;
import com.cwa.user.service.exceptions.ResourceNotFoundException;
import com.cwa.user.service.external.services.HotelService;
import com.cwa.user.service.respositories.UserRepository;
import com.cwa.user.service.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on servere !!: "+userId));
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{} ",ratingOfUser);
		
		List<Rating> asList = Arrays.asList(ratingOfUser);
		
		List<Rating> ratings = asList.stream().map((rating)->{
			//api cal to hotel service to get the hotel
//			Hotel hotel = restTemplate.getForObject("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//set the hotel to rating
			//return the rating
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRating(ratings);
		
		
		return user;
	}

}
