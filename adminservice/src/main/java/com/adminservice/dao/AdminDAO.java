package com.adminservice.dao;


import com.adminservice.entity.AdminRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<AdminRecords, Long> {
}
