package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class TechnologyEntity extends AbstractEntity {
    private String name;
    @Column(length = 1_000)
    private String description;
    private String image;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

}
