package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import pb.edu.pl.krysiukm.learnit.entity.ShowedQuestion;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.util.Optional;

public interface ShowedQuestionRepository extends CrudRepository<ShowedQuestion, Long> {

    Optional<ShowedQuestion> findByUserAttempt(UserAttempt userAttempt);

}
