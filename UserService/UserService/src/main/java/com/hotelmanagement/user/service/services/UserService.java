package com.hotelmanagement.user.service.services;

import java.util.List;

import com.hotelmanagement.user.service.entities.User;

public interface UserService {

    // user operations

    // create
    User saveUser(User user);

    // get all user
    List<User> getAllUsers();

    // get user using userID
    User getUser(String userId);

    // delete user
    void deleteUser(String userId);

    // update user details
    User updateUser(User user, String userId);

}
