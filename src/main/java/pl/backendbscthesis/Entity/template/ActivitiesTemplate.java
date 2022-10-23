package pl.backendbscthesis.Entity.template;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class ActivitiesTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Value("false")
    private boolean done;

    public ActivitiesTemplate(String name) {
        this.name = name;
    }
}
