package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Answer;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;

@Component
public class CardMapper {

    public CardDto mapToDto(ProgressWrapper<Question> questionProgress) {
        Question question = questionProgress.getEntry();
        Answer correctAnswer = question.getAnswers()
                .stream()
                .filter(Answer::isCorrect)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Missing correct answer in question: " + question.getId()));


        return CardDto.builder()
                .body(question.getBody())
                .difficultyValue(question.getDifficulty())
                .codeAttachment(question.getCodeAttachment())
                .codeLang(question.getCodeLang())
                .answer(correctAnswer)
                .build();
    }
}
