package c3.msmb.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import c3.msmb.jwt.JwtService;
import c3.msmb.model.User;
import c3.msmb.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userService.getUserByUsername(request.getUsername());
        String token = jwtService.getToken(userDetails);
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User(
            request.getUsername(),
            request.getEmail(),
            request.getFullName(),
            passwordEncoder.encode(request.getPassword()),
            request.getPhone(),
            request.getBirthDate(),
            LocalDateTime.now()
        );

        userService.saveUser(user);

        return new AuthResponse(jwtService.getToken(user));
    }
}