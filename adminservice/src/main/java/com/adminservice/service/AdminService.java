package com.adminservice.service;


import com.adminservice.dao.AdminDAO;
import com.adminservice.dto.AdminDTO;
import com.adminservice.dto.ResponseVO;
import com.adminservice.entity.AdminRecords;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;


@Transactional
@Service
@Slf4j
public class AdminService {

    @Autowired
    AdminDAO adminDao;

    @Autowired
    ObjectMapper objectMapper;

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseVO createUserAndSubmitDocs(AdminDTO adminDto){
        ResponseVO responseVO = new ResponseVO("Failed", 400 , null, null);
        String userServiceUrl = "http://localhost:8082";
        String docServiceUrl = "http://localhost:8081";
        Long userId = 0l;
        try {
            HttpEntity request = new HttpEntity<>(adminDto);
            ResponseEntity<ResponseVO> userResponse = restTemplate.postForEntity(userServiceUrl+"/user/create", request, ResponseVO.class);
            ResponseVO userResponseVO = userResponse.getBody();

            adminDto.setUserId(Long.parseLong(userResponseVO.getData().get("userId").toString()));
            userId= adminDto.getUserId();
            ResponseEntity<ResponseVO> docResponse = restTemplate.postForEntity(docServiceUrl+"/doc/saveDoc", request, ResponseVO.class);
            ResponseVO docResponseVO = docResponse.getBody();

            if (docResponse.getBody().getStatusCode().equals(200)){
                AdminRecords adminRecords = new AdminRecords( Long.parseLong(userResponseVO.getData().get("userId").toString()),
                        Long.parseLong(docResponseVO.getData().get("documentId").toString()),
                        true, Instant.now(Clock.system(ZoneId.systemDefault()))
                );
                adminDao.save(adminRecords);
                responseVO.setStatusCode(200);
                responseVO.setStatus("Success");
                responseVO.setData(objectMapper.convertValue(adminRecords, JsonNode.class));
            } else {
                throw new RuntimeException("Document service failed to save document");
            }

        } catch (Exception e) {
            log.error("Exception occurred in AdminService :: createUserAndSubmitDocs()", e);
            Map<String, Long> userData = new HashMap<>();
            userData.put("userId", userId);
            HttpEntity rollbackRequest = new HttpEntity(userData);
            restTemplate.postForEntity(userServiceUrl+"/user/rollback", rollbackRequest, ResponseVO.class);
            responseVO.setMessage("docService is failed, so user transaction is rollbacked for userId : "+userId);
        }
        return responseVO;
    }



}
