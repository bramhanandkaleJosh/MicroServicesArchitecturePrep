package com.adminService.adminService.controller;

import com.adminService.adminService.dto.AdminRequest;
import com.adminService.adminService.dto.AdminResponse;
import com.adminService.adminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin/add")
    public ResponseEntity<AdminResponse> submitUserAndDocs(@RequestBody AdminRequest adminRequest){
        return new ResponseEntity<>(adminService.submitUserAndDocs(adminRequest), HttpStatus.OK);
    }

}
