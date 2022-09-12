package com.challange.tenpo.services;

import com.challange.tenpo.dtos.UserDTO;
import com.challange.tenpo.entitys.User;
import com.challange.tenpo.exceptions.UserAlreadyExistException;
import com.challange.tenpo.repositories.UserRepository;
import com.challange.tenpo.services.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImplTest {

    private final static String TESTS_USERNAME = "Stefano";
    private final static String TESTS_PASSWORD = "1234";
    private final static String TESTS_MAIL = "stefanoyrigoyen@gmail.com";

    private UserServiceImpl service;
    private UserRepository repository;
    private PasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        repository = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
        service = new UserServiceImpl(repository, encoder);
    }

    @Test
    void GivenLoadUserByUsername_WhenUserFound_ShouldUser() {
        // Arrange
        User user = new User(TESTS_USERNAME, TESTS_PASSWORD, TESTS_MAIL);
        when(repository.findByUsername(TESTS_USERNAME)).thenReturn(user);

        // Act
        UserDetails result = service.loadUserByUsername(TESTS_USERNAME);

        // Assert
        assertEquals(result.getUsername(), TESTS_USERNAME);
        assertEquals(result.getPassword(), TESTS_PASSWORD);

    }

    @Test
    @SuppressWarnings("unused")
    void GivenLoadUserByUsername_WhenUserNotInDB_ShouldReturnUsernameNotFoundException() {
        // Arrange
		User user = new User(TESTS_USERNAME, TESTS_PASSWORD, TESTS_MAIL);
        when(repository.findByUsername(TESTS_USERNAME)).thenReturn(null);

        // Act
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(TESTS_USERNAME));

        // Assert
        assertTrue(exception instanceof UsernameNotFoundException);
        assertEquals(exception.getMessage(), "User not found in database");
    }

    @Test
    void GivenRegisterUser_WhenItsOkInfo_ShouldRegisterOkUser() throws UserAlreadyExistException {
        // Arrange
        User userToSave = new User(TESTS_USERNAME, TESTS_PASSWORD, TESTS_MAIL);
        UserDTO userDTO = new UserDTO(TESTS_USERNAME, TESTS_PASSWORD, TESTS_MAIL);
        when(encoder.encode(TESTS_PASSWORD)).thenReturn(TESTS_PASSWORD);
        when(repository.save(userToSave)).thenReturn(userToSave);

        // Act
        User user = service.registerUser(userDTO);

        // Assert
        assertEquals(user.getUsername(), userToSave.getUsername());
        assertEquals(user.getEmail(), userToSave.getEmail());
    }

    @Test
    void GivenIsUserRegistered_WhenUserExists_ShouldReturnTrue() {
        // Arrange
        User userFound = new User(TESTS_USERNAME, TESTS_PASSWORD, TESTS_MAIL);
        when(repository.findByUsername(TESTS_USERNAME)).thenReturn(userFound);

        // Act
        boolean result = service.isUserRegistered(TESTS_USERNAME);

        // Assert
        assertTrue(result);
    }

    @Test
    void GivenIsUserRegistered_WhenUserNotExists_ShouldReturnFalse() {
        // Arrange
        when(repository.findByUsername(TESTS_USERNAME)).thenReturn(null);

        // Act
        boolean result = service.isUserRegistered(TESTS_USERNAME);

        // Assert
        assertFalse(result);
    }

}
