package com.docService.dao;

import com.docService.entities.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsDao extends JpaRepository<Documents, Long> {
}
