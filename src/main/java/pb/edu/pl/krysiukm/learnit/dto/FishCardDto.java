package pb.edu.pl.krysiukm.learnit.dto;


import lombok.Data;
import pb.edu.pl.krysiukm.learnit.entity.Answer;

@Data
public class FishCardDto {
    private final String body;
    private final Answer answer;
}
