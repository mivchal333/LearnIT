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
    private boolean published;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer correctAnswer;

    @OneToMany(orphanRemoval = true, mappedBy = "question")
    private List<HistoryEntry> historyEntries;

    @JsonIgnore
    @ManyToOne
    private UserAccount creator;
    @ManyToOne
    private Technology technology;
    private Integer difficulty;

    @JoinColumn(name = "QUESTION_ID")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> badAnswers;

    public Question(String body, String codeAttachment, String codeLang, Answer correctAnswer, UserAccount creator, Technology technology, Integer difficulty, List<Answer> badAnswers) {
        this.body = body;
        this.codeAttachment = codeAttachment;
        this.codeLang = codeLang;
        this.correctAnswer = correctAnswer;
        this.creator = creator;
        this.technology = technology;
        this.difficulty = difficulty;
        this.badAnswers = badAnswers;
    }
}


