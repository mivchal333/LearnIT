package pb.edu.pl.krysiukm.learnit.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pb.edu.pl.krysiukm.learnit.entity.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
public class RoleRepositoryTestIT {

    @Autowired
    RoleRepository sut;

    private final String ROLE_1 = "ROLE_1";
    private final String ROLE_2 = "ROLE_2";

    @BeforeEach
    public void initData() {
        sut.save(new Role(ROLE_1));
        sut.save(new Role(ROLE_2));
    }

    @Test
    void shouldReadRole() {
        Role found = sut.findByName(ROLE_1);

        assertEquals(ROLE_1, found.getName());
    }

    @Test
    void shouldDeleteRole() {
        Role role1 = sut.findByName(ROLE_1);
        sut.delete(role1);

        assertNull(sut.findByName(ROLE_1));
    }
}
