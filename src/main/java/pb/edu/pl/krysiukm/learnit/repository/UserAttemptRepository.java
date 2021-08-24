package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;

import java.util.List;

@Repository
public interface UserAttemptRepository extends CrudRepository<UserAttempt, String> {

}
