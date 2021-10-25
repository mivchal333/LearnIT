package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.dto.Technology;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.service.FileResolver;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/technology")
@RestController
public class TechnologyController {
    private final TechnologyService technologyService;
    private final FileResolver fileResolver;

    @PostMapping
    public Technology create(@RequestBody TechnologyEntity technologyEntity) {
        TechnologyEntity entity = technologyService.create(technologyEntity);
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
        return Technology.builder()
                .name(technology.getName())
                .description(technology.getDescription())
                .image(fileResolver.resolveFile(technology.getImage()))
                .build();
    }
}
