package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.dto.QuestionDto;
import pb.edu.pl.krysiukm.learnit.model.Difficulty;
import pb.edu.pl.krysiukm.learnit.model.Question;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.service.DifficultyService;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

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

        Technology technology = technologyService.getById(questionDto.getTechnologyId())
                .orElseThrow(ResourceNotFoundException::new);
        question.setTechnology(technology);

        return questionService.createQuestion(question);
    }
}
