package pb.edu.pl.krysiukm.learnit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ShowedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    @OneToOne(mappedBy = "userAttempt")
    private UserAttempt userAttempt;
    private Instant date;

    public ShowedQuestion(Question question, UserAttempt userAttempt, Instant date) {
        this.question = question;
        this.userAttempt = userAttempt;
        this.date = date;
    }
}
