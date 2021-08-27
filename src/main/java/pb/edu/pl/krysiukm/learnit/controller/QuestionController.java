package pb.edu.pl.krysiukm.learnit.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.dto.AnswerSubmit;
import pb.edu.pl.krysiukm.learnit.dto.QuestionDto;
import pb.edu.pl.krysiukm.learnit.model.AnswerResult;
import pb.edu.pl.krysiukm.learnit.model.Difficulty;
import pb.edu.pl.krysiukm.learnit.model.Question;
import pb.edu.pl.krysiukm.learnit.model.Technology;
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
    public Question createQuestion(@RequestBody QuestionDto questionDto) {

        Question question = modelMapper.map(questionDto, Question.class);

        Difficulty difficulty = difficultyService.getById(questionDto.getDifficultyId())
                .orElseThrow(ResourceNotFoundException::new);
        question.setDifficulty(difficulty);

        Technology technology = technologyService.getById(questionDto.getTechnologyId());

        question.setTechnology(technology);

        return questionService.createQuestion(question);
    }

    @GetMapping
    public ResponseEntity<Question> getQuestion(@RequestParam String attemptId) {
        Assert.notNull(attemptId, "You must specify attemptId");
        try {
            Question question = questionService.getNextQuestion(attemptId);
            return ResponseEntity.ok(question);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<AnswerResult> submitAnswer(AnswerSubmit submitPayload) {

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
