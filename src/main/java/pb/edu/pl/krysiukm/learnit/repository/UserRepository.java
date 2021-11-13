package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pb.edu.pl.krysiukm.learnit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}