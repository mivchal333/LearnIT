package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Technology extends AbstractEntity {
    private String name;
    @Column(length = 1_000)
    private String description;
    private String image;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technology")
    private List<Question> questions;

    @JsonIgnore
    @JoinTable(
            name = "TECHNOLOGY_USER_ATTEMPT",
            joinColumns = @JoinColumn(name = "TECHNOLOGY_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ATTEMPT_ID")
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserAttempt> userAttempts;

    public Technology(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
