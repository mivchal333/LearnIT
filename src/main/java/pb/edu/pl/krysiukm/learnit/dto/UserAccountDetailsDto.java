package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccountDetailsDto {
    private String firstName;
    private String lastName;
    private String email;

}
