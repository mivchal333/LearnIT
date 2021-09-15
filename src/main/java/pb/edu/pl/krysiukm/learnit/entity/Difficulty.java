package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"createDate", "updateDate"})
public class Difficulty extends AbstractEntity {
    private String label;
    private int value;
}