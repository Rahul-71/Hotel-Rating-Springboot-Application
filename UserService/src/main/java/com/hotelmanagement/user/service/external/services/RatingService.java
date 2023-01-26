package com.hotelmanagement.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelmanagement.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE/ratings")
public interface RatingService {

    // create rating
    @PostMapping()
    Rating createRating(@RequestBody Rating rating);

    // get all rating
    @GetMapping()
    List<Rating> getAllRatings();

    // get rating by user id
    @GetMapping(path = "/users/{userId}")
    List<Rating> getRatingByUserID(@PathVariable String userId);

    // get rating by hotel id
    @GetMapping(path = "/hotels/{hotelId}")
    List<Rating> getRatingByHotelID(@PathVariable String hotelId);

}
