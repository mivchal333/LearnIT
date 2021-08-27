package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.model.ShowedQuestion;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.ShowedQuestionRepository;

@RequiredArgsConstructor
@Service
public class ShowedQuestionService {
    private final ShowedQuestionRepository showedQuestionRepository;

    public ShowedQuestion getLastShowedUserQuestion(UserAttempt userAttempt) {
        return showedQuestionRepository.findByUserAttempt(userAttempt).orElseThrow(ResourceNotFoundException::new);
    }
}
