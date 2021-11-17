package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.TechnologyEntity;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.util.List;

@Repository
public interface UserAttemptRepository extends CrudRepository<UserAttempt, String> {
    List<UserAttempt> findAllByUserAccount(UserAccount userAccount);

    List<UserAttempt> findAllByUserAccountAndTechnologyEntityOrderByStartDateDesc(UserAccount userAccount, TechnologyEntity technologyEntity);

    void deleteAllByTechnologyEntityId(Long id);

    List<UserAttempt> findAllByUserAccountOrderByStartDateDesc(UserAccount userAccount);

}
