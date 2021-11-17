package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.UserAlreadyExistException;
import pb.edu.pl.krysiukm.learnit.dto.UserDto;
import pb.edu.pl.krysiukm.learnit.entity.Role;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.repository.RoleRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;
import pb.edu.pl.krysiukm.learnit.security.AppRoles;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccount registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final UserAccount userAccount = new UserAccount();

        userAccount.setFirstName(accountDto.getFirstName());
        userAccount.setLastName(accountDto.getLastName());
        userAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        userAccount.setEmail(accountDto.getEmail());
        List<Role> userRoles = List.of(roleRepository.findByName(AppRoles.ROLE_USER));
        userAccount.setRoles(userRoles);
        return userRepository.save(userAccount);
    }

    public Optional<UserAccount> getUserAccount(String email) {
        return userRepository.findByEmail(email);
    }


    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}