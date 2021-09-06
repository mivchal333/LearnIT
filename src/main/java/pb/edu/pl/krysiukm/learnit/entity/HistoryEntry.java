package pb.edu.pl.krysiukm.learnit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.Instant;

@Getter
@Setter
@Entity
public class HistoryEntry extends AbstractEntity {
    @OneToOne
    private UserAttempt userAttempt;
    @OneToOne
    private Question question;
    private Boolean answerResult;
    private Instant date;
}
