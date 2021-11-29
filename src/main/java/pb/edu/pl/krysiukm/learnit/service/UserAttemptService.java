package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.entity.*;
import pb.edu.pl.krysiukm.learnit.repository.HistoryEntryRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;
import pb.edu.pl.krysiukm.learnit.service.exception.NotFoundException;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAttemptService {
    private final Clock clock;
    private final UserAttemptRepository userAttemptRepository;
    private final TechnologyService technologyService;
    private final UserRepository userRepository;
    private final HistoryEntryRepository historyEntryRepository;

    public UserAttempt startQuizAttempt(UserAccount userAccount, List<Long> technologyIds) {
        List<Technology> technologies = technologyService.findTechnologiesByIds(technologyIds);
        UserAttempt attempt = new UserAttempt(userAccount, technologies);
        attempt.setStartDate(clock.instant());

        technologies.forEach(technology -> {
            technology.getUserAttempts().add(attempt);
        });

        return userAttemptRepository.save(attempt);
    }

    public List<Question> getExposedQuestions(String attemptId) throws NotFoundException {
        UserAttempt userAttempt = getUserAttempt(attemptId);
        return userAttempt.getHistoryEntries().stream()
                .map(HistoryEntry::getQuestion)
                .collect(Collectors.toList());
    }

    public void addHistoryEntry(String attemptId, Question newQuestion, Boolean result) throws NotFoundException {
        UserAttempt userAttempt = this.getUserAttempt(attemptId);

        HistoryEntry historyEntry = new HistoryEntry(newQuestion, result, clock.instant());

        userAttempt.getHistoryEntries().add(historyEntry);

        userAttemptRepository.save(userAttempt);
    }

    public UserAttempt getUserAttempt(String id) {
        return userAttemptRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User attempt does not exist! Id:" + id));
    }

    public List<UserAttempt> getUserAttempts(UserAccount userAccount) {
        return userAttemptRepository.findAllByUserAccount(userAccount);
    }

    public List<UserAttempt> getUserHistory(UserAccount userAccount, Long technologyId) {
        Technology technology = technologyService.getById(technologyId);
        return userAttemptRepository.findAllByUserAccountAndTechnologiesInOrderByStartDateDesc(userAccount, List.of(technology));
    }

    public List<UserAttempt> getUserHistory(UserAccount userAccount, Long technologyId, Instant date) {
        return this.getUserHistory(userAccount, technologyId)
                .stream().filter(userAttempt -> userAttempt.getStartDate().isAfter(date))
                .collect(Collectors.toList());
    }
}
