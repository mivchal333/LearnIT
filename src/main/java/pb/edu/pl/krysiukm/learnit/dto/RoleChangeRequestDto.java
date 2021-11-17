package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Data;
import pb.edu.pl.krysiukm.learnit.security.AppRole;

@Data
public class RoleChangeRequestDto {
    private boolean add;
    private String userEmail;
    private AppRole role;
}
