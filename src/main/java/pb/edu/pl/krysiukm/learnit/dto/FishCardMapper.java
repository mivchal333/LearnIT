package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;

@Component
public class FishCardMapper {

    public FishCardDto mapToDto(ProgressWrapper<Question> questionProgress) {
        return FishCardDto.builder()
                .body(questionProgress.getEntry().getBody())
                .answer(questionProgress.getEntry().getCorrectAnswer())
                .build();
    }
}
