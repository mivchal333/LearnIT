package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.dto.QuestionCreateRequestDto;
import pb.edu.pl.krysiukm.learnit.dto.QuestionMapper;
import pb.edu.pl.krysiukm.learnit.dto.QuestionRequestResponseDto;
import pb.edu.pl.krysiukm.learnit.entity.*;
import pb.edu.pl.krysiukm.learnit.model.AnswerResult;
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
    private final DifficultyService difficultyService;
    private final TechnologyService technologyService;
    private final QuestionMapper questionMapper;

    public Question createQuestion(QuestionCreateRequestDto createRequestDto) {
        Question question = new Question();
        question.setBody(createRequestDto.getBody());

        Answer correctAnswer = new Answer(createRequestDto.getCorrectAnswer());
        question.setCorrectAnswer(correctAnswer);

        Technology technology = technologyService.getById(createRequestDto.getTechnologyId());
        question.setTechnology(technology);

        Difficulty difficulty = difficultyService.getById(createRequestDto.getDifficultyId());
        question.setDifficulty(difficulty);

        List<Answer> badAnswers = createRequestDto.getBadAnswers().stream()
                .map(Answer::new)
                .collect(Collectors.toList());

        question.setBadAnswers(badAnswers);
        return createQuestion(question);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public QuestionRequestResponseDto getNextQuestion(String attemptId) throws NotFoundException {
        UserAttempt userAttempt = userAttemptService.getUserAttempt(attemptId);

        List<Long> exposedQuestionsIds = userAttemptService.getExposedQuestions(attemptId)
                .stream().map(Question::getId)
                .collect(Collectors.toList());

        Technology technology = userAttempt.getTechnology();

        List<Question> foundQuestions;
        if (true) {
            foundQuestions = questionRepository.findAllByTechnology(technology);
        } else {
            foundQuestions = questionRepository.findAllByTechnologyAndIdNotIn(technology, exposedQuestionsIds);
        }

        if (foundQuestions.isEmpty()) {
            throw new NotFoundException("No more questions!");
        }

        int listSize = foundQuestions.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(listSize);

        Question randomQuestion = foundQuestions.get(randomIndex);

        showedQuestionService.showQuestion(userAttempt, randomQuestion);

        return questionMapper.mapToDto(randomQuestion);
    }


    public AnswerResult submitAnswer(AnswerSubmit submitPayload) throws NotFoundException {
        String attemptId = submitPayload.getAttemptId();
        UserAttempt userAttempt = userAttemptService.getUserAttempt(attemptId);

        ShowedQuestion lastShowedUserQuestion = showedQuestionService.getLastShowedQuestion(userAttempt);

        showedQuestionService.deleteShowedQuestion(lastShowedUserQuestion);

        Question lastUserQuestion = lastShowedUserQuestion.getQuestion();

        Long userAnswerId = submitPayload.getAnswerId();
        Answer correctAnswer = lastUserQuestion.getCorrectAnswer();

        if (correctAnswer.getId().equals(userAnswerId)) {
            userAttemptService.addHistoryEntry(attemptId, lastUserQuestion, true);
            return new AnswerResult(true);
        }
        userAttemptService.addHistoryEntry(attemptId, lastUserQuestion, false);
        return new AnswerResult(false, "Bad answer");
    }
}
