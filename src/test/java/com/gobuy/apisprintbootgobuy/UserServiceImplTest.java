package com.gobuy.apisprintbootgobuy;

import com.gobuy.apisprintbootgobuy.domain.model.User;
import com.gobuy.apisprintbootgobuy.domain.repository.UserRepository;
import com.gobuy.apisprintbootgobuy.domain.service.UserService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import com.gobuy.apisprintbootgobuy.service.UserServiceImpl;
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
public class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService roleService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("when GetUserById With Valid Id Then Returns User")
    public void whenGetUserByIdWithValidIdThenReturnsUser() {
        //Arrange
        Long id = 1L;
        User user = new User();
        user.setId(id);
        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        //Act
        User foundUser = userService.getUserById(id);
        //Assert
        assertThat(foundUser.getId()).isEqualTo(id);
    }

}
