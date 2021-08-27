package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;

@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    public Technology getById(Long id) {
        return technologyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Technology create(Technology technology) {
        return technologyRepository.save(technology);
    }
}
