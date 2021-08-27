package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.model.*;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserAttemptService userAttemptService;
    private final ShowedQuestionService showedQuestionService;


    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getNextQuestion(String attemptId) throws NotFoundException {
        UserAttempt userAttempt = userAttemptService.getUserAttempt(attemptId);

        List<Long> exposedQuestionsIds = userAttemptService.getExposedQuestions(attemptId)
                .stream().map(Question::getId)
                .collect(Collectors.toList());

        Technology technology = userAttempt.getTechnology();

        List<Question> foundQuestions;
        if (exposedQuestionsIds.isEmpty()) {
            foundQuestions = questionRepository.findAllByTechnology(technology);
        } else {
            foundQuestions = questionRepository.findAllByTechnologyAndIdNotIn(technology, exposedQuestionsIds);
        }

        if (foundQuestions.isEmpty()) {
            log.warn("No more questions!");
            throw new NotFoundException("No more questions!");
        }

        int listSize = foundQuestions.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(listSize);

        Question randomQuestion = foundQuestions.get(randomIndex);

        userAttemptService.addExposedQuestion(attemptId, randomQuestion);

        return randomQuestion;
    }


    public AnswerResult submitAnswer(AnswerSubmit submitPayload) throws NotFoundException {
        String attemptId = submitPayload.getAttemptId();
        UserAttempt userAttempt = userAttemptService.getUserAttempt(attemptId);

        ShowedQuestion lastShowedUserQuestion = showedQuestionService.getLastShowedUserQuestion(userAttempt);

        Question lastUserQuestion = lastShowedUserQuestion.getQuestion();

        String userAnswer = submitPayload.getAnswer();
        String correctAnswer = lastUserQuestion.getCorrectAnswer();

        if (correctAnswer.equals(userAnswer)) {
            return new AnswerResult(true);
        }
        return new AnswerResult(false);
    }
}
