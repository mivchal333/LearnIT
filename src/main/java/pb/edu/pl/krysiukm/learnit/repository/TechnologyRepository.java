package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import pb.edu.pl.krysiukm.learnit.model.Technology;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {
}
