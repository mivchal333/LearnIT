package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;

    public Optional<Technology> getById(Long id) {
        return technologyRepository.findById(id);
    }

    public Technology create(Technology technology) {
        return technologyRepository.save(technology);
    }
}
