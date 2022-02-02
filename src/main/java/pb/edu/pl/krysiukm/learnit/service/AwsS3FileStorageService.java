package pb.edu.pl.krysiukm.learnit.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class AwsS3FileStorageService implements FilesStorageService {

    private final AmazonS3 amazonS3Client;
    private final String bucketName;

    @Autowired
    public AwsS3FileStorageService(AmazonS3 amazonS3Client,
                                   @Value("${application.bucket.name}") String bucketName) {
        this.amazonS3Client = amazonS3Client;
        this.bucketName = bucketName;
    }

    @Override
    public String save(MultipartFile file) {
        String randomId = UUID.randomUUID().toString();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        String filename = randomId + "." + extension;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucketName, filename, file.getInputStream(), metadata);
            return filename;
        } catch (IOException ioe) {
            log.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException serviceException) {
            log.error("AmazonServiceException: "+ serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            log.error("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }
        return "File not uploaded: " + filename;
    }

    @Override
    public void deleteFile(String filename) {
        amazonS3Client.deleteObject(bucketName, filename);
    }
}
