package pb.edu.pl.krysiukm.learnit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Technology extends AbstractEntity {
    private String name;
    private String description;
}
