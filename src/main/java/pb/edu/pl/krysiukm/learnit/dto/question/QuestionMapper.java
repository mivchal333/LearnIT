package pb.edu.pl.krysiukm.learnit.dto.question;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Answer;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class QuestionMapper {

    public QuestionRequestResponseDto mapToDto(ProgressWrapper<Question> entity) {
        Question question = entity.getEntry();
        List<Answer> answers = Stream.concat(
                        question.getBadAnswers().stream(),
                        Stream.of(question.getCorrectAnswer()))
                .collect(Collectors.toList());

        Collections.shuffle(answers);

        return QuestionRequestResponseDto.builder()
                .body(question.getBody())
                .codeAttachment(question.getCodeAttachment())
                .codeLang(question.getCodeLang())
                .answers(answers)
                .difficultyValue(question.getDifficulty())
                .technologyId(question.getTechnology().getId())
                .build();
    }

    public QuestionPreviewDto mapToDto(Question question) {

        return QuestionPreviewDto.builder()
                .id(question.getId())
                .body(question.getBody())
                .difficulty(question.getDifficulty())
                .published(question.isPublished())
                .build();
    }
}
