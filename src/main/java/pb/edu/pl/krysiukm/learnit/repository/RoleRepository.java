package pb.edu.pl.krysiukm.learnit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pb.edu.pl.krysiukm.learnit.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
