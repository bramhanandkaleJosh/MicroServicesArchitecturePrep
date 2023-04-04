package com.userService.userService.service;

import com.userService.userService.dto.AddUserRequest;
import com.userService.userService.dto.AddUserResponse;

public interface UserService {

    AddUserResponse addUser(AddUserRequest addUserRequest);
}
