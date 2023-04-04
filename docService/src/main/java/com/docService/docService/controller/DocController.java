package com.docService.docService.controller;

import com.docService.docService.dto.DocRequest;
import com.docService.docService.dto.DocResponse;
import com.docService.docService.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocController {

    @Autowired
    DocService docService;

    @PostMapping("/doc/add")
    public ResponseEntity<DocResponse> addDocuments(@RequestBody DocRequest docRequest){
        return new ResponseEntity<>(docService.addDoc(docRequest), HttpStatus.OK);
    }
}
