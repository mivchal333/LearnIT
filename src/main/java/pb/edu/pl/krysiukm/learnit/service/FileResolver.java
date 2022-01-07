package pb.edu.pl.krysiukm.learnit.service;

import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.dto.UploadedFile;

@Service
public class FileResolver {
    private final String FILE_DIRECTORY_PREFIX = "/files/";

    public UploadedFile resolveFile(String filename) {
        String fileUrl = FILE_DIRECTORY_PREFIX + filename;
        return new UploadedFile(filename, fileUrl);
    }
}
