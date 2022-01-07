package pb.edu.pl.krysiukm.learnit.service;

import org.junit.jupiter.api.Test;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.UserAttempt;
import pb.edu.pl.krysiukm.learnit.repository.UserAttemptRepository;

import java.time.Clock;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAttemptServiceTest {
    Clock clock = mock(Clock.class);
    UserAttemptRepository userAttemptRepository = mock(UserAttemptRepository.class);
    TechnologyService technologyService = mock(TechnologyService.class);

    UserAttemptService sut = new UserAttemptService(
            clock,
            userAttemptRepository,
            technologyService
    );

    @Test
    void shouldReturnUserAttempt() {
        UserAttempt userAttempt = mock(UserAttempt.class);
        when(userAttemptRepository.findById("id"))
                .thenReturn(Optional.of(userAttempt));

        UserAttempt found = sut.getUserAttempt("id");

        assertNotNull(found);
        assertEquals(userAttempt, found);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        when(userAttemptRepository.findById("id"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getUserAttempt("id");
        });
    }
}