package pl.backendbscthesis.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Data
@Table(name = "activities")
@NoArgsConstructor
public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String name;

    private String attention;

    @Column(nullable = false)
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}


