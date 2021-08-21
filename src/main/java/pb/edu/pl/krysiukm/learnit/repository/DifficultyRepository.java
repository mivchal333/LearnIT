package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import pb.edu.pl.krysiukm.learnit.model.Difficulty;

public interface DifficultyRepository extends CrudRepository<Difficulty, Long> {
}
