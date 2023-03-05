package pl.backendbscthesis.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "individual_id", nullable = false)
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
