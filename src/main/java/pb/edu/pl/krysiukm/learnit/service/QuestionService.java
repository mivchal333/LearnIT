package pb.edu.pl.krysiukm.learnit.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserAttemptService userAttemptService;
    private final ShowedQuestionService showedQuestionService;
    private final TechnologyService technologyService;
    private final UserService userService;

    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

    public Question editQuestion(Long id, QuestionCreateRequestDto createRequestDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found question with id " + id));

        Answer correctAnswer = question.getCorrectAnswer();
        correctAnswer.setBody(createRequestDto.getCorrectAnswer().getBody());
        correctAnswer.setCode(createRequestDto.getCorrectAnswer().getCode());

        for (int i = 0; i < question.getBadAnswers().size(); i++) {
            Answer answer = question.getBadAnswers().get(i);
            AnswerPayload answerPayload = createRequestDto.getBadAnswers().get(i);
            answer.setBody(answerPayload.getBody());
            answer.setCode(answerPayload.getCode());
        }

        question.setDifficulty(createRequestDto.getDifficultyValue());

        question.setCodeAttachment(createRequestDto.getCodeAttachment());
        question.setCodeLang(createRequestDto.getCodeLang());

        return questionRepository.save(question);
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    public Question createQuestion(Long technologyId, QuestionCreateRequestDto createRequestDto) {

        AnswerPayload correctAnswerPayload = createRequestDto.getCorrectAnswer();
        Answer correctAnswer = new Answer(correctAnswerPayload.getBody(), correctAnswerPayload.getCode(), true);

        Technology technology = technologyService.getById(technologyId);


        List<Answer> answers = createRequestDto.getBadAnswers().stream()
                .map(answerPayload -> new Answer(answerPayload.getBody(), answerPayload.getCode()))
                .collect(Collectors.toList());

        answers.add(correctAnswer);
        Question question = Question.builder()
                .body(createRequestDto.getBody())
                .codeAttachment(createRequestDto.getCodeAttachment())
                .codeLang(createRequestDto.getCodeLang())
                .technology(technology)
                .difficulty(createRequestDto.getDifficultyValue())
                .answers(answers)
                .published(false)
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
                .stream()
                .map(Question::getId)
                .collect(Collectors.toList());

        List<Technology> technologies = userAttempt.getTechnologies();

        List<Question> foundQuestions;
        if (exposedQuestionsIds.isEmpty()) {
            foundQuestions = questionRepository.findAllByTechnologyInAndPublishedTrue(technologies);
        } else {
            foundQuestions = questionRepository.findAllByTechnologyInAndIdNotInAndPublishedTrue(technologies, exposedQuestionsIds);
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
                .total(questionRepository.findAllByTechnologyInAndPublishedTrue(technologies).size())
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
            int difficulty = lastUserQuestion.getDifficulty();
            userService.addPoints(userAccount.getEmail(), difficulty);
            userAttemptService.addHistoryEntry(attemptId, lastUserQuestion, true);
            return new AnswerResult(true);
        }
        userAttemptService.addHistoryEntry(attemptId, lastUserQuestion, false);
        return new AnswerResult(false, "Bad answer");
    }

    public long countQuestions(Long technologyId) {
        return questionRepository.countAllByTechnologyId(technologyId);
    }

    public void setPublishState(Long id, Boolean published) {
        questionRepository.setPublishedStateForQuestionId(id, published);
    }
}
