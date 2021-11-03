package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionCreateRequestDto {
    private String body;
    private Long technologyId;
    private Integer difficultyValue;
    private String correctAnswer;
    private List<String> badAnswers;
}
