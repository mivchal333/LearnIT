package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Technology {
    private Long id;
    private String name;
    private String description;
    private UploadedFile image;
}
