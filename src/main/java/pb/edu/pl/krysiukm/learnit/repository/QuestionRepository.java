package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.entity.Technology;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAllByTechnologyAndIdNotInAndPublishedTrue(Technology technology, List<Long> questionIds);

    List<Question> findAllByTechnologyAndPublishedTrue(Technology technology);

    long countAllByTechnologyId(Long technologyId);

    void deleteAllByTechnologyId(Long technologyId);

    @Modifying
    @Query("update Question set published = :published where id = :questionId ")
    void setPublishedStateForQuestionId(Long questionId, boolean published);

}
