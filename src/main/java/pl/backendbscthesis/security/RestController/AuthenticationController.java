package pl.backendbscthesis.security.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Dto.ResponseDTO;
import pl.backendbscthesis.Dto.UserDTO;
import pl.backendbscthesis.security.Session.SessionRegistry;
import pl.backendbscthesis.security.User.CurrentUser;

import java.util.List;

@RequestMapping("/authorization")
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    private final AuthenticationManager manager;
    public final SessionRegistry sessionRegistry;

    @Autowired
    public AuthenticationController(AuthenticationManager manager, SessionRegistry sessionRegistry) {
        this.manager = manager;
        this.sessionRegistry = sessionRegistry;

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {

        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        final String sessionId = sessionRegistry.registerSession(user.getUsername());

        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        List<String> roles = currentUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        return ResponseEntity.ok(new ResponseDTO(
                sessionId,
                currentUser.getUsername(),
                currentUser.getEmail(),
                currentUser.getIndividualId(),
                roles
        ));
    }
}
