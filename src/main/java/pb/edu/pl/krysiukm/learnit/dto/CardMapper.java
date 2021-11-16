package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;

@Component
public class CardMapper {

    public CardDto mapToDto(ProgressWrapper<Question> questionProgress) {
        Question question = questionProgress.getEntry();
        return CardDto.builder()
                .body(question.getBody())
                .codeAttachment(question.getCodeAttachment())
                .codeLang(question.getCodeLang())
                .answer(question.getCorrectAnswer())
                .build();
    }
}
