package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.model.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
