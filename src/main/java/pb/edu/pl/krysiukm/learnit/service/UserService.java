package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.UserAlreadyExistException;
import pb.edu.pl.krysiukm.learnit.dto.UserDto;
import pb.edu.pl.krysiukm.learnit.entity.User;
import pb.edu.pl.krysiukm.learnit.repository.RoleRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
//        user.setUsing2FA(accountDto.isUsing2FA());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}