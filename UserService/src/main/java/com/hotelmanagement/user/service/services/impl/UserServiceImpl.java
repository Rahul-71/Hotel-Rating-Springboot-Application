package com.hotelmanagement.user.service.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelmanagement.user.service.entities.Hotel;
import com.hotelmanagement.user.service.entities.Rating;
import com.hotelmanagement.user.service.entities.User;
import com.hotelmanagement.user.service.exceptions.ResourceNotFoundExceptions;
import com.hotelmanagement.user.service.external.services.HotelService;
import com.hotelmanagement.user.service.external.services.RatingService;
import com.hotelmanagement.user.service.repositories.UserRepository;
import com.hotelmanagement.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    // @Autowired
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // generate a random userId
        String randomUserId = UUID.randomUUID().toString();

        user.setUserId(randomUserId);

        // to save user parameter to db
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // to get all the user present into db
        List<User> allUsers = this.userRepository.findAll();

        allUsers = allUsers.stream().map(user -> {
            // fetch rating details from RATING-SERVICE
            List<Rating> ratingByUserID = this.ratingService.getRatingByUserID(user.getUserId());
            
            // fetch hotel info for each rating
            ratingByUserID = ratingByUserID.stream().map(rating -> {
            
                // get hotel info using HOTEL-SERVICE
                Hotel hotel = this.hotelService.getHotelById(rating.getHotelId());
                rating.setHotel(hotel);

                return rating;
            }).collect(Collectors.toList());

            // setting rating list to user
            user.setRatings(ratingByUserID);

            return user;
        }).collect(Collectors.toList());

        return allUsers;
    }

    @Override
    public User getUser(String userId) {
        // to fetch user based in userId
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptions("User with given id: " + userId + " not found!!"));

        // // fetch rating of the above user from RATING-SERVICE
        // // http://localhost:8083/ratings/users/d4af178c-5438-4670-bf42-f6ff17e90c23
        // Rating[] ratingArray = this.restTemplate.getForObject(
        // "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
        // Rating[].class);

        List<Rating> userRatings = this.ratingService.getRatingByUserID(userId);

        // List<Rating> userRatings = Arrays.stream(ratingArray).toList();

        List<Rating> ratings = userRatings.stream().map(rating -> {
            // api call to hotel service
            Hotel hotel = this.hotelService.getHotelById(rating.getHotelId());
            // set hotel to rating
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratings);
        logger.info("{} ", ratings);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        // deleting the user from the db using userid
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptions("User with given id: " + userId + " not found!!"));

        this.userRepository.delete(user);
    }

    @Override
    public User updateUser(User user, String userId) {
        // update the user with the given data
        User userDb = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptions("User with given id: " + userId + " not found!!"));

        userDb.setName(user.getName());
        userDb.setAbout(user.getAbout());
        userDb.setEmail(user.getEmail());

        return this.userRepository.save(userDb);
    }

}
