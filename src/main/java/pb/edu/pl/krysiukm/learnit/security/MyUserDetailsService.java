package pb.edu.pl.krysiukm.learnit.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.edu.pl.krysiukm.learnit.entity.Role;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<UserAccount> userAccountOpt = userRepository.findByEmail(email);

        UserAccount userAccount = userAccountOpt
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + email));

        return new org.springframework.security.core.userdetails.User(
                userAccount.getEmail(),
                userAccount.getPassword(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities(userAccount.getRoles()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> userRoles) {
        return userRoles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
