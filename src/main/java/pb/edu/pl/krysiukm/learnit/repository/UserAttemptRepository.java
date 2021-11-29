package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.entity.Technology;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserAttemptRepository extends CrudRepository<UserAttempt, String> {
    List<UserAttempt> findAllByUserAccount(UserAccount userAccount);

    List<UserAttempt> findAllByUserAccountAndTechnologiesInOrderByStartDateDesc(UserAccount userAccount, List<Technology> technologies);

    List<UserAttempt> findAllByUserAccountOrderByStartDateDesc(UserAccount userAccount);

    List<UserAttempt> findAllByUserAccountAndStartDateAfterOrderByStartDateDesc(UserAccount userAccount, Instant date);

}
