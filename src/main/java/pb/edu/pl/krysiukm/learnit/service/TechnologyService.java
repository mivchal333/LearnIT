package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final UserAttemptRepository userAttemptRepository;
    private final QuestionRepository questionRepository;
    private final FilesStorageService storageService;

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

    @Transactional
    public void remove(Long id) {
        TechnologyEntity entity = technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found technology with id: " + id));
        userAttemptRepository.deleteAllByTechnologyEntityId(id);
        questionRepository.deleteAllByTechnologyEntityId(id);
        Optional.ofNullable(entity.getImage())
                .ifPresent(storageService::deleteFile);
        technologyRepository.deleteById(id);
    }

    public TechnologyEntity update(Long id, TechnologyEntity technologyEntity) {

        TechnologyEntity oldTechnologyEntity = technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found technology with id: " + id));

        if (!technologyEntity.getName().equals(oldTechnologyEntity.getName())) {
            oldTechnologyEntity.setName(technologyEntity.getName());
        }

        if (!technologyEntity.getDescription().equals(oldTechnologyEntity.getDescription())) {
            oldTechnologyEntity.setDescription(technologyEntity.getDescription());
        }

        String oldImage = oldTechnologyEntity.getImage();
        if (!technologyEntity.getImage().equals(oldImage)) {
            oldTechnologyEntity.setImage(technologyEntity.getImage());
            storageService.deleteFile(oldImage);
        }

        return technologyRepository.save(oldTechnologyEntity);
    }
}
