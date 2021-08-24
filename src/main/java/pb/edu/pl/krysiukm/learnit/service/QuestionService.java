package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.Question;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TechnologyService technologyService;
    private final UserAttemptRepository userAttemptRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getQuestion(Long attemptId, Long technologyId) throws NotFoundException {
        UserAttempt userAttempt = userAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new NotFoundException("User attempt does not exist! Id:" + attemptId));

        List<Long> exposedQuestionsIds = userAttempt.getExposedQuestions()
                .stream().map(Question::getId)
                .collect(Collectors.toList());
        Technology technology = technologyService.getById(technologyId)
                .orElseThrow(() -> new NotFoundException("Technology does not exist! Id: " + technologyId));


        List<Question> foundQuestions = questionRepository.findAllByTechnologyAndIdNotIn(technology, exposedQuestionsIds);

        int listSize = foundQuestions.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(listSize);

        return foundQuestions.get(randomIndex);
    }

}
