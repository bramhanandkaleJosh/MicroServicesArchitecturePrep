package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private String name;

    private String surname;

    private Instant lastMessageTime;
    
    private String document;
}
