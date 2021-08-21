package pb.edu.pl.krysiukm.learnit.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class HistoryEntry extends AbstractEntity {
    @OneToOne
    private User user;
    @OneToOne
    private MultiChoiceQuestion question;
    private boolean isCorrect;
    private Instant date;
}
