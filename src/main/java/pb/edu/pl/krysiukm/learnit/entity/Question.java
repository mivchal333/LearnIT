package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer correctAnswer;

    @JsonIgnore
    @ManyToOne
    private UserAccount creator;
    @ManyToOne
    private Technology technology;
    private Integer difficulty;

    @JoinColumn(name = "QUESTION_ID")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> badAnswers;
}


