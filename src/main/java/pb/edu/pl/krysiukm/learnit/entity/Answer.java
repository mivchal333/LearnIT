package pb.edu.pl.krysiukm.learnit.entity;

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

    public Answer(String body, String code) {
        this.body = body;
        this.code = code;
    }
}
