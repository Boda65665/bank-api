package com.example.demo.Entiti;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Table(name = "users")
@Entity
public class Users {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name="email")
    String email;
    @Column(name = "login")
    private String login;
    @Column(name="password")
    private String password;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    public Users() {

    }


    public Users(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }


    @Override
    public String toString() {
        return "{" +
                "email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }




}
