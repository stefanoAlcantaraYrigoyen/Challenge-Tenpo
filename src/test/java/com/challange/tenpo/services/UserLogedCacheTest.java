package com.challange.tenpo.services;

import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserLogedCacheTest {

    private static final String TOKEN = "token";
    private static final String USERNAME = "mandres";
    private UserLogedCache service;
    private LoadingCache<String, String> cache;

    @BeforeEach
    public void setUp() {
        service = new UserLogedCache();
        cache = service.getCache();
    }

    @Test
    public void GivenAddUser_WhenAddCache_ShouldAddUserTokenToCache() throws ExecutionException {
        // Arrange
        // Act
        service.addUser(TOKEN, USERNAME);

        // Assert
        assertEquals(USERNAME, cache.get(TOKEN));
    }

    @Test
    public void GivenRemoveUser_WhenRemoveCache_ShouldRemoveTokenInCache() throws ExecutionException {
        // Arrange
        cache.put(TOKEN, USERNAME);

        // Act
        service.removeUser(TOKEN);

        // Assert
        assertEquals("", cache.get(TOKEN));
    }

    @Test
    public void GivenIsUserOnCacheTrue_WhenUserIsInCache_ShouldReturnTrue() throws ExecutionException {
        // Arrange
        cache.put(TOKEN, USERNAME);

        // Act
        boolean result = service.isUserOnCache(TOKEN);

        // Assert
        assertTrue(result);
    }

    @Test
    public void GivenIsUserOnCacheFalse_WhenUserIsNotInCache_ShouldReturnFalse() throws ExecutionException {
        // Arrange
        // Act
        boolean result = service.isUserOnCache(TOKEN);

        // Assert
        assertFalse(result);
    }

}
