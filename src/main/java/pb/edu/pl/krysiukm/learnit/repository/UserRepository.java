package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb.edu.pl.krysiukm.learnit.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
