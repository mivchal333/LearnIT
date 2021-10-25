package pb.edu.pl.krysiukm.learnit.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pb.edu.pl.krysiukm.learnit.message.FileUploadedMessage;

@Slf4j
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    private final String maxFileSize;

    public FileUploadExceptionAdvice(@Value("spring.servlet.multipart.max-file-size") String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<FileUploadedMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        log.warn("File limit error.", exc);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileUploadedMessage("File is too large. Max limit: " + maxFileSize));
    }
}