package pl.backendbscthesis.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "individual_id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.)
    private Long individualId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String secondName;

    @Column(nullable = false)
    private String lastName;

    private String email;

    @Column(nullable = false,length = 9)
    private Long phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfCreation;
}
