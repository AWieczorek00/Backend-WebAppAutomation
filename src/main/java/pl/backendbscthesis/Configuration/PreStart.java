package pl.backendbscthesis.Configuration;

import org.springframework.context.annotation.Configuration;
import pl.backendbscthesis.Entity.*;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
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
            ClientRepository clientRepository
            ) {


        Employee Adam = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", 12340678901L, 123456789L, LocalDate.now());
        Employee Paulina = new Employee(5678L, "Paulina", "", "Żelek", 12340678901L, 123456789L, LocalDate.now());
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

//        Activities testSpalin = new Activities("Analiza spalin","",true);
//        Activities testKomina = new Activities("Analiza komina","",true);
//        activitiesRepository.save(testSpalin);
//        activitiesRepository.save(testKomina);
//        List<Activities> activities = Arrays.asList(testSpalin,testKomina);


        Client sklepUromka = new Client(0l,"Sklep u Romka","123-456-10-10","Jana Pawła","Tuliszków","62-700","3","2A","123456789","uromka@wp.pl","firma");
        Client promont = new Client(0l,"Promont","987-654-10-10","Focus","Bydgoszcz","62-800","41","","987654321","poromont@onet.pl","firma");
        clientRepository.save(sklepUromka);
        clientRepository.save(promont);





        userRepo.save(new User(0L, "user1", "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG", "test@wp.pl", rolesAdmin, Adam));
//        userRepo.save(new User(0l,"user2","$2a$10$hvOa9FAisXftunkgb/QmkO5FLTQCI123rKTY.yuWAv9DjOW43/cSi",rolesUser));



//        orderRepository.save(new Order(1l, sklepUromka, employeeList, activities, LocalDate.now().minusDays(10), LocalDate.now(), "hight", "activ", "Monthly", "test"));
    }
}
