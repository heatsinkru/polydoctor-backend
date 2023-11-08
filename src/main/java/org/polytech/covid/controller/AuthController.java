package org.polytech.covid.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.polytech.covid.domain.ERole;
import org.polytech.covid.domain.Role;
import org.polytech.covid.domain.User;
import org.polytech.covid.payload.request.LoginRequest;
import org.polytech.covid.payload.request.SignupRequest;
import org.polytech.covid.payload.response.JwtResponse;
import org.polytech.covid.payload.response.MessageResponse;
import org.polytech.covid.repository.RoleRepository;
import org.polytech.covid.repository.UserRepository;
import org.polytech.covid.security.jwt.JwtUtils;
import org.polytech.covid.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles,
                         userDetails.getFirstName(), 
                         userDetails.getLastName(), 
                         userDetails.getPhone()
                         ));
  }

  @GetMapping("/signout")
  public ResponseEntity<?> signoutUser() {
    ResponseEntity.status(200);
    return ResponseEntity.ok(new MessageResponse("L'utilisateur a été déconnecté avec succès !"));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Ce nom d'utilisateur est déjà utilisé"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Cette adresse email est déjà utilisée"));
    }

    User user = new User(signUpRequest.getUsername(), 
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getEmail(),
               signUpRequest.getPhone(),
               signUpRequest.getFirstName(),
               signUpRequest.getLastName()
               );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
          .orElseThrow(() -> new RuntimeException("Ce rôle n'existe pas"));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Ce rôle n'existe pas"));
          roles.add(adminRole);

          break;
        case "superadmin":
          Role modRole = roleRepository.findByName(ERole.ROLE_SUPERADMIN)
              .orElseThrow(() -> new RuntimeException("Ce rôle n'existe pas"));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
              .orElseThrow(() -> new RuntimeException("Ce rôle n'existe pas."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Le compte utilisateur a été créé avec succès !"));
  }
}