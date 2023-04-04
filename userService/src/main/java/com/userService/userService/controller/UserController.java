package com.userService.userService.controller;

import com.userService.userService.dto.AddUserRequest;
import com.userService.userService.dto.AddUserResponse;
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
    public ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest addUserRequest){
        return new ResponseEntity<>(userService.addUser(addUserRequest), HttpStatus.OK);
    }
}
