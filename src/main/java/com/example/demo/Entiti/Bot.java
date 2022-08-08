package com.example.demo.Entiti;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "bots")
public class Bot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "token")
    private String token;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private Users user;
    @OneToOne
    @JoinColumn(name = "owner")
    private Users owner;
}
