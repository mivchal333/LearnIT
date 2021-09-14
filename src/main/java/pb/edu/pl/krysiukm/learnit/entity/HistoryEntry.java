package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class HistoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Question question;
    private Boolean answerResult;
    private Instant date;

    public HistoryEntry(Question question, Boolean answerResult, Instant date) {
        this.question = question;
        this.answerResult = answerResult;
        this.date = date;
    }
}
