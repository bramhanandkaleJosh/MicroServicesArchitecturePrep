package com.userService.UserService.dao;

import com.userService.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
