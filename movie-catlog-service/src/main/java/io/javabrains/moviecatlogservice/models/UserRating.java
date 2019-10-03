package io.javabrains.moviecatlogservice.models;

import java.util.List;

public class UserRating {

	public UserRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<Rating> userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
}
