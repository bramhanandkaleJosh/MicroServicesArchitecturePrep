package com.userService.userService.service;

import com.userService.userService.dto.UserRequest;
import com.userService.userService.dto.UserResponse;

import java.util.Map;

public interface UserService {

    UserResponse addUser(UserRequest userRequest);
    public UserResponse rollbackUserService(Long userId);
}
