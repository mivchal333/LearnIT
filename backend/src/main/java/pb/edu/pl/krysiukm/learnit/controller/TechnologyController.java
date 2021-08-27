package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.service.TechnologyService;

@RequiredArgsConstructor
@RequestMapping("/technology")
@RestController
public class TechnologyController {
    private final TechnologyService technologyService;

    @PostMapping
    public Technology create(@RequestBody Technology technology) {
        return technologyService.create(technology);
    }
}
