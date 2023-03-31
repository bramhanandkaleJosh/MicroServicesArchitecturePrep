package service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.AdminDao;
import dto.AdminDto;
import dto.ResponseVO;
import entities.AdminRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AdminService {

    @Autowired
    AdminDao adminDao;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseVO createUserAndSubmitDocs(AdminDto adminDto){
        ResponseVO responseVO = new ResponseVO("Failed", 400 , null, null);
        try {
            HttpEntity request = new HttpEntity<>(adminDto);
            ResponseEntity<ResponseVO> userResponse = restTemplate.postForEntity("http://localhost:8080/user/createUser", request, ResponseVO.class);
            ResponseVO userResponseVO = userResponse.getBody();

            adminDto.setUserId(Long.parseLong(userResponseVO.getData().get("userId").toString()));
            ResponseEntity<ResponseVO> docResponse = restTemplate.postForEntity("http://localhost:8080/doc/saveDoc", request, ResponseVO.class);
            ResponseVO docResponseVO = docResponse.getBody();

            AdminRecords adminRecords = new AdminRecords( Long.parseLong(userResponseVO.getData().get("userId").toString()),
                                                          Long.parseLong(docResponseVO.getData().get("documentId").toString()),
                                                     false, Instant.now(Clock.system(ZoneId.systemDefault()))
                                                      );

            if (docResponse.getBody().getStatusCode().equals(200)){
                adminRecords.setIsDocumentSubmitted(true);
                responseVO.setStatusCode(200);
                responseVO.setStatus("Success");
                responseVO.setData(objectMapper.convertValue(adminRecords, JsonNode.class));
            } else {
                Map<String, Long> userData = new HashMap<>();
                userData.put("userId", adminDto.getUserId());
                HttpEntity rollbackRequest = new HttpEntity(userData);
                ResponseEntity<ResponseVO> userRollbackResponse = restTemplate.postForEntity("http://localhost:8080/user/rollback", rollbackRequest, ResponseVO.class);
                responseVO.setMessage("docService is failed, so user transaction is rollbacked");
            }
            adminDao.save(adminRecords);

        }catch (Exception e){
            log.error("Exception occurred in AdminService :: createUserAndSubmitDocs() ", e);
        }
        return responseVO;
    }



}
