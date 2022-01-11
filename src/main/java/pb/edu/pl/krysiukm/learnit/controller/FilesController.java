package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pb.edu.pl.krysiukm.learnit.dto.UploadedFile;
import pb.edu.pl.krysiukm.learnit.service.FileResolver;
import pb.edu.pl.krysiukm.learnit.service.FilesStorageService;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class FilesController {

    private final FilesStorageService storageService;
    private final FileResolver fileResolver;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String savedFileName = storageService.save(file);

            UploadedFile uploadedFile = fileResolver.resolveFile(savedFileName);
            return ResponseEntity.status(HttpStatus.OK).body(uploadedFile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}