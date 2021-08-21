package pb.edu.pl.krysiukm.learnit.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class MultiChoiceQuestion extends Question {
    @OneToMany
    private Set<Answer> badAnswers;
}
