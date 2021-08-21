package pb.edu.pl.krysiukm.learnit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.edu.pl.krysiukm.learnit.model.Question;
import pb.edu.pl.krysiukm.learnit.model.Technology;
import pb.edu.pl.krysiukm.learnit.repository.QuestionRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestions(Technology technology) {
        return questionRepository.findAllByTechnology(technology);
    }
}
