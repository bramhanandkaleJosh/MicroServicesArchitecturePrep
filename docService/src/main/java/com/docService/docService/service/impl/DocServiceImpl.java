package com.docService.docService.service.impl;

import com.docService.docService.dto.DocRequest;
import com.docService.docService.dto.DocResponse;
import com.docService.docService.entity.Documents;
import com.docService.docService.repository.DocumentRepository;
import com.docService.docService.service.DocService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class DocServiceImpl implements DocService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public DocResponse addDoc(DocRequest docRequest) {
        DocResponse addDocResponse = new DocResponse("Failed", 400, null ,null);
        try{
            if (Objects.nonNull(docRequest)){
                Documents doc = new Documents();
                Optional.ofNullable(docRequest.getUserId()).ifPresent(doc::setUserId);
                Optional.ofNullable(docRequest.getDocName()).ifPresent(doc::setDocName);
                Optional.ofNullable(docRequest.getDocNumber()).ifPresent(doc::setDocNumber);
                Optional.ofNullable(docRequest.getUserId()).ifPresent(doc::setUserId);
                documentRepository.save(doc);
                addDocResponse.setMessage("Document Added Successfully for user" + docRequest.getUserId());
                addDocResponse.setStatus("Success");
                addDocResponse.setStatusCode(200);
                addDocResponse.setData(objectMapper.convertValue(doc, JsonNode.class));
            }
        } catch (Exception e){
            log.info("Exception occurred while creating user: ", e);
            addDocResponse.setStatus(e.getMessage());
            addDocResponse.setMessage("Failed");
            addDocResponse.setStatusCode(400);
            addDocResponse.setData(null);
        }
        return addDocResponse;
    }
}

