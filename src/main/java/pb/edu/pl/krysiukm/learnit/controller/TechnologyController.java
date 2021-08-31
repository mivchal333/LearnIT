package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/technology")
@RestController
public class TechnologyController {
    private final TechnologyService technologyService;

    @PostMapping
    public Technology create(@RequestBody Technology technology) {
        return technologyService.create(technology);
    }

    @GetMapping
    public List<Technology> getAllTechnologies() {
        return technologyService.getAll();
    }

    @GetMapping("/{id}")
    public Technology getTechnology(@PathVariable Long id) {
        return technologyService.getById(id);
    }
}
