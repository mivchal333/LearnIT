package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Technology extends AbstractEntity {
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserAttempt> userAttempts;
}
