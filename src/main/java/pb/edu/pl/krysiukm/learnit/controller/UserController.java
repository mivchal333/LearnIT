package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.controller.exception.UserAlreadyExistException;
import pb.edu.pl.krysiukm.learnit.dto.UserAccountDetailsDto;
import pb.edu.pl.krysiukm.learnit.dto.UserDto;
import pb.edu.pl.krysiukm.learnit.entity.Role;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUserAccount(@Valid @RequestBody UserDto userDto) {
        try {
            UserAccount registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            return ResponseEntity.badRequest().body("An account for that username/email already exists.");
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myAccount")
    public ResponseEntity getLoggedUserDetails(@AuthenticationPrincipal User user) {

        Optional<UserAccount> userAccountOpt = userService.getUserAccount(user.getUsername());
        UserAccount userAccount = userAccountOpt.orElseThrow(() -> new RuntimeException("User not found"));


        List<String> roles = userAccount.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        UserAccountDetailsDto accountDetailsDto = UserAccountDetailsDto.builder()
                .email(userAccount.getEmail())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .roles(roles)
                .build();
        return ResponseEntity.ok(accountDetailsDto);
    }
}
