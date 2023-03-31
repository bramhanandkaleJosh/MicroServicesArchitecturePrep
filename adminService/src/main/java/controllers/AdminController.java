package controllers;

import dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/accountOpen")
    public ResponseEntity<?> createUserAndSubmitDocs(@RequestBody AdminDto adminDto){
        return new ResponseEntity<>(adminService.createUserAndSubmitDocs(adminDto) , HttpStatus.OK);
    }
}
