package pb.edu.pl.krysiukm.learnit.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.repository.RoleRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);


    UserService sut = new UserService(
            userRepository,
            roleRepository,
            passwordEncoder
    );

    @Test
    void shouldGetUserAccount() {
        when(userRepository.findByEmail("email"))
                .thenReturn(Optional.empty());

        Optional<UserAccount> found = sut.getUserAccount("email");

        assertTrue(found.isEmpty());
    }
}