package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public Optional<ShowedQuestion> getLastShowedQuestion(UserAttempt userAttempt) {
        return showedQuestionRepository.findByUserAttempt(userAttempt);
    }

    public void showQuestion(UserAttempt userAttempt, Question question) {
        ShowedQuestion showedQuestion = new ShowedQuestion(question, userAttempt, clock.instant());

        showedQuestionRepository.save(showedQuestion);
    }

    public void deleteShowedQuestion(ShowedQuestion showedQuestion) {
        showedQuestionRepository.delete(showedQuestion);
    }
}
