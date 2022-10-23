package pl.backendbscthesis.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany
    @JoinColumn(name = "employee_individual_id")
    private List<Employee> employeeList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<Activities> activitiesList = new java.util.ArrayList<>();

    private LocalDate dateOfAdmission;

    private LocalDate dateOfExecution;

    private String priority;

    private String status;

    private String period;

    private String note;


}
