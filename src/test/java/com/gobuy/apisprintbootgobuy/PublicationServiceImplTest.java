package com.gobuy.apisprintbootgobuy;

import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.Publication;
import com.gobuy.apisprintbootgobuy.domain.repository.PublicationRepository;
import com.gobuy.apisprintbootgobuy.domain.service.PublicationService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import com.gobuy.apisprintbootgobuy.service.PublicationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PublicationServiceImplTest {
    @MockBean
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationService publicationService;

    @TestConfiguration
    static class PublicationServiceImplTestConfiguration {
        @Bean
        public PublicationService PublicationService() {
            return new PublicationServiceImpl();
        }
    }

    @Test
    @DisplayName("when GetPublicationById With Valid Id Then Returns Publication")
    public void whenGetPublicationByIdWithValidIdThenReturnsPublication() {
        //Arrange
        Long id = 1L;
        Publication publication = new Publication();
        publication.setId(id);
        when(publicationRepository.findById(id))
                .thenReturn(Optional.of(publication));
        //Act
        Publication foundPublication = publicationService.getPublicationById(id);
        //Assert
        assertThat(foundPublication.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetPublicationById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetPublicationByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 2L;
        String template = "Resource %s not found for %s with value %s";
        when(publicationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Publication", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Publication foundPublication = publicationService.getPublicationById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when DeletePublicationById With Invalid Id Then ReturnResourceNotFoundExceptions")
    public void whenDeletedPublicationByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 10L;
        String template = "Resource %s not found for %s with value %s";
        when(publicationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Publication", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> deletedPublication = publicationService.deletePublication(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when UpdatePublicationById With Valid Id Then Return UpdatedPublication")
    public void whenUpdatePublicationByIdWithValidIdThenReturnUpdatedPublication() {
        //Arrange
        Long id = 2L;
        Publication publication = new Publication();
        publication.setDescription("Publiacion de Juan");
        publication.setPhoto("img.jpg");
        publication.setId(id);
        Publication newPublication = new Publication();
        newPublication.setPhoto("img.png");
        newPublication.setDescription("Publiacion modifica de Juan");
        newPublication.setId(id);

        when(publicationRepository.findById(id)).thenReturn(Optional.of(publication));
        when(publicationRepository.save(newPublication)).thenReturn(newPublication);
        //Act
        Publication foundPublication = publicationService.updatePublication(id, newPublication);
        //Assert
        assertThat(publication).isNotEqualTo(foundPublication);
    }

    @Test
    @DisplayName("when UpdatePublicationById With Invalid Id Then Return ResourceNotFoundExceptions")
    public void whenUpdatePublicationByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 14L;
        Publication publication = new Publication();
        String template = "Resource %s not found for %s with value %s";
        when(publicationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Publication", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Publication updatedPublication = publicationService.updatePublication(id, publication);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }




}
