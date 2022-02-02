package pb.edu.pl.krysiukm.learnit.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@Primary
@Profile("dev")
public class ServerFilesStorageService implements FilesStorageService {
    private final Path root = Paths.get("uploads");

    public ServerFilesStorageService() {
            try {
                Files.createDirectory(root);
            } catch (Exception e) {
                throw new RuntimeException("Could not initialize folder for upload!");
            }
    }

    @Override
    public String save(MultipartFile file) {
        String randomId = UUID.randomUUID().toString();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        String filename = randomId + "." + extension;
        try {
            Files.copy(file.getInputStream(), this.root.resolve(filename));
            return filename;
        } catch (Exception e) {
            log.error("[ServerFilesStorageService] File save error", e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Files.delete(this.root.resolve(filename));
        } catch (IOException e) {
            log.error("[ServerFilesStorageService] Unable to remove file: {}", filename, e);
        }
    }
}
