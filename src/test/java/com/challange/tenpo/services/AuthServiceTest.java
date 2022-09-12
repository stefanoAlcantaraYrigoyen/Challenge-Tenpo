package com.challange.tenpo.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    private static final String USERNAME = "mandres";
    private static final String PASSWORD = "1234";

    private AuthService service;
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        UserLogedCache cache = mock(UserLogedCache.class);
        authenticationManager = mock(AuthenticationManager.class);
        service = new AuthService(authenticationManager, cache);
    }

    /*
    CHANGE MOCK
    @Test
    public void GivenAuthenticate_WhenIsTrue_ShouldReturnIsAuthenticatedTrue() {
        // Arrange
        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        // Act
        AccessTokenDTO authentication = service.authenticate(USERNAME, PASSWORD);

        // Assert
        assertNotNull(authentication.getToken());
    }
    */

    @Test
    public void GivenAuthenticate_WhenBadCredentialsException_ShouldReturnCredentialsAreNotValid() {
        // Arrange
        when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);

        // Act
        Exception exception = assertThrows(BadCredentialsException.class, () -> service.authenticate(USERNAME, PASSWORD));

        // Assert
        Assertions.assertTrue(exception instanceof BadCredentialsException);
        assertEquals(exception.getMessage(), "Credentials are not valid");
    }

    @Test
    public void GivenAuthenticate_WhenDisabledException_ShouldReturnUserIsNotActive() {
        // Arrange
        when(authenticationManager.authenticate(any())).thenThrow(DisabledException.class);

        // Act
        Exception exception = assertThrows(DisabledException.class, () -> service.authenticate(USERNAME, PASSWORD));

        // Assert
        Assertions.assertTrue(exception instanceof DisabledException);
        assertEquals(exception.getMessage(), "User is not active");
    }

}
