package pb.edu.pl.krysiukm.learnit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadedFile {
    private String filename;
    private String fileUrl;
}
