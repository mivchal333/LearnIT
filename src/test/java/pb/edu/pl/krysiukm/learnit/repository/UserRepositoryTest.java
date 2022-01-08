package pb.edu.pl.krysiukm.learnit.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository sut;

    @Test
    @Sql("createUser.sql")
    void findByEmail() {

        Optional<UserAccount> foundOpt = sut.findByEmail(DataFixture.USER_1_EMAIL);

        assertTrue(foundOpt.isPresent());
        assertEquals(DataFixture.USER_1_EMAIL, foundOpt.get().getEmail());
    }

    @Test
    @Sql("createUser.sql")
    void deleteUserAccount() {
        UserAccount userAccount = sut.findByEmail(DataFixture.USER_1_EMAIL).get();

        sut.delete(userAccount);

        assertTrue(sut.findByEmail(DataFixture.USER_1_EMAIL).isEmpty());
    }
}