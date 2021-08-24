package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.Difficulty;
import pb.edu.pl.krysiukm.learnit.repository.DifficultyRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;

    public Optional<Difficulty> getById(Long id) {
        return difficultyRepository.findById(id);
    }

    public Difficulty create(Difficulty difficulty) {
        return difficultyRepository.save(difficulty);
    }
}
