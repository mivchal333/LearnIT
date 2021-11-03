package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final UserAttemptRepository userAttemptRepository;


    public TechnologyEntity getById(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Technology with id: %s not found.", id)));
    }

    public TechnologyEntity create(TechnologyEntity technologyEntity) {
        return technologyRepository.save(technologyEntity);
    }

    public List<TechnologyEntity> getAll() {
        return technologyRepository.findAll();
    }

    public void remove(Long id) {
        userAttemptRepository.deleteAllByTechnologyEntityId(id);
        technologyRepository.deleteById(id);
    }
}
