package pb.edu.pl.krysiukm.learnit.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.dto.GameProgressWrapper;
import pb.edu.pl.krysiukm.learnit.dto.question.QuestionCreateRequestDto;
import pb.edu.pl.krysiukm.learnit.dto.question.QuestionMapper;
import pb.edu.pl.krysiukm.learnit.dto.question.QuestionRequestResponseDto;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.AnswerResult;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody QuestionCreateRequestDto questionResponseDto) {
        try {
            Question question = questionService.createQuestion(questionResponseDto);
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            log.error("[QuestionController] Create question error", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getQuestion(@RequestParam String attemptId) {
        Assert.notNull(attemptId, "You must specify attemptId");
        try {
            ProgressWrapper<Question> questionProgress = questionService.getNextQuestion(attemptId);
            QuestionRequestResponseDto questionDto = questionMapper.mapToDto(questionProgress);
            GameProgressWrapper<QuestionRequestResponseDto> nextQuestionProgress = new GameProgressWrapper<>(questionProgress, questionDto);
            return ResponseEntity.ok(nextQuestionProgress);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<AnswerResult> submitAnswer(@RequestBody AnswerSubmit submitPayload) {
        AnswerResult answer;
        try {
            answer = questionService.submitAnswer(submitPayload);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(answer);
    }
}
