package it.linksmt.academy.msnote.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private long id;

    @Column(name = "CodiceFiscale", nullable = false)
    private String codiceFiscale;

    @Column(name = "Firstname", nullable = false)
    private String firstname;

    @Column(name = "Lastname", nullable = false)
    private String lastname;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Email")
    private String email;

    @Column(name = "Visibility", nullable = false)
    private boolean visibility = false;

    @Column(name = "Birthdate")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "user")
    private List<Address> addressList;

    @OneToMany(mappedBy = "user")
    private List<Note> noteList;
}
