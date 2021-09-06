package pb.edu.pl.krysiukm.learnit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResult {
    private Boolean correct;
    private String message;

    public AnswerResult(boolean correct) {
        this.correct = correct;
    }
}
