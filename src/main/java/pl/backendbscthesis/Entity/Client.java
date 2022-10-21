package pl.backendbscthesis.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 10)
    private String nip;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false,length = 9)
    private String numberPhone;

    private String email;

    @Column(nullable = false)
    private String type;


}
