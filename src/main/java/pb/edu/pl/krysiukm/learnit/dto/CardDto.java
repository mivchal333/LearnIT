package pb.edu.pl.krysiukm.learnit.dto;


import lombok.Builder;
import lombok.Getter;
import pb.edu.pl.krysiukm.learnit.entity.Answer;

@Getter
@Builder
public class CardDto {
    private final String body;
    private String codeAttachment;
    private String codeLang;
    private final Answer answer;
    private int difficultyValue;
}
