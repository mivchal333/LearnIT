package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionRequestResponseDto {
    private String body;
    private Long technologyId;
    private Long difficultyId;
    private List<String> answers;
}
