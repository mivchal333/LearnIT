package pb.edu.pl.krysiukm.learnit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @OneToOne
    private Answer correctAnswer;

    @ManyToOne
    private User creator;
    @ManyToOne
    private Technology technology;
    @ManyToOne
    private Difficulty difficulty;

    @JoinColumn(name = "QUESTION_ID")
    @OneToMany
    private List<Answer> badAnswers;
}


