package com.hotelmanagement.rating.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.rating.service.entities.Rating;
import com.hotelmanagement.rating.service.repositories.RatingRepository;
import com.hotelmanagement.rating.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        List<Rating> ratings = this.ratingRepository.findAll();
        for (Rating rating : ratings) {
            System.out.println(rating.getRatingId());
            System.out.println(rating.getFeedback());
            System.out.println("---------------");
        }
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }

}
