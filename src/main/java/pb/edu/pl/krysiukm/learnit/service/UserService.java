package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.controller.exception.UserAlreadyExistException;
import pb.edu.pl.krysiukm.learnit.dto.UserDto;
import pb.edu.pl.krysiukm.learnit.entity.Role;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.repository.RoleRepository;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;
import pb.edu.pl.krysiukm.learnit.security.AppRole;
import pb.edu.pl.krysiukm.learnit.service.exception.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Role userRole = roleRepository.findByName(AppRole.ROLE_USER.name())
                .orElseThrow(() -> new ResourceNotFoundException("Not found USER role!"));
        userAccount.setRoles(List.of(userRole));
        return userRepository.save(userAccount);
    }

    public Optional<UserAccount> getUserAccount(String email) {
        return userRepository.findByEmail(email);
    }


    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public List<UserAccount> findAll() {
        return userRepository.findAll();
    }

    public void revokeRole(String email, AppRole role) {
        Optional<UserAccount> userOpt = userRepository.findByEmail(email);
        UserAccount userAccount = userOpt.orElseThrow(() -> new NotFoundException("Not found user with that email"));

        List<Role> newRoles = userAccount.getRoles()
                .stream()
                .filter(role1 -> !role1.getName().equals(role.name()))
                .collect(Collectors.toList());
        userAccount.setRoles(newRoles);
    }

    public void grantRole(String userEmail, AppRole roleToGrant) {
        Optional<UserAccount> userOpt = userRepository.findByEmail(userEmail);
        UserAccount userAccount = userOpt.orElseThrow(() -> new NotFoundException("Not found user with that email"));

        Role role = roleRepository.findByName(roleToGrant.name())
                .orElseThrow(() -> new ResourceNotFoundException("Not found role to grant!"));

        userAccount.getRoles().add(role);
    }

    public void addPoints(String email, int difficulty) {
        UserAccount userAccount = userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);

        long oldPoints = userAccount.getPoints();
        long newPoints = oldPoints + difficulty;

        userAccount.setPoints(newPoints);
    }
}