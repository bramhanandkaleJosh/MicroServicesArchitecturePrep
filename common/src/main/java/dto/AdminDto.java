package dto;


import jakarta.persistence.Basic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private String name;

    private String surname;

    private Instant lastMessageTime;
    
    private String document;
}
