package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pb.edu.pl.krysiukm.learnit.dto.UserAttemptDto;
import pb.edu.pl.krysiukm.learnit.dto.UserAttemptMapper;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;
import pb.edu.pl.krysiukm.learnit.service.UserAttemptService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class UserHistoryController {
    private final UserAttemptService userAttemptService;
    private final UserRepository userRepository;
    private final UserAttemptMapper userAttemptMapper;

    @GetMapping
    public ResponseEntity<?> getUserHistoryByTechnology(@RequestParam Long technologyId) {
        UserAccount userAccount = userRepository.findAll().iterator().next();

        List<UserAttemptDto> userHistory = userAttemptService.getUserHistory(userAccount, technologyId)
                .stream()
                .map(userAttemptMapper::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userHistory);
    }
}
