package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.Question;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.model.User;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.time.Clock;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAttemptService {
    private final Clock clock;
    private final UserAttemptRepository userAttemptRepository;
    private final TechnologyService technologyService;

    public UserAttempt startAttempt(User user, Long technologyId) {
        Technology technology = technologyService.getById(technologyId);
        UserAttempt attempt = new UserAttempt(user, technology);
        attempt.setStartDate(clock.instant());
        return userAttemptRepository.save(attempt);
    }

    public List<Question> getExposedQuestions(String attemptId) throws NotFoundException {
        UserAttempt userAttempt = getUserAttempt(attemptId);
        return userAttempt.getExposedQuestions();
    }

    public void addExposedQuestion(String attemptId, Question newQuestion) throws NotFoundException {
        UserAttempt userAttempt = this.getUserAttempt(attemptId);

        userAttempt.getExposedQuestions().add(newQuestion);

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
