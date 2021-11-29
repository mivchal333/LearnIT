package pb.edu.pl.krysiukm.learnit.dto.question;

import lombok.Builder;
import lombok.Data;
import pb.edu.pl.krysiukm.learnit.entity.Question;

@Data
public class QuestionPreviewDto {
    private Long id;
    private String body;
    private int difficulty;
    private boolean published;
    private String creatorEmail;

    @Builder
    public QuestionPreviewDto(Long id, String body, int difficulty, boolean published, String creatorEmail) {
        this.id = id;
        this.body = body;
        this.difficulty = difficulty;
        this.published = published;
        this.creatorEmail = creatorEmail;
    }

    public QuestionPreviewDto(Question question) {
        this.id = question.getId();
        this.body = question.getBody();
        this.difficulty = question.getDifficulty();
        this.creatorEmail = question.getCreator().getEmail();
    }
}
