package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;

@Repository
public interface TechnologyRepository extends JpaRepository<TechnologyEntity, Long> {
}
