package com.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @NonNull
    @Basic
    private String name;

    @NonNull
    @Basic
    private String surname;

    @NonNull
    @Basic
    private Instant createdTime;
}
