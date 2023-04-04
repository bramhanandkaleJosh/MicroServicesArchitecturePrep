package com.adminService.adminService.service;

import com.adminService.adminService.dto.AdminRequest;
import com.adminService.adminService.dto.AdminResponse;

public interface AdminService {
    AdminResponse submitUserAndDocs(AdminRequest adminRequest);

}
