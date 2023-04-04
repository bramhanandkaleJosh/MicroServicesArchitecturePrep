package com.userService.userService.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userService.userService.dto.AddUserRequest;
import com.userService.userService.dto.AddUserResponse;
import com.userService.userService.entity.User;
import com.userService.userService.repository.UserRepository;
import com.userService.userService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    public AddUserResponse addUser(AddUserRequest addUserRequest){

        AddUserResponse addUserResponse = new AddUserResponse("Failed", 400, null ,null);
        try{
            if (Objects.nonNull(addUserRequest)){
                User user = new User();
                //Optional.ofNullable(addUserRequest.getFirstName()).ifPresent(user::setFirstName);
                user.setFirstName(addUserRequest.getFirstName());
                user.setLastName(addUserRequest.getLastName());
                user.setEmail(addUserRequest.getEmail());
                user.setContactNumber(addUserRequest.getContactNumber());
                userRepository.save(user);
                addUserResponse.setMessage("User Added Successfully");
                addUserResponse.setStatus("Success");
                addUserResponse.setStatusCode(200);
                addUserResponse.setData(objectMapper.convertValue(user, JsonNode.class));
            }
        } catch (Exception e){
            log.info("Exception occurred while creating user: ", e);
            addUserResponse.setStatus(e.getMessage());
            addUserResponse.setMessage("Failed");
            addUserResponse.setStatusCode(400);
            addUserResponse.setData(null);
        }
        return addUserResponse;
    }

}
