package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.ShowedQuestion;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.ShowedQuestionRepository;

@RequiredArgsConstructor
@Service
public class ShowedQuestionService {
    private final ShowedQuestionRepository showedQuestionRepository;

    public ShowedQuestion getLastShowedUserQuestion(UserAttempt userAttempt) {
        return showedQuestionRepository.findByUserAttempt(userAttempt).orElseThrow(() -> new ResourceNotFoundException(String.format("Showed question with id: %s not found")));
    }
}
