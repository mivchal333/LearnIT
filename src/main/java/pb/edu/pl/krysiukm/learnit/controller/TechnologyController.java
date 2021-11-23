package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.dto.TechnologyDto;
import pb.edu.pl.krysiukm.learnit.entity.Technology;
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public TechnologyDto create(@RequestBody Technology technology) {
        Technology entity = technologyService.create(technology);
        return mapToTechnology(entity);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public TechnologyDto create(@PathVariable Long id,
                                @RequestBody Technology technology) {
        Technology entity = technologyService.update(id, technology);

        return mapToTechnology(entity);
    }

    @GetMapping
    public List<TechnologyDto> getAllTechnologies() {
        return technologyService.getAll()
                .stream()
                .map(this::mapToTechnology)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TechnologyDto getTechnology(@PathVariable Long id) {
        Technology entity = technologyService.getById(id);
        return mapToTechnology(entity);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        technologyService.remove(id);
        return ResponseEntity.noContent().build();
    }

    private TechnologyDto mapToTechnology(Technology technology) {

        TechnologyDto.TechnologyDtoBuilder builder = TechnologyDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .description(technology.getDescription())
                .questionCount(technology.getQuestions().size());

        Optional.ofNullable(technology.getImage())
                .ifPresent((image) -> builder.image(fileResolver.resolveFile(image)));

        return builder.build();
    }
}
