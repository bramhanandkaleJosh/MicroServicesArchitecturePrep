package com.docservice.service;

import com.docservice.dto.AdminDTO;
import com.docservice.dto.ResponseVO;
import com.docservice.entity.Documents;
import com.docservice.dao.DocumentsDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Objects;


@Service
@Slf4j
public class NewDocumentService {

    @Autowired
    DocumentsDAO documentsDao;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseVO saveDocumentService(AdminDTO adminDto) {
        ResponseVO responseVO = new ResponseVO("Failed", 400, null, null);
        try {
            if (Objects.nonNull(adminDto)) {
                Documents document = new Documents(adminDto.getDocument(), adminDto.getDocumentType(), adminDto.getUserId(), Instant.now());
                documentsDao.save(document);
                responseVO.setStatus("Success");
                responseVO.setData(objectMapper.convertValue(document, JsonNode.class));
                responseVO.setStatusCode(200);
            }
        } catch (Exception e) {
            log.error("Exception occurred in DocumentService :: saveDocumentService()", e);
            responseVO.setMessage(e.getMessage());
        }
        return responseVO;
    }
}

