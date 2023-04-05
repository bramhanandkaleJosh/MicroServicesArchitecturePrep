package com.docservice.dao;

import com.docservice.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsDAO extends JpaRepository<Documents, Long> {
}
