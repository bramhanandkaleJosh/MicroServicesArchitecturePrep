package com.adminService.adminService.dao;


import com.adminService.adminService.entities.AdminRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<AdminRecords, Long> {
}
