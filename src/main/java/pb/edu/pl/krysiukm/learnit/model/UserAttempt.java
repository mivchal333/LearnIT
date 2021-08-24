package pb.edu.pl.krysiukm.learnit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class UserAttempt {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")    private String id;
    @OneToMany
    private List<Question> exposedQuestions;

    @JsonIgnore
    @OneToOne
    private User user;

    public UserAttempt(User user) {
        this.user = user;
        this.exposedQuestions = new ArrayList<>();
    }
}
