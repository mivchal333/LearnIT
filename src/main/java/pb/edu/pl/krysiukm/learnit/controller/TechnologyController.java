package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.dto.Technology;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.service.FileResolver;
import pb.edu.pl.krysiukm.learnit.service.QuestionService;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/technology")
@RestController
public class TechnologyController {
    private final TechnologyService technologyService;
    private final FileResolver fileResolver;
    private final QuestionService questionService;

    @PostMapping
    public Technology create(@RequestBody TechnologyEntity technologyEntity) {
        TechnologyEntity entity = technologyService.create(technologyEntity);
        return mapToTechnology(entity);
    }

    @PutMapping("/{id}")
    public Technology create(@PathVariable Long id,
                             @RequestBody TechnologyEntity technologyEntity) {
        TechnologyEntity entity = technologyService.update(id, technologyEntity);

        return mapToTechnology(entity);
    }

    @GetMapping
    public List<Technology> getAllTechnologies() {
        return technologyService.getAll()
                .stream()
                .map(this::mapToTechnology)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Technology getTechnology(@PathVariable Long id) {
        TechnologyEntity entity = technologyService.getById(id);
        return mapToTechnology(entity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        technologyService.remove(id);
        return ResponseEntity.noContent().build();
    }

    private Technology mapToTechnology(TechnologyEntity technology) {
        long questionsCount = questionService.countQuestions(technology.getId());

        Technology.TechnologyBuilder builder = Technology.builder()
                .id(technology.getId())
                .name(technology.getName())
                .description(technology.getDescription())
                .questionCount(questionsCount);

        Optional.ofNullable(technology.getImage())
                .ifPresent((image) -> builder.image(fileResolver.resolveFile(image)));

        return builder.build();
    }
}
