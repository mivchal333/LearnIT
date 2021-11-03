package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.Difficulty;
import pb.edu.pl.krysiukm.learnit.repository.DifficultyRepository;

@RequiredArgsConstructor
@Service
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;

    public Difficulty getByValue(Integer id) {
        return difficultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Difficulty with id:%s not found", id)));
    }
}
