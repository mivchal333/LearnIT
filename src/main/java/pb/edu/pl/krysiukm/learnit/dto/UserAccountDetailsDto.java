package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAccountDetailsDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long points;
    private List<String> roles;
}
