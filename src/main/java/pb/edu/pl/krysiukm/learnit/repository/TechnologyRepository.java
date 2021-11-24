package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.Technology;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<Technology> findByQuestions_PublishedEquals(@NonNull boolean published);

}
