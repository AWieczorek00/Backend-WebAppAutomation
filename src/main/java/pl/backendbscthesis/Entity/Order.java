package pl.backendbscthesis.Entity;

import lombok.Data;

import javax.persistence.*;
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
    @JoinColumn(name= "employee_individual_id")
    private List<Employee> employeeList;

}
