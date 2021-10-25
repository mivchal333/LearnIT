package pb.edu.pl.krysiukm.learnit.service;

import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.dto.UploadedFile;

@Service
public class FileResolver {

    public UploadedFile resolveFile(String filename) {
        String fileUrl = "/files/" + filename;
        return new UploadedFile(filename, fileUrl);
    }
}
