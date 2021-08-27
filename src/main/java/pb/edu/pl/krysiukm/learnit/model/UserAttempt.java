package pb.edu.pl.krysiukm.learnit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class UserAttempt {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToMany
    private List<Question> exposedQuestions;

    @JsonIgnore
    @OneToOne
    private User user;
    private Instant startDate;

    @OneToOne
    private Technology technology;

    public UserAttempt(User user, Technology technology) {
        this.user = user;
        this.exposedQuestions = new ArrayList<>();
        this.technology = technology;
    }
}
