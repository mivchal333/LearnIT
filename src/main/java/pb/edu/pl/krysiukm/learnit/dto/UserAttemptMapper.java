package pb.edu.pl.krysiukm.learnit.dto;

import org.springframework.stereotype.Component;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserAttemptMapper {

    public UserAttemptDto mapToDto(UserAttempt userAttempt) {
        UserAttemptDto.UserAttemptDtoBuilder builder = UserAttemptDto.builder()
                .id(userAttempt.getId())
                .gameType(userAttempt.getGameType())
                .startDate(userAttempt.getStartDate().toEpochMilli());

        List<HistoryEntryDto> history = userAttempt.getHistoryEntries()
                .stream()
                .map(HistoryEntryDto::new)
                .collect(Collectors.toList());
        builder.history(history);

        Optional.ofNullable(userAttempt.getEndDate()).ifPresent(endDate -> builder.endDate(endDate.toEpochMilli()));

        return builder.build();
    }
}
