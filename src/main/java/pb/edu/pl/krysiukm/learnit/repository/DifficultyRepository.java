package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.Difficulty;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
}
