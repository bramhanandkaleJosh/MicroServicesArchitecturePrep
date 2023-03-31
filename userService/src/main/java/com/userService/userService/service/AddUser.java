package com.userService.userService.service;

import com.userService.userService.dto.AddUserRequest;
import com.userService.userService.dto.AddUserResponse;

public interface AddUser {

    AddUserResponse addUser(AddUserRequest addUserRequest);
}
