package pl.backendbscthesis.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;


    private LocalDate executionTime;

    private Boolean done;

    @OneToOne
    @JoinColumn(name = "employee_individual_id")
    private Employee employee;

}
