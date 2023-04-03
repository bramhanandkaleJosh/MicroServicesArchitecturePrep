package com.docService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Documents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentId;

    @NonNull
    @Basic
    private String name;

    @NonNull
    @Basic
    private String documentType;

    @NonNull
    @Basic
    private Long userId;

    @NonNull
    @Basic
    private Instant createdTime;
}
