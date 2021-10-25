package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.entity.User;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.util.List;

@Repository
public interface UserAttemptRepository extends CrudRepository<UserAttempt, String> {
    List<UserAttempt> findAllByUser(User user);

    List<UserAttempt> findAllByUserAndTechnologyEntityOrderByStartDateDesc(User user, TechnologyEntity technologyEntity);
}
