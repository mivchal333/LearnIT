package pb.edu.pl.krysiukm.learnit.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Question extends AbstractEntity {
    private String body;
    private String correctAnswer;
    @OneToOne
    private User creator;
    @OneToOne
    private Technology technology;
    @OneToOne
    private Difficulty difficulty;
    @ElementCollection
    private List<String> badAnswers;

}


