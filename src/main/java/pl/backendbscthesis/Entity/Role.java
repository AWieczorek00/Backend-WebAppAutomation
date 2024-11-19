package pl.backendbscthesis.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.backendbscthesis.Enum.ERole;

import jakarta.persistence.*;



@Entity
@Data
@NoArgsConstructor
@Table(name="role")
public class Role {
    public Role(ERole name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
