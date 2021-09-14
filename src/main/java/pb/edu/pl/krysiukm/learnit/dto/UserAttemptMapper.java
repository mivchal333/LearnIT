package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.util.stream.Collectors;

@Component
public class UserAttemptMapper {

    public UserAttemptDto mapToDto(UserAttempt userAttempt) {
        return UserAttemptDto.builder()
                .id(userAttempt.getId())
                .gameType(userAttempt.getGameType())
                .endDate(userAttempt.getEndDate())
                .startDate(userAttempt.getStartDate())
                .history(userAttempt.getHistoryEntries()
                        .stream()
                        .map(HistoryEntryDto::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
