package service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dao.UserDao;
import dto.AdminDto;
import dto.ResponseVO;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;



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
            if (ObjectUtils.isEmpty(adminDto)){
                User user = new User(adminDto.getName(), adminDto.getSurname(), Instant.now(Clock.system(ZoneId.systemDefault())));
                userDao.save(user);
                responseVO.setData(objectMapper.convertValue(user, JsonNode.class));
            }
        } catch (Exception e){
            log.info("Exception occurred in UserService :: createUserService()", e);
        }

        return responseVO;
    }

    public ResponseVO rollbackUserService(Map<String, String> input) {
        ResponseVO responseVO = new ResponseVO("Failed", 400, null ,null);
        try{
            userDao.deleteById(Long.parseLong(input.get("userId")));
            input.put("Status", "respective userId is rollback done due to transaction failure in doc service");
            responseVO.setData(objectMapper.convertValue(input, JsonNode.class));
        } catch (Exception e){
            log.info("Exception occurred in UserService :: rollbackUserService()", e);
        }
        return responseVO;
    }
}
