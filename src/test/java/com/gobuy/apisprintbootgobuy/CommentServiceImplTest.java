package com.gobuy.apisprintbootgobuy;


import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.Role;
import com.gobuy.apisprintbootgobuy.domain.repository.CommentRepository;
import com.gobuy.apisprintbootgobuy.domain.repository.RoleRepository;
import com.gobuy.apisprintbootgobuy.domain.service.CommentService;
import com.gobuy.apisprintbootgobuy.domain.service.RoleService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import com.gobuy.apisprintbootgobuy.service.CommentServiceImpl;
import com.gobuy.apisprintbootgobuy.service.RoleServiceImpl;
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
public class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @TestConfiguration
    static class CommentServiceImplTestConfiguration {
        @Bean
        public CommentService CommentService() {
            return new CommentServiceImpl();
        }
    }

    @Test
    @DisplayName("when GetCommentById With Valid Id Then Returns Comment")
    public void whenGetCommentByIdWithValidIdThenReturnsComment() {
        //Arrange
        Long id = 1L;
        Comment comment = new Comment();
        comment.setId(id);
        when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));
        //Act
        Comment foundCommemt = commentService.getCommentById(id);
        //Assert
        assertThat(foundCommemt.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetCommentById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetCommentByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 2L;
        String template = "Resource %s not found for %s with value %s";
        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Comment", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Comment foundComment = commentService.getCommentById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("when DeleteCommentById With Invalid Id Then ReturnResourceNotFoundExceptions")
    public void whenDeletedCommentByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 10L;
        String template = "Resource %s not found for %s with value %s";
        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Comment", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> deletedComment = commentService.deleteRole(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when UpdateCommentById With Valid Id Then Return UpdatedComment")
    public void whenUpdateCommentByIdWithValidIdThenReturnUpdatedComment() {
        //Arrange
        Long id = 2L;
        Comment comment = new Comment();
        comment.setMessage("Hola");
        comment.setId(id);
        Comment newComment = new Comment();
        newComment.setMessage("Adios");
        newComment.setId(id);

        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));
        when(commentRepository.save(newComment)).thenReturn(newComment);
        //Act
        Comment foundRole = commentService.updateComment(id, newComment);
        //Assert
        assertThat(comment).isNotEqualTo(foundRole);
    }

    @Test
    @DisplayName("when UpdateCommentById With Invalid Id Then Return ResourceNotFoundExceptions")
    public void whenUpdateCommentByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 14L;
        Comment comment = new Comment();
        String template = "Resource %s not found for %s with value %s";
        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Comment", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Comment updatedComment = commentService.updateComment(id, comment);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
