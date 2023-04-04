package com.docService.docService.repository;

import com.docService.docService.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Documents, Long> {
}
