package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pb.edu.pl.krysiukm.learnit.model.User;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;
import pb.edu.pl.krysiukm.learnit.service.UserAttemptService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attempt")
public class AttemptController {
    private final UserAttemptService userAttemptService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserAttempt> startAttempt() {
        // TODO: get user from security context
        User user = userRepository.findAll().iterator().next();
        UserAttempt attempt = userAttemptService.startAttempt(user);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping
    public ResponseEntity<List<UserAttempt>> getUserActiveAttempts() {
        // TODO: get user from security context
        User user = userRepository.findAll().iterator().next();

        List<UserAttempt> attempts = userAttemptService.getUserAttempts(user);

        return ResponseEntity.ok(attempts);
    }
}
