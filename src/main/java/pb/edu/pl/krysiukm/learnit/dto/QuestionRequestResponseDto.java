package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Getter;
import pb.edu.pl.krysiukm.learnit.entity.Answer;

import java.util.List;

@Getter
@Builder
public class QuestionRequestResponseDto {
    private final String body;
    private final Long technologyId;
    private final Integer difficultyValue;
    private final List<Answer> answers;
}
