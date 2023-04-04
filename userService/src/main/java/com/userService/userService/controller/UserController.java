package com.userService.userService.controller;

import com.userService.userService.dto.UserRequest;
import com.userService.userService.dto.UserResponse;
import com.userService.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/user/rollback")
    public ResponseEntity<UserResponse> addUser(@RequestBody Long userId){
        return new ResponseEntity<>(userService.rollbackUserService(userId), HttpStatus.OK);
    }
}
