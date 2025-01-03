package pl.backendbscthesis.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.backendbscthesis.Enum.ERole;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole name;

    @Column(name = "DESCRIPTION" ,nullable = false)
    private String description;

    @CreationTimestamp
    @Column(updatable = false, name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public Role(ERole name, String description) {
        this.name = name;
        this.description = description;
    }
}
