package controllers;

import dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody AdminDto adminDto){
        return new ResponseEntity<>(userService.createUserService(adminDto), HttpStatus.OK);
    }

    @PostMapping(value = "/rollback")
    public ResponseEntity<?> rollbackUser(@RequestBody Map<String, Long> input){
        return new ResponseEntity<>(userService.rollbackUserService(input.get("userId")), HttpStatus.OK);
    }
}
