package pb.edu.pl.krysiukm.learnit.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import pb.edu.pl.krysiukm.learnit.controller.exception.ResourceNotFoundException;
import pb.edu.pl.krysiukm.learnit.entity.Technology;
import pb.edu.pl.krysiukm.learnit.repository.TechnologyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TechnologyServiceTest {
    TechnologyRepository technologyRepository = mock(TechnologyRepository.class);
    FilesStorageService storageService = mock(FilesStorageService.class);

    TechnologyService sut = new TechnologyService(
            technologyRepository,
            storageService
    );

    @Test
    void shouldThrowExceptionWhenTechnologyNotFound() {
        when(technologyRepository.findById(any()))
                .thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> {
            sut.getById(0L);
        });
    }

    @Test
    void shouldGetTechnologyById() {
        Technology mockedTechnology = mock(Technology.class);
        when(technologyRepository.findById(0L))
                .thenReturn(Optional.of(mockedTechnology));

        Technology found = sut.getById(0L);

        assertNotNull(found);
    }

    @Test
    void shouldUpdateTechnologyWithImage() {
        Technology oldTechnology = new Technology(
                "name1",
                "desc1",
                "image1"
        );
        when(technologyRepository.findById(0L))
                .thenReturn(Optional.of(oldTechnology));

        Technology newTechnology = new Technology(
                "name1",
                "desc2",
                "image2"
        );

        sut.update(0L, newTechnology);

        verify(storageService, times(1)).deleteFile("image1");
        verify(technologyRepository).save(argThat(new TechnologyNameMatcher(newTechnology)));
    }

    @AllArgsConstructor
    static class TechnologyNameMatcher implements ArgumentMatcher<Technology> {
        private Technology left;

        @Override
        public boolean matches(Technology technology) {
            return left.getName().equals(technology.getName());
        }
    }
}