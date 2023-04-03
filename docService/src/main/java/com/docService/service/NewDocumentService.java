package com.docService.service;

import com.docService.dto.AdminDto;
import com.docService.dto.ResponseVO;
import com.docService.entities.Documents;
import com.docService.dao.DocumentsDao;
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
    DocumentsDao documentsDao;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseVO saveDocumentService(AdminDto adminDto) {
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

