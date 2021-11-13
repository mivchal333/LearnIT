package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;
import pb.edu.pl.krysiukm.learnit.repository.ShowedQuestionRepository;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;
import pb.edu.pl.krysiukm.learnit.service.exception.FileDeleteException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final UserAttemptRepository userAttemptRepository;
    private final QuestionRepository questionRepository;
    private final FilesStorageService storageService;
    private final ShowedQuestionRepository showedQuestionRepository;

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

        try {
            Optional.ofNullable(entity.getImage())
                    .ifPresent(storageService::deleteFile);
        } catch (FileDeleteException e) {
            log.error("[TechnologyService] Failed to delete image. Skipping...", e);
        }

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

        if (isNewImage(technologyEntity, oldImage)) {
            oldTechnologyEntity.setImage(technologyEntity.getImage());
            storageService.deleteFile(oldImage);
        }

        return technologyRepository.save(oldTechnologyEntity);
    }

    private boolean isNewImage(TechnologyEntity technologyEntity, @Nullable String oldImage) {
        return Optional.ofNullable(oldImage)
                .map(image -> !image.equals(technologyEntity.getImage()))
                .orElse(false);

    }
}
