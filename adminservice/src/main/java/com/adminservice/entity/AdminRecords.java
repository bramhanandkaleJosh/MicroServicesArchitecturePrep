package com.adminservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;


@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AdminRecords {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long adminId;

        @NonNull
        @Basic
        private Long userId;

        @NonNull
        @Basic
        private Long documentId;

        @NonNull
        @Basic
        private Boolean isDocumentSubmitted;

        @NonNull
        @Basic
        private Instant createdTime;

}
