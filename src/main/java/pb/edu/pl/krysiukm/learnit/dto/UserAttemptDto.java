package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.util.List;

@Data
@Builder
public class UserAttemptDto {
    private String id;
    private List<HistoryEntryDto> history;
    private Long startDate;
    private Long endDate;
    private UserAttempt.GameType gameType;
}
