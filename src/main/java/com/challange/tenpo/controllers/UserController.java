package com.challange.tenpo.controllers;

import com.challange.tenpo.dtos.AccessTokenDTO;
import com.challange.tenpo.dtos.UserDTO;
import com.challange.tenpo.dtos.UserLoginDTO;
import com.challange.tenpo.entitys.User;
import com.challange.tenpo.exceptions.UserAlreadyExistException;
import com.challange.tenpo.services.AuthService;
import com.challange.tenpo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/tenpo/user")
@Api(tags = "Users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    
    public UserController(UserService userService, AuthService authService) {
		super();
		this.userService = userService;
		this.authService = authService;
	}

	@PostMapping("/singup")
    @ApiOperation(value = "User Register")
    public ResponseEntity<User> singUpUser(
            @ApiParam(value = "User to register", required = true)
            @Valid @RequestBody UserDTO userDTO) throws UserAlreadyExistException {
        return ResponseEntity.created(userService.buildSingUpUserUriCreated()).body(userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    @ApiOperation(value = "User Login")
    public ResponseEntity<AccessTokenDTO> login(
            @ApiParam(value = "User Credentials", required = true)
            @Valid @RequestBody UserLoginDTO credentials) {
        return ResponseEntity.ok(authService.authenticate(credentials.getUsername(), credentials.getPassword()));
    }

    @GetMapping("/logout")
    @ApiOperation(value = "User Logout")
    public ResponseEntity<Map<String, String>> logout(
            @RequestHeader(required = true, value = "Authorization") String authHeader) {
        return ResponseEntity.ok(authService.invalidateAccessToken(authHeader));
    }
}
