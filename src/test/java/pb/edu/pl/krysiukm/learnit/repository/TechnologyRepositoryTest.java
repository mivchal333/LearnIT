package pb.edu.pl.krysiukm.learnit.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pb.edu.pl.krysiukm.learnit.entity.Answer;
import pb.edu.pl.krysiukm.learnit.entity.Question;
import pb.edu.pl.krysiukm.learnit.entity.Technology;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class TechnologyRepositoryTest {

    @Autowired
    TechnologyRepository technologyRepository;

    Technology ANY_TECHNOLOGY;
    Answer ANY_ANSWER;
    Question ANY_QUESTION;

    @BeforeEach
    void setUp() {
        ANY_TECHNOLOGY = new Technology("name", "desc", "image.jpg", Collections.emptyList());
        ANY_ANSWER = new Answer("body", "code");

        ANY_QUESTION = new Question("body", "code", "codeaLang", ANY_ANSWER, null, null, 1, Collections.emptyList());
        ANY_QUESTION.setCorrectAnswer(ANY_ANSWER);
        ANY_QUESTION.setTechnology(ANY_TECHNOLOGY);

        ANY_TECHNOLOGY.setQuestions(Collections.singletonList(ANY_QUESTION));
        technologyRepository.save(ANY_TECHNOLOGY);
    }

    @Test
    void shouldFetchQuestionsWithTechnology() {

        // when
        Optional<Technology> result = technologyRepository.findById(1L);

        // then
        assertTrue(result.isPresent());
        assertFalse(result.get().getQuestions().isEmpty());
    }
}