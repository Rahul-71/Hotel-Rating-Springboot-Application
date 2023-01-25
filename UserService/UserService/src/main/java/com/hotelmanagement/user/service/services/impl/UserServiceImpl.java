package com.hotelmanagement.user.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelmanagement.user.service.entities.User;
import com.hotelmanagement.user.service.exceptions.ResourceNotFoundExceptions;
import com.hotelmanagement.user.service.repositories.UserRepository;
import com.hotelmanagement.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        return this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptions("User with given id: " + userId + " not found!!"));
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
