package io.javabrains.moviecatlogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatlogservice.models.CataLogItem;
import io.javabrains.moviecatlogservice.models.Movie;
import io.javabrains.moviecatlogservice.models.Rating;
import io.javabrains.moviecatlogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	@GetMapping
	public List<CataLogItem> getCatalog(@PathVariable("userId") String userId) {
		
		String movieInfoUrl = "http://MOVIE-INFO-SERVICE/movies/";
		
		String movieRatingUrl = "http://MOVIE-RATING-SERVICE/ratingsdata/users/" + userId;
				
/*		Hardcoded*/
		/*List<Rating> ratings= Arrays.asList(
				new Rating("12345",4),
				new Rating ("5678",3)
				);*/
		
		UserRating ratings = restTemplate.getForObject(movieRatingUrl, UserRating.class);
		
		
		return ratings.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject(movieInfoUrl+rating.getMovieId(), Movie.class);
			
			//Movie movie= (Movie) webClientBuilder.build().get().uri(url+rating.getMovieId()).retrieve();
			
			return new CataLogItem(movie.getName(),"Sceince Fiction Movie",rating.getRating());
			
		}).collect(Collectors.toList());
		
		
/*		if(userId.equals("shafaqat")) {
			return Collections.singletonList(
					new CataLogItem("Inception","Sceince Fiction Movie",9)
					);

		} else {
			return Collections.singletonList(
					new CataLogItem("Transformers","Action Movie",5)
					);

		}*/
		
	}
}
