package com.hotelmanagement.user.service.services.impl;

import java.util.Arrays;
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
import com.hotelmanagement.user.service.repositories.UserRepository;
import com.hotelmanagement.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
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
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // to fetch user based in userId
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptions("User with given id: " + userId + " not found!!"));

        // fetch rating of the above user from RATING-SERVICE
        // http://localhost:8083/ratings/users/d4af178c-5438-4670-bf42-f6ff17e90c23
        Rating[] ratingArray = this.restTemplate.getForObject(
                "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                Rating[].class);

        List<Rating> userRatings = Arrays.stream(ratingArray).toList();

        List<Rating> ratings = userRatings.stream().map(rating -> {
            // api call to hotel service
            // http://localhost:8082/hotels/ae25f673-b728-4c5e-961b-f32750540b12
            Hotel hotel = this.restTemplate
                    .getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);

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
