package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    @Column(length = 2000)
    private String codeAttachment;
    private String codeLang;
    private boolean published;

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
    private List<Answer> answers;

    public Question(String body, String codeAttachment, String codeLang, UserAccount creator, Technology technology, Integer difficulty, List<Answer> answers) {
        this.body = body;
        this.codeAttachment = codeAttachment;
        this.codeLang = codeLang;
        this.creator = creator;
        this.technology = technology;
        this.difficulty = difficulty;
        this.answers = answers;
    }

    public Answer getCorrectAnswer() {
        return this.answers.stream().filter(Answer::isCorrect)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Missing correct answer"));
    }

    public List<Answer> getBadAnswers() {
        return this.answers.stream().filter(answer -> !answer.isCorrect())
                .collect(Collectors.toList());
    }
}


