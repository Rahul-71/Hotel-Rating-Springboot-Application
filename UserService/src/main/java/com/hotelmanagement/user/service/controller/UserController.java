package com.hotelmanagement.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagement.user.service.entities.User;
import com.hotelmanagement.user.service.payload.ApiResponse;
import com.hotelmanagement.user.service.services.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // get all user
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUsers = this.userService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    // get user using userID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = this.userService.getUser(userId);

        return ResponseEntity.ok(user);
    }

    // delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        this.userService.deleteUser(userId);

        ApiResponse response = ApiResponse.builder()
                .message("User deleted Successfully!!")
                .status(HttpStatus.OK)
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }

    // update user details
    @PutMapping(path = "/update/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId) {
        // update user based on userId
        User updatedUser = this.userService.updateUser(user, userId);

        return ResponseEntity.ok(updatedUser);
    }
}
