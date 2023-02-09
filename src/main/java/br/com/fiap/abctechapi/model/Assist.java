package br.com.fiap.abctechapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "assistances")
public class Assist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 300)
    private String description;

}
