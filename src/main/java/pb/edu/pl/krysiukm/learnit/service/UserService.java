package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.UserAlreadyExistException;
import pb.edu.pl.krysiukm.learnit.dto.UserDto;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.repository.RoleRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final UserAccount userAccount = new UserAccount();

        userAccount.setFirstName(accountDto.getFirstName());
        userAccount.setLastName(accountDto.getLastName());
        userAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        userAccount.setEmail(accountDto.getEmail());
//        user.setUsing2FA(accountDto.isUsing2FA());
        userAccount.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> getUserAccount(String email) {
        return userRepository.findByEmail(email);
    }


    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}