package pb.edu.pl.krysiukm.learnit.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pb.edu.pl.krysiukm.learnit.entity.Answer;

@EqualsAndHashCode(callSuper = true)
@Data
public class FishCardDto extends GameProgressWrapper {
    private final String body;
    private final Answer answer;
}
