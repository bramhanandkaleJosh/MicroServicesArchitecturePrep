package com.adminservice.controller;

import com.adminservice.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adminservice.service.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/accountOpen")
    public ResponseEntity<?> createUserAndSubmitDocs(@RequestBody AdminDTO adminDto){
        return new ResponseEntity<>(adminService.createUserAndSubmitDocs(adminDto) , HttpStatus.OK);
    }
}
