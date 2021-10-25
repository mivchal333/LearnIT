package pb.edu.pl.krysiukm.learnit.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadedMessage {
    private String message;
    private String filename;
    private String fileUrl;

    public FileUploadedMessage(String message) {
        this.message = message;
    }
}
