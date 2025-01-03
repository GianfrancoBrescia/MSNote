package it.linksmt.academy.msnote.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "IdUser", referencedColumnName = "Id", nullable = false)
    private User user;

    @Column(name = "Title")
    private String title;

    @Column(name = "Body")
    private String body;
}
