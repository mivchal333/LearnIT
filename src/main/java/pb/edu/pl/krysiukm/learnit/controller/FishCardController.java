package pb.edu.pl.krysiukm.learnit.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pb.edu.pl.krysiukm.learnit.dto.FishCardDto;
import pb.edu.pl.krysiukm.learnit.dto.FishCardMapper;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fishcard")
public class FishCardController {
    private final QuestionService questionService;
    private final FishCardMapper fishCardMapper;

    @GetMapping
    public ResponseEntity<?> getFishCard(@RequestParam String attemptId) {
        Assert.notNull(attemptId, "You must specify attemptId");

        try {
            Question question = questionService.getNextQuestion(attemptId);
            FishCardDto fishCardDto = fishCardMapper.mapToDto(question);
            return ResponseEntity.ok(fishCardDto);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
