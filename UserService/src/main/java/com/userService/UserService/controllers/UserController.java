package com.userService.UserService.controllers;

import com.userService.UserService.dto.AdminDto;
import com.userService.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody AdminDto adminDto){
        return new ResponseEntity<>(userService.createUserService(adminDto), HttpStatus.OK);
    }

    @PostMapping(value = "/rollback")
    public ResponseEntity<?> rollbackUser(@RequestBody Map<String, String> input){
        return new ResponseEntity<>(userService.rollbackUserService(input), HttpStatus.OK);
    }
}
