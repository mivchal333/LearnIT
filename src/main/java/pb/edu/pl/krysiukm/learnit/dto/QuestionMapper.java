package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.model.Question;

import java.util.Collections;
import java.util.List;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Override
    public QuestionDto mapToDto(Question entity) {
        List<String> answers = entity.getBadAnswers();
        answers.add(entity.getCorrectAnswer());
        Collections.shuffle(answers);
        return QuestionDto.builder()
                .body(entity.getBody())
                .answers(answers)
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
