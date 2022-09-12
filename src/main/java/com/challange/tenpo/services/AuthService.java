package com.challange.tenpo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.challange.tenpo.dtos.AccessTokenDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import static com.challange.tenpo.config.Consts.*;

@Service
public class AuthService {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UserLogedCache cache;

    @Autowired
    private Environment env;
    
    public AuthService(AuthenticationManager authenticationManager, UserLogedCache cache) {
		super();
		this.authenticationManager = authenticationManager;
		this.cache = cache;
	}

	public AccessTokenDTO authenticate(String username, String password) {
        try {
            return generateAccessToken(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)));
        } catch (DisabledException e) {
            log.info("[Log] Username {} is not active", username);
            throw new DisabledException(USER_IS_NOT_ACTIVE_EXCEPTION);
        } catch (BadCredentialsException e) {
            log.info("[Log] Bad Credentials: username {} , password {}", username, password);
            throw new BadCredentialsException(CREDENTIALS_ARE_NOT_VALID_EXCEPTION);
        }
    }

    private AccessTokenDTO generateAccessToken(Authentication auth) {
        User user = (User) auth.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty(SECRET_KEY).getBytes());
        long startTime = System.currentTimeMillis();
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(startTime))
                .withExpiresAt(new Date(startTime + ACCESS_TOKEN_EXPIRATION))
                .withClaim(ROLES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        log.info("[Log] Successful - Username {} loading in cache", user.getUsername());
        cache.addUser(access_token, user.getUsername());
        return new AccessTokenDTO(access_token);

    }

    public Map<String, String> invalidateAccessToken(String authHeader) {
        String token = authHeader.substring(TOKEN.length());
        String username = getUsernameFromToken(token);
        if (cache.isUserOnCache(token)) {
            log.info("[Log] Successful - {} disconnected and removing data from cache", username);
            cache.removeUser(token);
        }
        Map<String, String> logoutMsg = new HashMap<>();
        logoutMsg.put("message", String.format("User: %s logged out successfully.", username));
        return logoutMsg;
    }

    private String getUsernameFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty(SECRET_KEY).getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

}
