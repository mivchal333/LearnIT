package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.edu.pl.krysiukm.learnit.dto.UserAttemptDto;
import pb.edu.pl.krysiukm.learnit.dto.UserAttemptMapper;
import pb.edu.pl.krysiukm.learnit.entity.UserAccount;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserRepository;
import pb.edu.pl.krysiukm.learnit.service.UserAttemptService;
import pb.edu.pl.krysiukm.learnit.service.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserAttemptById(@PathVariable String id) {

        try {
            UserAttempt userAttempt = userAttemptService.getUserAttempt(id);
            UserAttemptDto userAttemptDto = userAttemptMapper.mapToDto(userAttempt);
            return ResponseEntity.ok(userAttemptDto);
        } catch (NotFoundException e) {
            log.error("[UserHistoryController] Not found user attempt");
            return ResponseEntity.badRequest().build();
        }
    }
}
