package pl.backendbscthesis.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany
    @JoinColumn(name = "employee_individual_id")
    private List<Employee> employeeList;

    @OneToMany
    @JoinColumn(name = "activities_id")
    private List<Activities> activitiesList;

    private LocalDate dateOfAdmission;

    private LocalDate dateOfExecution;

    private String priority;

    private String status;

    private String period;

    private String note;


}
