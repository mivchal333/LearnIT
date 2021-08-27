package pb.edu.pl.krysiukm.learnit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerResult {
    private boolean isCorrect;
    private String message;

    public AnswerResult(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
