package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.model.Difficulty;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.service.DifficultyService;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

@RequiredArgsConstructor
@RequestMapping("/difficulty")
@RestController
public class DifficultyController {
    private final DifficultyService difficultyService;

    @PostMapping
    public Difficulty create(@RequestBody Difficulty difficulty) {
        return difficultyService.create(difficulty);
    }

}
