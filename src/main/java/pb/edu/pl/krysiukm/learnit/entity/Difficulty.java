package pb.edu.pl.krysiukm.learnit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Difficulty extends AbstractEntity {
    private String label;
    private int value;
}