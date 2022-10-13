package pl.backendbscthesis.Configuration;

import org.springframework.context.annotation.Configuration;
import pl.backendbscthesis.Entity.Role;
import pl.backendbscthesis.Entity.User;
import pl.backendbscthesis.Repository.RoleRepository;
import pl.backendbscthesis.Repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class PreStart {
    public PreStart(UserRepository userRepo , RoleRepository roleRepository) {

        roleRepository.save(new Role(0l,"ADMIN"));
        roleRepository.save(new Role(0l,"USER"));
        Set<Role> rolesAdmin = new HashSet<>();
        Set<Role> rolesUser = new HashSet<>();
        rolesAdmin.add(new Role(1l,"ADMIN"));
        rolesUser.add(new Role(2l,"USER"));

        userRepo.save(new User(0l,"user1","$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG",rolesAdmin));
        userRepo.save(new User(0l,"user2","$2a$10$hvOa9FAisXftunkgb/QmkO5FLTQCI123rKTY.yuWAv9DjOW43/cSi",rolesUser));

    }
}
