package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.AuthRequestDto;
import com.betrybe.agrix.controllers.dto.AuthResponseDto;
import com.betrybe.agrix.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Instantiates a new Authentication controller.
   *
   * @param authenticationManager the authentication manager
   * @param tokenService          the token service
   */
  public AuthController(AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login auth response dto.
   *
   * @param authRequestDto the auth request dto
   * @return the auth response dto
   */
  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
        authRequestDto.username(),
        authRequestDto.password()
    );

    Authentication authenticate = authenticationManager.authenticate(usernamePassword);
    UserDetails person = (UserDetails) authenticate.getPrincipal();

    String token = tokenService.generateToken(person);

    return ResponseEntity.ok(new AuthResponseDto(token));
  }
}
