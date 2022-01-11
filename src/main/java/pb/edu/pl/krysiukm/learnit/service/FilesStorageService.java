package pb.edu.pl.krysiukm.learnit.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    String save(MultipartFile file);

    void deleteFile(String filename);
}
