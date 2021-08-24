package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.Question;
import pb.edu.pl.krysiukm.learnit.model.User;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAttemptService {
    private final UserAttemptRepository userAttemptRepository;

    public UserAttempt startAttempt(User user) {
        UserAttempt attempt = new UserAttempt(user);
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

    private UserAttempt getUserAttempt(String attemptId) throws NotFoundException {
        return userAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new NotFoundException("User attempt does not exist! Id:" + attemptId));
    }
}
