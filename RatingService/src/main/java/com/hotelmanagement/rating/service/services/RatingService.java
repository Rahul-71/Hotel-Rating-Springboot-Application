package com.hotelmanagement.rating.service.services;

import java.util.List;

import com.hotelmanagement.rating.service.entities.Rating;

public interface RatingService {

    // create
    Rating createRating(Rating rating);

    // get all rating
    List<Rating> getAllRating();

    // get all by userId
    List<Rating> getAllRatingByUserId(String userId);

    // get all by hotelId
    List<Rating> getAllRatingByHotelId(String hotelId);
}
