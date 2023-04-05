package com.docservice.controller;

import com.docservice.dto.AdminDTO;
import com.docservice.service.NewDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doc")
public class DocumentController {

    @Autowired
    NewDocumentService documentService;

    @PostMapping(value = "/saveDoc")
    public ResponseEntity<?> saveUserDocument(@RequestBody AdminDTO adminDto) {
        return new ResponseEntity<>(documentService.saveDocumentService(adminDto), HttpStatus.OK);
    }
}
