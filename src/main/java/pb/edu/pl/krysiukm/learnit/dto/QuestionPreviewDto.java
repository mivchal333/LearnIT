package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;
import pb.edu.pl.krysiukm.learnit.entity.Difficulty;
import pb.edu.pl.krysiukm.learnit.entity.Question;

@Data
public class QuestionPreviewDto {
    private Long id;
    private String body;
    private Difficulty difficulty;

    public QuestionPreviewDto(Question question) {
        this.id = question.getId();
        this.body = question.getBody();
        this.difficulty = question.getDifficulty();
    }

    @Builder
    public QuestionPreviewDto(Long id, String body, Difficulty difficulty) {
        this.id = id;
        this.body = body;
        this.difficulty = difficulty;
    }
}
