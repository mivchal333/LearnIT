package pb.edu.pl.krysiukm.learnit.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.dto.QuestionCreateRequestDto;
import pb.edu.pl.krysiukm.learnit.dto.QuestionRequestResponseDto;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.AnswerResult;
import pb.edu.pl.krysiukm.learnit.service.DifficultyService;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final DifficultyService difficultyService;
    private final TechnologyService technologyService;
    private final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody QuestionCreateRequestDto questionResponseDto) {
        try {
            Question question = questionService.createQuestion(questionResponseDto);
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getQuestion(@RequestParam String attemptId) {
        Assert.notNull(attemptId, "You must specify attemptId");
        try {
            QuestionRequestResponseDto question = questionService.getNextQuestion(attemptId);
            return ResponseEntity.ok(question);
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
