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

    @Column(nullable = false,length = 11)
    private  Long pesel;

    @Column(nullable = false,length = 9)
    private Long phoneNumber;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfCreation;

    @ManyToMany
    @JoinColumn(name = "task_id")
    private List<Task> task;

    public Employee(Long individualId, String firstName, String secondName, String lastName, Long pesel, Long phoneNumber) {
        this.individualId = individualId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
    }


    //    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
