package com.hotelmanagement.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagement.rating.service.entities.Rating;
import com.hotelmanagement.rating.service.services.RatingService;

@RestController
@RequestMapping(path = "/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // create rating
    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.ratingService.createRating(rating));
    }

    // get all rating
    @GetMapping()
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(this.ratingService.getAllRating());
    }

    // get rating by user id
    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserID(@PathVariable String userId) {
        return ResponseEntity.ok(this.ratingService.getAllRatingByUserId(userId));
    }

    // get rating by hotel id
    @GetMapping(path = "/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelID(@PathVariable String hotelId) {
        return ResponseEntity.ok(this.ratingService.getAllRatingByHotelId(hotelId));
    }
}
