package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentId;

    @Basic
    private String name;

    @Basic
    private String documentType;

    @Basic
    private Long userId;

    @Basic
    private Instant createdTime;
}
