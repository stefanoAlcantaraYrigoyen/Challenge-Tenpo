package com.challange.tenpo.services.impl;

import com.challange.tenpo.dtos.UserDTO;
import com.challange.tenpo.entitys.User;
import com.challange.tenpo.exceptions.UserAlreadyExistException;
import com.challange.tenpo.repositories.UserRepository;
import com.challange.tenpo.services.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.challange.tenpo.config.Consts.EMAIL_IS_ALREADY_REGISTERED_EXCEPTION;
import static com.challange.tenpo.config.Consts.USER_IS_ALREADY_REGISTERED_EXCEPTION;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            log.error("[Log] User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }
        log.info("[Log] User found in database: {}", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER_ROLE"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User registerUser(UserDTO userDTO) throws UserAlreadyExistException {
        if (isUserRegistered(userDTO.getUsername().trim().toLowerCase())) {
            throw new UserAlreadyExistException(USER_IS_ALREADY_REGISTERED_EXCEPTION.concat(userDTO.getUsername()));
        } else if (isEmailRegistered(userDTO.getMail().trim().toLowerCase())){
            throw new UserAlreadyExistException(EMAIL_IS_ALREADY_REGISTERED_EXCEPTION.concat(userDTO.getMail()));
        }
        User user = new User(userDTO.getUsername().trim().toLowerCase(), userDTO.getPassword(), userDTO.getMail());
        log.info("[Log] Saving user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public URI buildSingUpUserUriCreated() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/singup").toUriString());
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean isUserRegistered(String username) {
        return (this.getUserByUsername(username) != null);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return (this.getUserByEmail(email) != null);
    }

}
