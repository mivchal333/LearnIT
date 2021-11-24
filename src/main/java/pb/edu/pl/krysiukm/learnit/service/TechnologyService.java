package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.entity.Technology;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;
import pb.edu.pl.krysiukm.learnit.repository.ShowedQuestionRepository;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final UserAttemptRepository userAttemptRepository;
    private final QuestionRepository questionRepository;
    private final FilesStorageService storageService;
    private final ShowedQuestionRepository showedQuestionRepository;

    public Technology getById(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Technology with id: %s not found.", id)));
    }

    @PreAuthorize("isAuthenticated()")
    public Technology create(Technology technology) {
        return technologyRepository.save(technology);
    }

    public List<Technology> getAll() {
        return technologyRepository.findAll().stream()
                .peek(technology -> {
                    List<Question> publishedQuestions = technology.getQuestions().stream()
                            .filter(Question::isPublished)
                            .collect(Collectors.toList());
                    technology.setQuestions(publishedQuestions);

                }).collect(Collectors.toList());
    }

    public List<Technology> findTechnologiesByIds(List<Long> ids) {
        return technologyRepository.findAllById(ids);
    }

    public void remove(Long id) {
        Technology entity = technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found technology with id: " + id));
        String image = entity.getImage();

        technologyRepository.deleteById(id);

        try {
            Optional.ofNullable(image)
                    .ifPresent(storageService::deleteFile);
        } catch (Exception e) {
            log.error("[TechnologyService] Failed to delete image. Skipping...", e);
        }
    }

    public Technology update(Long id, Technology technology) {

        Technology oldTechnology = technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found technology with id: " + id));

        if (!technology.getName().equals(oldTechnology.getName())) {
            oldTechnology.setName(technology.getName());
        }

        if (!technology.getDescription().equals(oldTechnology.getDescription())) {
            oldTechnology.setDescription(technology.getDescription());
        }

        String oldImage = oldTechnology.getImage();

        if (isNewImage(technology, oldImage)) {
            oldTechnology.setImage(technology.getImage());
            storageService.deleteFile(oldImage);
        }

        return technologyRepository.save(oldTechnology);
    }

    private boolean isNewImage(Technology technology, @Nullable String oldImage) {
        return Optional.ofNullable(oldImage)
                .map(image -> !image.equals(technology.getImage()))
                .orElse(false);
    }
}
