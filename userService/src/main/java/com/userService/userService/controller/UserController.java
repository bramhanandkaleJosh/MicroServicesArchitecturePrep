package com.userService.userService.controller;

import com.userService.userService.dto.AddUserRequest;
import com.userService.userService.dto.AddUserResponse;
import com.userService.userService.service.AddUser;
import com.userService.userService.service.impl.AddUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    AddUser a;

    @PostMapping("/user/add")
    public AddUserResponse addUser(@RequestBody AddUserRequest addUserRequest){
        return a.addUser(addUserRequest);
    }
}
