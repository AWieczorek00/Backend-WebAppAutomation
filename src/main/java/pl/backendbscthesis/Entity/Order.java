package pl.backendbscthesis.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinColumn(name = "employee_individual_id")
    private List<Employee> employeeList;

    @OneToMany
    @JoinColumn(name = "activities_id")
    private List<Activities> activitiesList;

    @OneToMany
    @JoinColumn(name = "part_id")
    private List<Part> partList;

    private LocalDate dateOfAdmission;

    private LocalDate dateOfExecution;

    private float manHour;

    private float distance;

    private String priority;

    private String status;

    private String period;

    private String note;




}
