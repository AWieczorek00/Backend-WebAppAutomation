package pl.backendbscthesis.Configuration;

import org.springframework.context.annotation.Configuration;
import pl.backendbscthesis.Entity.*;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.Entity.template.PartsTemplate;
import pl.backendbscthesis.Repository.*;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class PreStart {
    public PreStart(
            UserRepository userRepo,
            RoleRepository roleRepository,
            EmployeeRepository employeeRepository,
            ActivitiesTemplateRepository activitiesTemplateRepository,
            ActivitiesRepository activitiesRepository,
            OrderRepository orderRepository,
            ClientRepository clientRepository,
            PartsTemplateRepository partsTemplateRepository
            ) {


        Employee Adam = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Employee Paulina = new Employee(5678L, "Paulina", "", "Żelek", "awieczorek0000@gmail.com", 123456789L, LocalDate.now());
        List<Employee> employeeList = Arrays.asList(Adam, Paulina);
        employeeRepository.save(Adam);
        employeeRepository.save(Paulina);

        roleRepository.save(new Role(0L, "ADMIN"));
        roleRepository.save(new Role(0L, "USER"));
        Set<Role> rolesAdmin = new HashSet<>();
        Set<Role> rolesUser = new HashSet<>();
        rolesAdmin.add(new Role(1L, "ADMIN"));
        rolesUser.add(new Role(2L, "USER"));

        activitiesTemplateRepository.save(new ActivitiesTemplate("Analiza spalin"));
        activitiesTemplateRepository.save(new ActivitiesTemplate("Analiza komina"));




        Client sklepUromka = new Client(0L,"Sklep u Romka","123-456-10-10","Jana Pawła","Tuliszków","62-700","3","2A","123456789","uromka@wp.pl","firma");
        Client promont = new Client(0L,"Promont","987-654-10-10","Focus","Bydgoszcz","62-800","41","","987654321","poromont@onet.pl","firma");
        clientRepository.save(sklepUromka);
        clientRepository.save(promont);

        PartsTemplate oring = new PartsTemplate(0L,"Oring",3.25f,0.23f);
        PartsTemplate wezyk = new PartsTemplate(0L,"Gumowy wąż",5,0.20f);

        partsTemplateRepository.save(oring);
        partsTemplateRepository.save(wezyk);



        userRepo.save(new User(0L, "user1", "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG", "test@wp.pl", rolesAdmin, Adam));
        userRepo.save(new User(0L,"user2","$2a$10$hvOa9FAisXftunkgb/QmkO5FLTQCI123rKTY.yuWAv9DjOW43/cSi","test@wp.pl",rolesUser,Paulina));



    }
}
