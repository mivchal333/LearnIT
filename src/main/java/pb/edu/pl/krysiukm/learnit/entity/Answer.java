package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 999)
    private String body;
    @Column(length = 999)
    private String code;
    @OneToOne
    @JsonIgnore
    private Question question;
    private boolean correct;

    public Answer(String body, String code) {
        this.body = body;
        this.code = code;
    }

    public Answer(String body, String code, boolean correct) {
        this.body = body;
        this.code = code;
        this.correct = correct;
    }
}
