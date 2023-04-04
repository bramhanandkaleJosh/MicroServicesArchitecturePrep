package com.userService.userService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    @Basic
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    @Basic
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    @Basic
    private String email;

    @Column(name  = "contact_number",nullable = false, unique = true, length = 20)
    @Basic
    private long contactNumber;

}
