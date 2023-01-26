package com.hotelmanagement.rating.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotelmanagement.rating.service.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

    // find rating using userId
    List<Rating> findByUserId(String userId);

    // find rating using hotelId
    List<Rating> findByHotelId(String hotelId);
}
