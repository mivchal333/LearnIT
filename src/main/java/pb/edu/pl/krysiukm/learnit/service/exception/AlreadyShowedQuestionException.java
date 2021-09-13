package pb.edu.pl.krysiukm.learnit.service.exception;

public class AlreadyShowedQuestionException extends RuntimeException {
    public AlreadyShowedQuestionException(String message) {
        super(message);
    }
}
