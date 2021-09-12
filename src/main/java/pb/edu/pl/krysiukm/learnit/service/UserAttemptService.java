package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.entity.*;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAttemptService {
    private final Clock clock;
    private final UserAttemptRepository userAttemptRepository;
    private final TechnologyService technologyService;

    public UserAttempt startQuizAttempt(User user, Long technologyId) {
        Technology technology = technologyService.getById(technologyId);
        UserAttempt attempt = new UserAttempt(user, technology, UserAttempt.GameType.QUIZ);
        attempt.setStartDate(clock.instant());
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

    public UserAttempt getUserAttempt(String id) throws NotFoundException {
        return userAttemptRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User attempt does not exist! Id:" + id));
    }

    public List<UserAttempt> getUserAttempts(User user) {
        return userAttemptRepository.findAllByUser(user);
    }
}
