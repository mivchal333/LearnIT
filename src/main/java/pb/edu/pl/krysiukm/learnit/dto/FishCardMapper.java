package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Question;

@Component
public class FishCardMapper {

    public FishCardDto mapToDto(Question entity) {
        return new FishCardDto(entity.getBody(), entity.getCorrectAnswer());
    }
}
