package pb.edu.pl.krysiukm.learnit.dto.question;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionCreateRequestDto {
    private String body;
    private String codeAttachment;
    private String codeLang;
    private Long technologyId;
    private Integer difficultyValue;
    private AnswerPayload correctAnswer;
    private List<AnswerPayload> badAnswers;
}
