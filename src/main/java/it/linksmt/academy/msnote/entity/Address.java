package it.linksmt.academy.msnote.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private long id;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "IdUser", referencedColumnName = "Id", nullable = false)
    private User user;
}
