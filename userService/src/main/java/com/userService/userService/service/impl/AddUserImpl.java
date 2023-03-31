package com.userService.userService.service.impl;

import com.userService.userService.dto.AddUserRequest;
import com.userService.userService.dto.AddUserResponse;
import com.userService.userService.entity.User;
import com.userService.userService.repository.UserRepository;
import com.userService.userService.service.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserImpl implements AddUser {
    @Autowired
    UserRepository userRepository;



    public AddUserResponse addUser(AddUserRequest addUserRequest){
        User user = new User();
        user.setEmail(addUserRequest.getEmail());
        user.setDocName(addUserRequest.getDocName());
        user.setDocId(addUserRequest.getDocId());
        user.setLastName(addUserRequest.getLastName());
        user.setFirstName(addUserRequest.getFirstName());
        User saved = userRepository.save(user);
        AddUserResponse addUserResponse = new AddUserResponse();
        //String s = String.valueOf()
        addUserResponse.setMessage("Successfully added" + saved.getId());
        addUserResponse.setStatusCode("200");
        addUserResponse.setId(saved.getId());
        return addUserResponse;
    }

}
