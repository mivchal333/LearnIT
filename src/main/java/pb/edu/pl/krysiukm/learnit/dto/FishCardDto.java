package pb.edu.pl.krysiukm.learnit.dto;


import lombok.Builder;
import lombok.Getter;
import pb.edu.pl.krysiukm.learnit.entity.Answer;

@Getter
@Builder
public class FishCardDto {
    private final String body;
    private final Answer answer;
}
