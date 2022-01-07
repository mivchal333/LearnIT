package pb.edu.pl.krysiukm.learnit.service;

import org.junit.jupiter.api.Test;
import pb.edu.pl.krysiukm.learnit.dto.UploadedFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileResolverTest {

    FileResolver sut = new FileResolver();

    @Test
    void shouldLoadFileCatalogPrefix() {
        String filename = "file.jpg";

        UploadedFile file = sut.resolveFile(filename);

        assertEquals(filename, file.getFilename());
        assertTrue(file.getFileUrl().startsWith("/"));
    }
}