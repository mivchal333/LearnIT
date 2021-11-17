package pb.edu.pl.krysiukm.learnit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 999)
    private String body;
    @Column(length = 999)
    private String codeAttachment;
    private String codeLang;

    @OneToOne(cascade = CascadeType.ALL)
    private Answer correctAnswer;

    @ManyToOne
    private UserAccount creator;
    @ManyToOne
    private TechnologyEntity technologyEntity;
    @ManyToOne
    private Difficulty difficulty;

    @JoinColumn(name = "QUESTION_ID")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> badAnswers;
}


