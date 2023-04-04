package com.docService.docService.service;

import com.docService.docService.dto.DocRequest;
import com.docService.docService.dto.DocResponse;

public interface DocService {
    DocResponse addDoc(DocRequest docRequest);
}
