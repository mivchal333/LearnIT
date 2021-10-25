package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAllByTechnologyEntityAndIdNotIn(TechnologyEntity technologyEntity, List<Long> questionIds);

    List<Question> findAllByIdNotIn(List<Long> questionIds);

    List<Question> findAllByTechnologyEntity(TechnologyEntity technologyEntity);
}
