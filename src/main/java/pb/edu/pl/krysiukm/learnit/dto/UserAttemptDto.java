package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAttemptDto {
    private String id;
    private List<HistoryEntryDto> history;
    private Long startDate;
    private Long endDate;
}
