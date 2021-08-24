package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.User;
import pb.edu.pl.krysiukm.learnit.model.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

@Service
@RequiredArgsConstructor
public class UserAttemptService {
    private final UserAttemptRepository userAttemptRepository;

    public UserAttempt startAttempt(User user) {
        UserAttempt attempt = new UserAttempt(user);
        return userAttemptRepository.save(attempt);
    }
}
