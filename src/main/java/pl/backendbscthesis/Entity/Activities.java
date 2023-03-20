package pl.backendbscthesis.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "activities")
@NoArgsConstructor
@AllArgsConstructor
public class Activities {
    public Activities(String name, String attention, boolean done) {
        this.name = name;
        this.attention = attention;
        this.done = done;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String attention;

    @Column(nullable = false)
    private boolean done;



}


