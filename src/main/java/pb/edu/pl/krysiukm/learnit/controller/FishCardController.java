package pb.edu.pl.krysiukm.learnit.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pb.edu.pl.krysiukm.learnit.dto.FishCardDto;
import pb.edu.pl.krysiukm.learnit.dto.FishCardMapper;
import pb.edu.pl.krysiukm.learnit.dto.GameProgressWrapper;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;
import pb.edu.pl.krysiukm.learnit.service.exception.NoMoreQuestionsException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/fishcard")
public class FishCardController {
    private final QuestionService questionService;
    private final FishCardMapper fishCardMapper;

    @GetMapping
    public ResponseEntity<?> getFishCard(@RequestParam String attemptId) {
        Assert.notNull(attemptId, "You must specify attemptId");

        try {
            ProgressWrapper<Question> nextQuestionProgress = questionService.getNextQuestion(attemptId);
            FishCardDto fishCardDto = fishCardMapper.mapToDto(nextQuestionProgress);
            GameProgressWrapper<FishCardDto> fishCardDtoProgress = new GameProgressWrapper<>(nextQuestionProgress, fishCardDto);

            return ResponseEntity.ok(fishCardDtoProgress);
        } catch (NoMoreQuestionsException e) {
            log.warn("[GET_CARD_WARNING] No next card found.");
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            log.error("[GET_CARD_ERROR] Unable to get next card.", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
