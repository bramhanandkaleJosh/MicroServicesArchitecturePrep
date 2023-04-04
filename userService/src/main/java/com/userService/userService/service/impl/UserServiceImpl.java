package com.userService.userService.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userService.userService.dto.UserRequest;
import com.userService.userService.dto.UserResponse;
import com.userService.userService.entity.User;
import com.userService.userService.repository.UserRepository;
import com.userService.userService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    public UserResponse addUser(UserRequest userRequest){

        UserResponse userResponse = new UserResponse("Failed", 400, null ,null);
        try{
            if (Objects.nonNull(userRequest)){
                User user = new User();
                //Optional.ofNullable(addUserRequest.getFirstName()).ifPresent(user::setFirstName);
                user.setFirstName(userRequest.getFirstName());
                user.setLastName(userRequest.getLastName());
                user.setEmail(userRequest.getEmail());
                user.setContactNumber(userRequest.getContactNumber());
                userRepository.save(user);
                userResponse.setMessage("User Added Successfully");
                userResponse.setStatus("Success");
                userResponse.setStatusCode(200);
                userResponse.setData(objectMapper.convertValue(user, JsonNode.class));
            }
        } catch (Exception e){
            log.info("Exception occurred while creating user: ", e);
            userResponse.setStatus(e.getMessage());
            userResponse.setMessage("Failed");
            userResponse.setStatusCode(400);
            userResponse.setData(null);
        }
        return userResponse;
    }

    public UserResponse rollbackUserService(Long userId) {
        UserResponse userResponse = new UserResponse("Failed", 400, null ,null);
        try{
            User user = userRepository.getReferenceById(userId);
            if (Objects.nonNull(user.getId())){
                userRepository.delete(user);
                userResponse.setData(objectMapper.convertValue("User Rolledback", JsonNode.class));
                userResponse.setStatus("Success");
                userResponse.setStatusCode(200);
            } else {
                userResponse.setMessage("Record not found");
            }
        } catch (Exception e){
            log.info("Exception occurred in UserService :: rollbackUserService()", e);
            userResponse.setMessage(e.getMessage());
        }
        return userResponse;
    }

}
