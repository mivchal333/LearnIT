package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.Technology;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    public Technology getById(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Technology with id: %s not found.", id)));
    }

    public Technology create(Technology technology) {
        return technologyRepository.save(technology);
    }

    public List<Technology> getAll() {
        return technologyRepository.findAll();
    }
}
