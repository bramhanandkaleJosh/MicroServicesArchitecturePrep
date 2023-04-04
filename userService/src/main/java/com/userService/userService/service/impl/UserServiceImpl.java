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

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;


    public AddUserResponse addUser(AddUserRequest addUserRequest){
//        User user = new User();
//        user.setEmail(addUserRequest.getEmail());
//        user.setDocName(addUserRequest.getDocName());
//        user.setDocId(addUserRequest.getDocId());
//        user.setLastName(addUserRequest.getLastName());
//        user.setFirstName(addUserRequest.getFirstName());
//        User saved = userRepository.save(user);
//        AddUserResponse addUserResponse = new AddUserResponse();
//        //String s = String.valueOf()
//        addUserResponse.setMessage("Successfully added" + saved.getId());
//        addUserResponse.setStatusCode("200");
//        addUserResponse.setId(saved.getId());
//        return addUserResponse;

        AddUserResponse addUserResponse = new AddUserResponse("Failed", 400, null ,null);
        try{
            if (Objects.nonNull(addUserRequest)){
                //User user = new User(addUserRequest.getFirstName(), addUserRequest.getLastName(), addUserRequest.getEmail() ,addUserRequest.getContactNumber());
                User user = new User();
                user.setFirstName(addUserRequest.getFirstName());
                user.setLastName(addUserRequest.getLastName());
                user.setEmail(addUserRequest.getEmail());
                user.setContactNumber(addUserRequest.getContactNumber());
                userRepository.save(user);
                addUserResponse.setData(objectMapper.convertValue(user, JsonNode.class));
            }
        } catch (Exception e){
            log.info("Exception occurred in UserService :: createUserService()", e);
            addUserResponse.setStatus(e.getMessage());
        }

        return addUserResponse;

    }

}
