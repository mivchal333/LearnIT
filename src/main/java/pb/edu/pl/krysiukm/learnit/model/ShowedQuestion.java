package pb.edu.pl.krysiukm.learnit.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
public class ShowedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Question question;
    @OneToOne
    private User user;
    @OneToOne
    private UserAttempt userAttempt;
    private Instant date;
}
