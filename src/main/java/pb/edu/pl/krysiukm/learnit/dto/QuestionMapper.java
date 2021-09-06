package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Answer;
import pb.edu.pl.krysiukm.learnit.entity.Question;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public QuestionRequestResponseDto mapToDto(Question entity) {
        List<String> answers = entity.getBadAnswers().stream()
                .map(Answer::getBody)
                .collect(Collectors.toList());

        answers.add(entity.getCorrectAnswer().getBody());
        Collections.shuffle(answers);
        return QuestionRequestResponseDto.builder()
                .body(entity.getBody())
                .answers(answers)
                .difficultyId(entity.getDifficulty().getId())
                .technologyId(entity.getTechnology().getId())
                .build();
    }
}
