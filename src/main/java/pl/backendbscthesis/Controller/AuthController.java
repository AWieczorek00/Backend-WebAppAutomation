package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Dto.JwtDto;
import pl.backendbscthesis.Dto.LoginDto;
import pl.backendbscthesis.Dto.MessageDto;
import pl.backendbscthesis.Dto.SignUpDto;
import pl.backendbscthesis.Entity.Role;
import pl.backendbscthesis.Entity.User;
import pl.backendbscthesis.Enum.ERole;
import pl.backendbscthesis.Repository.RoleRepository;
import pl.backendbscthesis.Repository.UserRepository;
import pl.backendbscthesis.Service.AuthService;
import pl.backendbscthesis.security.Jwt.JwtUtils;
import pl.backendbscthesis.security.service.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        JwtDto jwtDto = authService.authenticateUser(loginDto);

        return new ResponseEntity<>(jwtDto,HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {
            MessageDto messageDto;
        try {
            messageDto=authService.registerUser(signUpRequest);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(messageDto,HttpStatus.OK);
    }

}
