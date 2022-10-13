package pl.backendbscthesis.security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.backendbscthesis.Entity.User;
import pl.backendbscthesis.Repository.UserRepository;
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Nie znaleziono takiego użytkownika: "+username));

//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());

//        final CurrentUser currentUser = new CurrentUser();
//        currentUser.setUsername(user.get().getUsername());
//        currentUser.setPassword(user.get().getPassword());
//        currentUser.setAuthorities(authorities);


        return CurrentUser.build(user);
    }
}
