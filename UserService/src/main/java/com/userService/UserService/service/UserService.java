package com.userService.UserService.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userService.UserService.dao.UserDao;
import com.userService.UserService.dto.AdminDto;
import com.userService.UserService.dto.ResponseVO;
import com.userService.UserService.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseVO createUserService(AdminDto adminDto){
        ResponseVO responseVO = new ResponseVO("Failed", 400, null ,null);
        try{
            if (Objects.nonNull(adminDto)){
                User user = new User(adminDto.getName(), adminDto.getSurname(), Instant.now(Clock.system(ZoneId.systemDefault())));
                userDao.save(user);
                responseVO.setData(objectMapper.convertValue(user, JsonNode.class));
            }
        } catch (Exception e){
            log.info("Exception occurred in UserService :: createUserService()", e);
            responseVO.setStatus(e.getMessage());
        }

        return responseVO;
    }

    public ResponseVO rollbackUserService(Map<String, String> input) {
        ResponseVO responseVO = new ResponseVO("Failed", 400, null ,null);
        try{
            User user = userDao.getReferenceById(Long.parseLong(input.get("userId")));
            if (Objects.nonNull(user.getUserId())){
                userDao.delete(user);
                input.put("Status", "respective userId is rollback done due to transaction failure in doc service");
                responseVO.setData(objectMapper.convertValue(input, JsonNode.class));
                responseVO.setStatus("Success");
                responseVO.setStatusCode(200);
            } else {
                responseVO.setMessage("Respective record not found");
            }
        } catch (Exception e){
            log.info("Exception occurred in UserService :: rollbackUserService()", e);
            responseVO.setMessage(e.getMessage());
        }
        return responseVO;
    }
}
