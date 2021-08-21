package pb.edu.pl.krysiukm.learnit.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Question extends AbstractEntity {
    private String body;
    @OneToOne
    private Answer correctAnswer;
    @OneToOne
    private User creator;
    @OneToOne
    private Technology technology;
    @OneToOne
    private Difficulty difficulty;
}


