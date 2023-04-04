package com.adminService.adminService.service.impl;

import com.adminService.adminService.dto.AdminRequest;
import com.adminService.adminService.dto.AdminResponse;
import com.adminService.adminService.entity.Admin;
import com.adminService.adminService.repository.AdminRepo;
import com.adminService.adminService.service.AdminService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public AdminResponse submitUserAndDocs(AdminRequest adminRequest) {
        RestTemplate restTemplate = new RestTemplate();
        AdminResponse adminResponse = new AdminResponse("Failed", 400 , null, null);
        String userServiceUrl = "http://localhost:8081/user/add";
        String docServiceUrl = "http://localhost:8082/doc/add";
            try {
            HttpEntity request = new HttpEntity<>(adminRequest);
            ResponseEntity<AdminResponse> userResponse = restTemplate.postForEntity(userServiceUrl, request, AdminResponse.class);
            AdminResponse user = userResponse.getBody();

            adminRequest.setUserId(Long.parseLong(user.getData().get("id").toString()));

            ResponseEntity<AdminResponse> docResponse = restTemplate.postForEntity(docServiceUrl, request, AdminResponse.class);
            AdminResponse doc = docResponse.getBody();

            if (docResponse.getBody().getStatusCode().equals(200)){
                Admin admin = new Admin();
                admin.setUserId(Long.parseLong(user.getData().get("id").toString()));
                admin.setDocId(Long.parseLong(doc.getData().get("id").toString()));
                adminRepo.save(admin);
                adminResponse.setStatusCode(200);
                adminResponse.setStatus("Success");
                adminResponse.setData(objectMapper.convertValue(admin, JsonNode.class));
            } else {
                throw new RuntimeException("Document service failed to save document");
            }

        } catch (Exception e) {
            log.error("Exception occurred in AdminService :: createUserAndSubmitDocs()", e);
//            Map<String, Long> userData = new HashMap<>();
//            userData.put("userId", userId);
//            HttpEntity rollbackRequest = new HttpEntity(userData);
//            restTemplate.postForEntity(userServiceUrl+"/user/rollback", rollbackRequest, ResponseVO.class);
            adminResponse.setMessage("docService is failed, so user transaction is rollbacked for userId : ");
        }
        return adminResponse;
    }

}