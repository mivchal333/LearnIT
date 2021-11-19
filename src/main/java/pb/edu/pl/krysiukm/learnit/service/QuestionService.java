package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.dto.question.AnswerPayload;
import pb.edu.pl.krysiukm.learnit.dto.question.QuestionCreateRequestDto;
import pb.edu.pl.krysiukm.learnit.entity.*;
import pb.edu.pl.krysiukm.learnit.model.AnswerResult;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;
import pb.edu.pl.krysiukm.learnit.service.exception.AlreadyShowedQuestionException;
import pb.edu.pl.krysiukm.learnit.service.exception.NoMoreQuestionsException;

import java.util.List;
import java.util.Optional;
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
    private final UserService userService;

    public Question createQuestion(QuestionCreateRequestDto createRequestDto) {

        AnswerPayload correctAnswerPayload = createRequestDto.getCorrectAnswer();
        Answer correctAnswer = new Answer(correctAnswerPayload.getBody(), correctAnswerPayload.getCode());

        TechnologyEntity technologyEntity = technologyService.getById(createRequestDto.getTechnologyId());

        Difficulty difficulty = difficultyService.getByValue(createRequestDto.getDifficultyValue());

        List<Answer> badAnswers = createRequestDto.getBadAnswers().stream()
                .map(answerPayload -> new Answer(answerPayload.getBody(), answerPayload.getCode()))
                .collect(Collectors.toList());

        Question question = Question.builder()
                .body(createRequestDto.getBody())
                .codeAttachment(createRequestDto.getCodeAttachment())
                .codeLang(createRequestDto.getCodeLang())
                .correctAnswer(correctAnswer)
                .technologyEntity(technologyEntity)
                .difficulty(difficulty)
                .badAnswers(badAnswers)
                .build();

        return createQuestion(question);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public ProgressWrapper<Question> getNextQuestion(String attemptId) throws NotFoundException {

        UserAttempt userAttempt = userAttemptService.getUserAttempt(attemptId);
        Optional<ShowedQuestion> lastShowedQuestion = showedQuestionService.getLastShowedQuestion(userAttempt);
        if (lastShowedQuestion.isPresent()) {
            log.warn("Attempt: {} has already showed question.", attemptId);
            throw new AlreadyShowedQuestionException("Attempt has already showed question.");
        }

        List<Long> exposedQuestionsIds = userAttemptService.getExposedQuestions(attemptId)
                .stream().map(Question::getId)
                .collect(Collectors.toList());

        TechnologyEntity technologyEntity = userAttempt.getTechnologyEntity();

        List<Question> foundQuestions;
        if (exposedQuestionsIds.isEmpty()) {
            foundQuestions = questionRepository.findAllByTechnologyEntity(technologyEntity);
        } else {
            foundQuestions = questionRepository.findAllByTechnologyEntityAndIdNotIn(technologyEntity, exposedQuestionsIds);
        }

        if (foundQuestions.isEmpty()) {
            throw new NoMoreQuestionsException();
        }

        int totalSize = foundQuestions.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(totalSize);

        Question randomQuestion = foundQuestions.get(randomIndex);

        showedQuestionService.showQuestion(userAttempt, randomQuestion);

        return ProgressWrapper.<Question>builder()
                .entry(randomQuestion)
                .actual(userAttemptService.getExposedQuestions(attemptId).size())
                .total(questionRepository.findAllByTechnologyEntity(technologyEntity).size())
                .build();
    }

    public AnswerResult submitAnswer(AnswerSubmit submitPayload) throws NotFoundException {
        String attemptId = submitPayload.getAttemptId();
        UserAttempt userAttempt = userAttemptService.getUserAttempt(attemptId);
        UserAccount userAccount = userAttempt.getUserAccount();

        Optional<ShowedQuestion> lastShowedQuestionOpt = showedQuestionService.getLastShowedQuestion(userAttempt);
        ShowedQuestion showedQuestion = lastShowedQuestionOpt.orElseThrow(() -> new RuntimeException("Not found showed question!"));

        showedQuestionService.deleteShowedQuestion(showedQuestion);

        Question lastUserQuestion = showedQuestion.getQuestion();

        Long userAnswerId = submitPayload.getAnswerId();
        Answer correctAnswer = lastUserQuestion.getCorrectAnswer();

        if (correctAnswer.getId().equals(userAnswerId)) {
            int difficulty = lastUserQuestion.getDifficulty().getValue();
            userService.addPoints(userAccount.getEmail(), difficulty);
            userAttemptService.addHistoryEntry(attemptId, lastUserQuestion, true);
            return new AnswerResult(true);
        }
        userAttemptService.addHistoryEntry(attemptId, lastUserQuestion, false);
        return new AnswerResult(false, "Bad answer");
    }

    public long countQuestions(Long technologyId) {
        return questionRepository.countAllByTechnologyEntityId(technologyId);
    }
}
