package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.entity.User;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;
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
    public ResponseEntity<UserAttempt> startAttempt(@RequestParam Long technologyId) {
        // TODO: get user from security context
        User user = userRepository.findAll().iterator().next();
        UserAttempt attempt = userAttemptService.startAttempt(user, technologyId);
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
