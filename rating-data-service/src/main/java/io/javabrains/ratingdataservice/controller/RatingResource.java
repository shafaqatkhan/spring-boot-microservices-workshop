package io.javabrains.ratingdataservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingdataservice.models.Rating;
import io.javabrains.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUsersRating(@PathVariable("userId") String userId)
	{
		 List<Rating> ratings = Arrays.asList(
				new Rating("12345",4),
				new Rating ("5678",3)
				);
		 
		 UserRating userRatings = new UserRating();
		 userRatings.setUserRating(ratings);
		 return userRatings;
	}
}
