package pb.edu.pl.krysiukm.learnit.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.dto.GameProgressWrapper;
import pb.edu.pl.krysiukm.learnit.dto.question.*;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.AnswerResult;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final TechnologyService technologyService;

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody QuestionCreateRequestDto questionResponseDto,
                                            @RequestParam Long technologyId,
                                            @AuthenticationPrincipal User user
    ) {
        try {
            Question question = questionService.createQuestion(technologyId, questionResponseDto, user);
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            log.error("[QuestionController] Create question error", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/attempt")
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


    @GetMapping
    public List<QuestionPreviewDto> getTechnologyQuestions(@RequestParam Long id) {
        return technologyService.getById(id)
                .getQuestions()
                .stream()
                .map(questionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
    @GetMapping("/{id}")
    public Question getQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found question with id " + id));
    }

    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
    @PutMapping("/{id}")
    public Question putQuestion(@PathVariable Long id, @RequestBody QuestionCreateRequestDto questionResponseDto) {
        return questionService.editQuestion(id, questionResponseDto);
    }

    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
    }

    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_MOD')")
    @PostMapping("/{id}/published")
    public void setPublishState(@PathVariable Long id, @RequestBody ChangePublishStatePayload payload) {
        questionService.setPublishState(id, payload.isPublished());
    }
}
