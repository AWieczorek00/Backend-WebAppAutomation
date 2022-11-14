package pl.backendbscthesis.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private float price;
    private int amount;

}
