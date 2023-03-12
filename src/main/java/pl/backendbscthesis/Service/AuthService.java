package pl.backendbscthesis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backendbscthesis.Dto.JwtDto;
import pl.backendbscthesis.Dto.LoginDto;
import pl.backendbscthesis.Dto.MessageDto;
import pl.backendbscthesis.Dto.SignUpDto;
import pl.backendbscthesis.Entity.Role;
import pl.backendbscthesis.Entity.User;
import pl.backendbscthesis.Enum.ERole;
import pl.backendbscthesis.Repository.RoleRepository;
import pl.backendbscthesis.Repository.UserRepository;
import pl.backendbscthesis.security.Jwt.JwtUtils;
import pl.backendbscthesis.security.service.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EmployeeService employeeService;

    public JwtDto authenticateUser(LoginDto loginDto) {

        Authentication authentication = getAuthenticate(loginDto);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = getRolesAssignedToUser(userDetails);

        JwtDto jwtDto = new JwtDto(jwt,
                userDetails.getIndividualId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        return jwtDto;
    }

    @Transactional
    public MessageDto registerUser(SignUpDto signUpRequest) {
        isExistUsernameOrPasswordThrowException(userRepository.existsByUsername(signUpRequest.getUsername()), "Error: Username is already taken!");

        isExistUsernameOrPasswordThrowException(userRepository.existsByEmail(signUpRequest.getEmail()), "Error: Email is already in use!");

        User user = createUser(signUpRequest);

        Set<String> strRoles = signUpRequest.getRole();

        user.setRoles(setRolesOrThrowException(strRoles));
        employeeService.createNewEmployee(signUpRequest.getEmployee());
        userRepository.save(user);

        return new MessageDto("User registered successfully!");
    }

    private Set<Role> setRolesOrThrowException(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        return roles;
    }

    private void isExistUsernameOrPasswordThrowException(Boolean userRepository, String message) {
        if (userRepository) {
            throw new RuntimeException(message);
        }
    }

    private User createUser(SignUpDto signUpRequest) {
        return new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmployee());
    }

    private Authentication getAuthenticate(LoginDto loginDto) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    }

    private List<String> getRolesAssignedToUser(UserDetailsImpl userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
