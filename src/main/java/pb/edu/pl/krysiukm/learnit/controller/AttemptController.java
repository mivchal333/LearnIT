package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;
import pb.edu.pl.krysiukm.learnit.service.UserAttemptService;
import pb.edu.pl.krysiukm.learnit.service.UserService;
import pb.edu.pl.krysiukm.learnit.service.exception.NotFoundException;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/attempt")
public class AttemptController {
    private final UserAttemptService userAttemptService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserAttempt> startAttempt(@RequestParam("technologyId") List<Long> technologyIds,
                                                    @AuthenticationPrincipal User user) {
        String email = user.getUsername();
        UserAccount userAccount = userService.getUserAccount(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found."));

        UserAttempt attempt = userAttemptService.startQuizAttempt(userAccount, technologyIds);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping
    public ResponseEntity<List<UserAttempt>> getUserActiveAttempts(
            @AuthenticationPrincipal User user) {
        String email = user.getUsername();
        UserAccount userAccount = userService.getUserAccount(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found."));


        List<UserAttempt> attempts = userAttemptService.getUserAttempts(userAccount);

        return ResponseEntity.ok(attempts);
    }
}
