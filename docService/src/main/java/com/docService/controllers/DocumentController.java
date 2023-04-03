package com.docService.controllers;

import com.docService.dto.AdminDto;
import com.docService.service.NewDocumentService;
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
    public ResponseEntity<?> saveUserDocument(@RequestBody AdminDto adminDto) {
        return new ResponseEntity<>(documentService.saveDocumentService(adminDto), HttpStatus.OK);
    }
}
