package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;
import pb.edu.pl.krysiukm.learnit.entity.HistoryEntry;

import java.time.Instant;

@Data
public class HistoryEntryDto {
    private Long id;
    private QuestionPreviewDto question;
    private Boolean answerResult;
    private Instant date;

    public HistoryEntryDto(HistoryEntry historyEntry) {
        this.id = historyEntry.getId();
        this.question = new QuestionPreviewDto(historyEntry.getQuestion());
        this.answerResult = historyEntry.getAnswerResult();
        this.date = historyEntry.getDate();
    }

    @Builder
    public HistoryEntryDto(Long id, QuestionPreviewDto question, Boolean answerResult, Instant date) {
        this.id = id;
        this.question = question;
        this.answerResult = answerResult;
        this.date = date;
    }
}
