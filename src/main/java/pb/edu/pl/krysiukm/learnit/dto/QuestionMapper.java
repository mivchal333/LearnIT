package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Answer;
import pb.edu.pl.krysiukm.learnit.entity.Question;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class QuestionMapper {

    public QuestionRequestResponseDto mapToDto(Question entity) {
        List<Answer> answers = Stream.concat(
                        entity.getBadAnswers().stream(),
                        Stream.of(entity.getCorrectAnswer()))
                .collect(Collectors.toList());

        Collections.shuffle(answers);
        return QuestionRequestResponseDto.builder()
                .body(entity.getBody())
                .answers(answers)
                .difficultyId(entity.getDifficulty().getId())
                .technologyId(entity.getTechnology().getId())
                .build();
    }
}
