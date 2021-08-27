package pb.edu.pl.krysiukm.learnit.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Technology extends AbstractEntity {
    private String name;
    private String description;
    @OneToMany
    private List<Question> questions;
}
