package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.entity.ShowedQuestion;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.ShowedQuestionRepository;

import java.time.Clock;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ShowedQuestionService {
    private final ShowedQuestionRepository showedQuestionRepository;
    private final Clock clock;

    public ShowedQuestion getLastShowedQuestion(UserAttempt userAttempt) {
        Optional<ShowedQuestion> byUserAttempt = showedQuestionRepository.findByUserAttempt(userAttempt);
        if (byUserAttempt.isEmpty()) {
            log.error("[ShowedQuestionService] Showed question not found for attemptId: {}", userAttempt.getId());
            throw new ResourceNotFoundException("Showed question not found.");
        }
        return byUserAttempt.get();
    }

    public void showQuestion(UserAttempt userAttempt, Question question) {
        ShowedQuestion showedQuestion = new ShowedQuestion(question, userAttempt, clock.instant());

        showedQuestionRepository.save(showedQuestion);
    }

    public void deleteShowedQuestion(ShowedQuestion showedQuestion) {
        showedQuestionRepository.delete(showedQuestion);
    }
}
