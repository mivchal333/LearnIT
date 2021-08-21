package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.model.Question;

import java.util.stream.Collectors;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Override
    public QuestionDto mapToDto(Question entity) {
        return QuestionDto.builder()
                .body(entity.getBody())
                .correctAnswer(entity.getCorrectAnswer())
                .badAnswers(entity.getBadAnswers())
                .difficultyId(entity.getDifficulty().getId())
                .technologyId(entity.getTechnology().getId())
                .build();
    }

    @Override
    public Question mapToEntity(QuestionDto dto) {
        Question question = new Question();
        question.setBody(dto.getBody());
        return null;
    }
}
