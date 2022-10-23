package pl.backendbscthesis.Configuration;

import org.springframework.context.annotation.Configuration;
import pl.backendbscthesis.Entity.Activities;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Entity.Role;
import pl.backendbscthesis.Entity.User;

import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.Repository.ActivitiesTemplateRepository;
import pl.backendbscthesis.Repository.EmployeeRepository;
import pl.backendbscthesis.Repository.RoleRepository;
import pl.backendbscthesis.Repository.UserRepository;
import pl.backendbscthesis.service.EmployeeService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class PreStart {
    public PreStart(UserRepository userRepo , RoleRepository roleRepository, EmployeeRepository employeeRepository, ActivitiesTemplateRepository activitiesRepository) {

        employeeRepository.save(
                new Employee(1234L, "Adam","Andrzej","Wieczorek", 12340678901L, 123456789L,LocalDate.now()));
        employeeRepository.save(
                new Employee(5678L, "Paulina","","Żelek", 12340678901L,123456789l,LocalDate.now()));


        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", 12340678901L, 123456789l,LocalDate.now());


        roleRepository.save(new Role(0L,"ADMIN"));
        roleRepository.save(new Role(0L,"USER"));
        Set<Role> rolesAdmin = new HashSet<>();
        Set<Role> rolesUser = new HashSet<>();
        rolesAdmin.add(new Role(1L,"ADMIN"));
        rolesUser.add(new Role(2L,"USER"));

        activitiesRepository.save(new ActivitiesTemplate("Analiza spalin"));


        userRepo.save(new User(0L,"user1","$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG", "test@wp.pl",rolesAdmin, employee));
//        userRepo.save(new User(0l,"user2","$2a$10$hvOa9FAisXftunkgb/QmkO5FLTQCI123rKTY.yuWAv9DjOW43/cSi",rolesUser));

    }
}
